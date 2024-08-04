package com.example.dietAssistant.controller;

import com.example.dietAssistant.dto.DietPlan;
import com.example.dietAssistant.result.Result;
import com.example.dietAssistant.service.DietPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/dietPlan")
public class dietPlanController {

    @Autowired
    DietPlanService dietPlanService;

    @PostMapping("/add")
    @ResponseBody
    public Result<String> addDietPlan(@RequestBody DietPlan dietPlan) {
        dietPlanService.add(dietPlan);
        return Result.success("添加饮食计划成功");
    }
}
