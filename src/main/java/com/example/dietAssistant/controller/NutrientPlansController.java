package com.example.dietAssistant.controller;

import com.example.dietAssistant.dto.NutrientPlan;
import com.example.dietAssistant.mapper.NutrientPlansMapper;
import com.example.dietAssistant.result.Result;
import com.example.dietAssistant.service.impl.NutrientPlansImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/nutrientplans")
public class NutrientPlansController {

    @Autowired
    NutrientPlansImpl nutrientPlans;

    @PostMapping("/add")
    @ResponseBody
    public Result<String> addNutrientPlans(@RequestBody NutrientPlan nutrientPlan) {
        nutrientPlans.addPlan(nutrientPlan);
        return Result.success("添加营养计划成功");
    }

}
