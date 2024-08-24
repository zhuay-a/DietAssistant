package com.example.dietAssistant.controller;

import com.example.dietAssistant.dto.NutrientPlan;
import com.example.dietAssistant.mapper.NutrientPlansMapper;
import com.example.dietAssistant.result.Result;
import com.example.dietAssistant.service.impl.NutrientPlansImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
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

    @Autowired
    RedisTemplate redisTemplate;

    @PostMapping("/add")
    @ResponseBody
    public Result<String> addNutrientPlans(@RequestBody NutrientPlan nutrientPlan) {
        nutrientPlans.addPlan(nutrientPlan);
        //把官方营养计划加入到缓存
        if (nutrientPlan.getIs_official() == true)
        redisTemplate.opsForValue().set("nutrientPlan" + nutrientPlan.getPlan_id(), nutrientPlan);
        return Result.success("添加营养计划成功");
    }

}
