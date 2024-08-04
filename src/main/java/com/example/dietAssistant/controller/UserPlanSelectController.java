package com.example.dietAssistant.controller;

import com.example.dietAssistant.dto.UserNutrientSelect;
import com.example.dietAssistant.dto.UserPlanSelect;
import com.example.dietAssistant.result.Result;
import com.example.dietAssistant.service.UserPlanSelectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/userplan")
public class UserPlanSelectController {

    @Autowired
    UserPlanSelectService userPlanSelectService;

    @PostMapping("/addFoodPlan")
    @ResponseBody
    public Result<String> addFoodPlan(@RequestBody UserPlanSelect userPlanSelect) {
        userPlanSelectService.addFoodPlan(userPlanSelect);
        return Result.success("用户收藏饮食计划成功");
    }

    @PutMapping("/useFoodPlan")
    @ResponseBody
    public Result<String> useFoodPlan(@RequestBody UserPlanSelect userPlanSelect) {
        userPlanSelectService.useFoodPlan(userPlanSelect);
        return Result.success("启用饮食计划成功");
    }

    @PostMapping("/addNutrientPlan")
    @ResponseBody
    public Result<String> addNutrientPlan(@RequestBody UserNutrientSelect userNutrientSelect) {
        userPlanSelectService.addNutrientPlan(userNutrientSelect);
        return Result.success("用户收藏营养计划成功");
    }

    @PutMapping("/useNutrientPlan")
    @ResponseBody
    public Result<String> useNutrientPlan(@RequestBody UserNutrientSelect userNutrientSelect) {
        userPlanSelectService.useNutrientPlan(userNutrientSelect);
        return Result.success("启用营养计划成功");
    }


}
