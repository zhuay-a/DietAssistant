package com.example.dietAssistant.controller;

import com.example.dietAssistant.dto.DietPlan;
import com.example.dietAssistant.dto.Food;
import com.example.dietAssistant.dto.PlanFood;
import com.example.dietAssistant.dto.PlanFoodVo;
import com.example.dietAssistant.result.Result;
import com.example.dietAssistant.service.PlanFoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/planfood")
public class PlanFoodController {

    @Autowired
    PlanFoodService planFoodService;

    @PostMapping("/add")
    @ResponseBody
    public Result<String> addPlanFood(@RequestBody PlanFood planFood) {
        planFoodService.add(planFood);
        return Result.success("添加计划食品成功");
    }

    @GetMapping("/get")
    @ResponseBody
    public Result<List<PlanFoodVo>> getPlanFood(@RequestBody DietPlan dietPlan) {
        List<PlanFoodVo> list = planFoodService.getByPlanId(dietPlan.getPlanId());
        Result result = Result.success("成功获取计划食品");
        result.data(list);
        return result;
    }
}
