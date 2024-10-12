package com.example.dietAssistant.service.impl;

import com.example.dietAssistant.dto.FoodNutrient;
import com.example.dietAssistant.dto.IntakeFood;
import com.example.dietAssistant.dto.IntakeNutrient;
import com.example.dietAssistant.dto.UserIntake;
import com.example.dietAssistant.mapper.FoodNutrientMapper;
import com.example.dietAssistant.mapper.UserIntakeMapper;
import com.example.dietAssistant.result.Result;
import com.example.dietAssistant.service.UserIntakeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Service
public class UserIntakeServiceImpl implements UserIntakeService {

    @Autowired
    UserIntakeMapper userIntakeMapper;

    @Autowired
    FoodNutrientMapper foodNutrientMapper;

    @Autowired
    ThreadPoolExecutor nutrientThreadPool;

    @Autowired
    ThreadPoolExecutor foodThreadPool;

    @Autowired
    RedisTemplate redisTemplate;

    private static final DefaultRedisScript<Long> UpdateDeleteTime;
    static{
        UpdateDeleteTime = new DefaultRedisScript<>();
        UpdateDeleteTime.setLocation(new ClassPathResource("UpdateDeleteTime.lua"));
    }

    private static final DefaultRedisScript<Long> RemoveDeleteTime;
    static{
        RemoveDeleteTime = new DefaultRedisScript<>();
        RemoveDeleteTime.setLocation(new ClassPathResource("RemoveDeleteTime.lua"));
    }

    @Override
    @Transactional
    public Boolean intakeNutrient(UserIntake userIntake) {
        Boolean flag = Boolean.FALSE;
        //摄入营养
        userIntake.setIntakeDate(LocalDate.now());
        UserIntake result = userIntakeMapper.getNurientIntake(userIntake);
        if(result == null)
            userIntakeMapper.insertNutrient(userIntake);
        else {
            result.setAmount(result.getAmount() + userIntake.getAmount());
            userIntakeMapper.updateNutrient(result);
        }

        //删除缓存数据
        if(redisTemplate.delete("intakeNutrient" + userIntake.getUserId() + "time" + userIntake.getIntakeDate()))
            flag = Boolean.TRUE;

        //更新删除时间
        LocalDateTime deleteTime = LocalDateTime.now().plus(1000, ChronoUnit.MILLIS);
        Long updateFlag = (Long) redisTemplate.execute(
                UpdateDeleteTime,
                Collections.emptyList(),
                "intakeNutrient" + userIntake.getUserId() + "time" + userIntake.getIntakeDate() + "deleteTime",
                String.valueOf(deleteTime)
        );
        if(updateFlag == 1)
            flag = Boolean.TRUE;

        //如果两次缓存操作都失败就手动回滚事务
        if(!flag) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return flag;
    }

    @Override
    public List<IntakeNutrient> getIntakeNutrient(UserIntake userIntake) {
        //获取摄入营养信息
        List<IntakeNutrient> list;
        LocalDateTime deleteTime = (LocalDateTime) redisTemplate.opsForValue().get("intakeNutrient" + userIntake.getUserId() + "time" + userIntake.getIntakeDate() + "deleteTime");
        if(deleteTime == null || LocalDateTime.now().isAfter(deleteTime)) {
            //如果缓存中不存在数据或者数据已过期就更新缓存数据
            list = userIntakeMapper.getIntakeNutrient(userIntake);
            redisTemplate.opsForValue().set("intakeNutrient" + userIntake.getUserId() + "time" + userIntake.getIntakeDate(), list, 12, TimeUnit.HOURS);

            // 用一个原子操作来更新缓存，原子操作的功能是判断当前redis中的要更新的数据的deleteTime有没有变，变了说明查询到的数据可能是旧值，不能把deleteTime值置为空，否则就把deleteTime置为空
            redisTemplate.execute(
                    RemoveDeleteTime,
                    Collections.emptyList(),
                    "intakeNutrient" + userIntake.getUserId() + "time" + userIntake.getIntakeDate(),
                    String.valueOf(deleteTime)
            );
        }
        //从缓存中查询摄入营养
        list = (List<IntakeNutrient>) redisTemplate.opsForValue().get("intakeNutrient" + userIntake.getUserId() + "time" + userIntake.getIntakeDate());
        return list;
    }

    @Override
    public Boolean intakeFood(UserIntake userIntake) {
        Boolean flag = Boolean.FALSE;

        //摄入食物
        List<FoodNutrient> nutrients = foodNutrientMapper.getNutrientById(userIntake.getFoodId());
        UserIntake userIntake1 = new UserIntake();
        userIntake.setIntakeDate(LocalDate.now());
        userIntake1.setUserId(userIntake.getUserId());
        userIntake1.setIntakeDate(LocalDate.now());
        for(FoodNutrient foodNutrient: nutrients) {
            userIntake1.setNutrientId(foodNutrient.getNutrientId());
            userIntake1.setNutrientName(foodNutrient.getNutrientName());
            if(foodNutrient.getPercent() > 1) {
                userIntake1.setAmount(userIntake.getAmount() * foodNutrient.getPercent() / 100);
                userIntake1.setUnit(userIntake.getUnit());
            }
            else {
                userIntake1.setAmount(userIntake.getAmount() * foodNutrient.getPercent() * 10);
                userIntake1.setUnit("毫克");
            }
            intakeNutrient(userIntake1);
        }
        UserIntake result = userIntakeMapper.getFoodIntake(userIntake);
        if(result == null)
            userIntakeMapper.insertFood(userIntake);
        else {
            result.setAmount(result.getAmount() + userIntake.getAmount());
            userIntakeMapper.updateFood(result);
        }

        //删除缓存数据
        if(redisTemplate.delete("intakeFood" + userIntake.getUserId() + "time" + userIntake.getIntakeDate()))
            flag = Boolean.TRUE;

        //更新删除时间
        LocalDateTime deleteTime = LocalDateTime.now().plus(500, ChronoUnit.MILLIS);
        Long updataFlag = (Long) redisTemplate.execute(
                UpdateDeleteTime,
                Collections.emptyList(),
                "intakeFood" + userIntake.getUserId() + "time" + userIntake.getIntakeDate() + "deleteTime",
                String.valueOf(deleteTime)
        );
        if(updataFlag == 1)
            flag = Boolean.TRUE;

        //如果两次缓存操作都失败就手动回滚事务
        if(!flag)
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        return flag;
    }

    @Override
    public List<IntakeFood> getIntakeFood(UserIntake userIntake) {
        //查询摄入食物
        List<IntakeFood> list;
        LocalDateTime deleteTime = (LocalDateTime) redisTemplate.opsForValue().get("intakeFood" + userIntake.getUserId() + "time" + userIntake.getIntakeDate() + "deleteTime");
        if (deleteTime != null && LocalDateTime.now().isAfter(deleteTime)) {
            //如果缓存中不存在数据或者数据已过期就更新缓存数据
            list = userIntakeMapper.getIntakeFood(userIntake);
            redisTemplate.opsForValue().set("intakeFood" + userIntake.getUserId() + "time" + userIntake.getIntakeDate(), list, 12, TimeUnit.HOURS);

            // 用一个原子操作来更新缓存，原子操作的功能是重置缓存中的删除过期时间数据，重置删除时间的前提是删除时间没有被更新，也就是和之前的deleteTime相等
            redisTemplate.execute(
                    RemoveDeleteTime,
                    Collections.emptyList(),
                    "intakeFood" + userIntake.getUserId() + "time" + userIntake.getIntakeDate(),
                    String.valueOf(deleteTime)
            );
        }

        //从缓存中查询数据
        list = (List<IntakeFood>) redisTemplate.opsForValue().get("intakeFood" + userIntake.getUserId() + "time" + userIntake.getIntakeDate());
        return list;
    }
}
