package com.example.dietAssistant.controller;

import com.example.dietAssistant.dto.IntakeFood;
import com.example.dietAssistant.dto.IntakeNutrient;
import com.example.dietAssistant.dto.UserIntake;
import com.example.dietAssistant.mapper.UserIntakeMapper;
import com.example.dietAssistant.result.Result;
import com.example.dietAssistant.service.UserIntakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/userintake")
public class UserIntakeController {

    @Autowired
    UserIntakeService userIntakeService;

    @PostMapping("/intakeNutrient")
    @ResponseBody
    public Result<String> intakeNutrient(@RequestBody UserIntake userIntake) {
        userIntakeService.intakeNutrient(userIntake);
        return Result.success("摄入营养成功");
    }

    @PostMapping("/intakeFood")
    @ResponseBody
    public Result<String> intakeFood(@RequestBody UserIntake userIntake) {
        userIntakeService.intakeFood(userIntake);
        return Result.success("摄入食品成功");
    }

    @GetMapping("/getIntakeFood")
    @ResponseBody
    public Result<List<IntakeFood>> getIntakeFood(@RequestBody UserIntake userIntake) {
        List<IntakeFood> list = userIntakeService.getIntakeFood(userIntake);
        Result result = Result.success("查询摄入食物成功").data(list);
        return result;
    }

    @GetMapping("/getIntakeNutrient")
    @ResponseBody
    public Result<List<IntakeNutrient>> getIntakeNutrient(@RequestBody UserIntake userIntake) {
        List<IntakeNutrient> list = userIntakeService.getIntakeNutrient(userIntake);
        Result result = Result.success("查询摄入营养成功").data(list);
        return result;
    }
}
