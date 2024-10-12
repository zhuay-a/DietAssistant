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

    @PostMapping("/intakeNutrient")
    @ResponseBody
    public Result<String> intakeNutrient(@RequestBody UserIntake userIntake) {
        //记录摄入营养
        Boolean flag = userIntakeService.intakeNutrient(userIntake);
        if(flag)
            return Result.success("添加摄入营养信息成功");
        else
            return Result.fail("添加摄入营养信息失败");
    }

    @GetMapping("/getIntakeNutrient")
    @ResponseBody
    public Result<List<IntakeNutrient>> getIntakeNutrient(@RequestBody UserIntake userIntake) {
        //获取摄入营养信息
        List<IntakeNutrient> list = userIntakeService.getIntakeNutrient(userIntake);
        Result result = Result.success("查询摄入营养成功").data(list);
        return result;
    }

    @PostMapping("/intakeFood")
    @ResponseBody
    public Result<String> intakeFood(@RequestBody UserIntake userIntake) {
        //记录摄入食物
        Boolean flag = userIntakeService.intakeFood(userIntake);
        if(flag)
            return Result.success("添加摄入食品信息成功");
        else
            return Result.fail("添加摄入食品信息失败");
    }

    @GetMapping("/getIntakeFood")
    @ResponseBody
    public Result<List<IntakeFood>> getIntakeFood(@RequestBody UserIntake userIntake) {
        //获取摄入食物信息
        List<IntakeFood> list = userIntakeService.getIntakeFood(userIntake);
        Result result = Result.success("查询摄入食物成功").data(list);
        return result;
    }
}
