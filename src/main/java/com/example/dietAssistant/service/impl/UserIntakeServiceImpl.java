package com.example.dietAssistant.service.impl;

import com.example.dietAssistant.dto.FoodNutrient;
import com.example.dietAssistant.dto.IntakeFood;
import com.example.dietAssistant.dto.IntakeNutrient;
import com.example.dietAssistant.dto.UserIntake;
import com.example.dietAssistant.mapper.FoodNutrientMapper;
import com.example.dietAssistant.mapper.UserIntakeMapper;
import com.example.dietAssistant.service.UserIntakeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

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

    @Override
    public void intakeNutrient(UserIntake userIntake) {
        //摄入营养
        nutrientThreadPool.execute(() -> {
            userIntake.setIntakeDate(LocalDate.now());
            UserIntake result = userIntakeMapper.getNurientIntake(userIntake);
            if(result == null)
                userIntakeMapper.insertNutrient(userIntake);
            else {
                result.setAmount(result.getAmount() + userIntake.getAmount());
                userIntakeMapper.updateNutrient(result);
            }
            //后台线程中在更新完数据库后在缓存中设置营养数据更新时间
            LocalDateTime deleteTime = LocalDateTime.now().plus(500, ChronoUnit.MILLIS);
//            redisTemplate.opsForValue().set("intakeNutrient" + userIntake.getUserId() + "time" + userIntake.getIntakeDate() + "deleteTime", deleteTime);
            redisTemplate.execute(
                    UpdateDeleteTime,
                    Collections.emptyList(),
                    "intakeNutrient" + userIntake.getUserId() + "time" + userIntake.getIntakeDate() + "deleteTime",
                    String.valueOf(deleteTime)
            );
        });
    }

    @Override
    public void intakeFood(UserIntake userIntake) {
        //摄入食物
        foodThreadPool.execute(
            new Runnable() {
                @Override
                @Transactional
                public void run() {
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
                    //后台线程中在更新完数据库后在缓存中设置食品数据更新时间
                    LocalDateTime deleteTime = LocalDateTime.now().plus(500, ChronoUnit.MILLIS);
//                    redisTemplate.opsForValue().set("intakeFood" + userIntake.getUserId() + "time" + userIntake.getIntakeDate() + "deleteTime", deleteTime);
                    redisTemplate.execute(
                            UpdateDeleteTime,
                            Collections.emptyList(),
                            "intakeFood" + userIntake.getUserId() + "time" + userIntake.getIntakeDate() + "deleteTime",
                            String.valueOf(deleteTime)
                    );
                }
            }
        );
    }

    @Override
    public List<IntakeFood> getIntakeFood(UserIntake userIntake) {
        //查询摄入食物
        return userIntakeMapper.getIntakeFood(userIntake);
    }

    @Override
    public List<IntakeNutrient> getIntakeNutrient(UserIntake userIntake) {
        //查询摄入营养
        return userIntakeMapper.getIntakeNutrient(userIntake);
    }
}
