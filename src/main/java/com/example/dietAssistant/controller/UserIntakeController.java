package com.example.dietAssistant.controller;

import com.example.dietAssistant.dto.*;
import com.example.dietAssistant.result.Result;
import com.example.dietAssistant.service.UserIntakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/userintake")
public class UserIntakeController {

    @Autowired
    UserIntakeService userIntakeService;

    @Autowired
    RedisTemplate redisTemplate;

    private static final DefaultRedisScript<Long> RemoveDeleteTime;
    static{
        RemoveDeleteTime = new DefaultRedisScript<>();
        RemoveDeleteTime.setLocation(new ClassPathResource("RemoveDeleteTime.lua"));
    }

    @PostMapping("/intakeNutrient")
    @ResponseBody
    public Result<String> intakeNutrient(@RequestBody UserIntake userIntake) {
        //记录摄入营养
        userIntakeService.intakeNutrient(userIntake);
        //设置摄入营养数据超时时间
//        RedisIntakeNutrient redisNutrientIntake = (RedisIntakeNutrient) redisTemplate.opsForValue().get("intakeNutrient" + userIntake.getUserId() + "time" + userIntake.getIntakeDate());
//        if(redisNutrientIntake == null)
//            redisNutrientIntake = new RedisIntakeNutrient();
        return Result.success("摄入营养成功");
    }

    @PostMapping("/intakeFood")
    @ResponseBody
    public Result<String> intakeFood(@RequestBody UserIntake userIntake) {
        //记录摄入食物
        userIntakeService.intakeFood(userIntake);
        //设置摄入食品数据的超时时间
//        RedisIntakeFood redisIntakeFood = (RedisIntakeFood) redisTemplate.opsForValue().get("intakeFood" + userIntake.getUserId() + "time" + userIntake.getIntakeDate());
//        if(redisIntakeFood == null)
//            redisIntakeFood = new RedisIntakeFood();
        return Result.success("摄入食品成功");
    }

    @GetMapping("/getIntakeFood")
    @ResponseBody
    public Result<List<IntakeFood>> getIntakeFood(@RequestBody UserIntake userIntake) {
        //获取摄入食物信息
        List<IntakeFood> list;
//        RedisIntakeFood redisIntakeFood = (RedisIntakeFood) redisTemplate.opsForValue().get("intakeFood" + userIntake.getUserId() + "time" + userIntake.getIntakeDate());
        list = (List<IntakeFood>) redisTemplate.opsForValue().get("intakeFood" + userIntake.getUserId() + "time" + userIntake.getIntakeDate());
        LocalDateTime deleteTime = (LocalDateTime) redisTemplate.opsForValue().get("intakeFood" + userIntake.getUserId() + "time" + userIntake.getIntakeDate() + "deleteTime");
        if (list == null || (deleteTime != null && LocalDateTime.now().isAfter(deleteTime))) {
            //如果缓存中不存在数据或者数据已过期就更新缓存数据
            list = userIntakeService.getIntakeFood(userIntake);
            // 用一个原子操作来更新缓存，原子操作的功能是更新缓存并从缓存中删除过期时间数据，删除删除时间的前提是删除时间没有被更新，也就是和之前的deleteTime相等
            redisTemplate.opsForValue().set("intakeFood" + userIntake.getUserId() + "time" + userIntake.getIntakeDate(), list, 12, TimeUnit.HOURS);
//            redisTemplate.opsForValue().decrement("intakeFood" + userIntake.getUserId() + "time" + userIntake.getIntakeDate() + "deleteTime");
            if (deleteTime != null)
                redisTemplate.execute(
                        RemoveDeleteTime,
                        Collections.emptyList(),
                        "intakeFood" + userIntake.getUserId() + "time" + userIntake.getIntakeDate(),
                        String.valueOf(deleteTime)
                );
        }
        Result result = Result.success("查询摄入食物成功").data(list);
        return result;
    }

    @GetMapping("/getIntakeNutrient")
    @ResponseBody
    public Result<List<IntakeNutrient>> getIntakeNutrient(@RequestBody UserIntake userIntake) {
        //获取摄入营养信息
        List<IntakeNutrient> list;
//        RedisIntakeNutrient redisIntakeNutrient = (RedisIntakeNutrient) redisTemplate.opsForValue().get("intakeNutrient" + userIntake.getUserId() + "time" + userIntake.getIntakeDate());
        list = (List<IntakeNutrient>) redisTemplate.opsForValue().get("intakeNutrient" + userIntake.getUserId() + "time" + userIntake.getIntakeDate());
        LocalDateTime deleteTime = (LocalDateTime) redisTemplate.opsForValue().get("intakeNutrient" + userIntake.getUserId() + "time" + userIntake.getIntakeDate() + "deleteTime");
        if(list == null || (deleteTime != null && LocalDateTime.now().isAfter(deleteTime))) {
            //如果缓存中不存在数据或者数据已过期就更新缓存数据
            list = userIntakeService.getIntakeNutrient(userIntake);
            // 用一个原子操作来更新缓存，原子操作的功能是判断当前redis中的要更新的数据的deleteTime有没有变，变了说明查询到的数据可能是旧值，不能把deleteTime值置为空，否则就把deleteTime置为空
            redisTemplate.opsForValue().set("intakeNutrient" + userIntake.getUserId() + "time" + userIntake.getIntakeDate(), list, 12, TimeUnit.HOURS);
//            redisTemplate.opsForValue().decrement("intakeNutrient" + userIntake.getUserId() + "time" + userIntake.getIntakeDate() + "deleteTime");
            if (deleteTime != null)
                redisTemplate.execute(
                        RemoveDeleteTime,
                        Collections.emptyList(),
                        "intakeNutrient" + userIntake.getUserId() + "time" + userIntake.getIntakeDate(),
                        String.valueOf(deleteTime)
                );
        }
        Result result = Result.success("查询摄入营养成功").data(list);
        return result;
    }
}
