package com.example.dietAssistant.controller;

import com.example.dietAssistant.dto.DietPlan;
import com.example.dietAssistant.dto.Food;
import com.example.dietAssistant.dto.PlanFood;
import com.example.dietAssistant.dto.PlanFoodVo;
import com.example.dietAssistant.result.Result;
import com.example.dietAssistant.service.PlanFoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/planfood")
public class PlanFoodController {

    @Autowired
    PlanFoodService planFoodService;

    @Autowired
    RedisTemplate redisTemplate;

    @PostMapping("/add")
    @ResponseBody
    public Result<String> addPlanFood(@RequestBody PlanFood planFood) {
        planFoodService.add(planFood);
        //用集合存储计划食品，用食品id作分数
        redisTemplate.opsForZSet().add("planFood" + planFood.getPlanId(), planFood, planFood.getFoodId());
        return Result.success("添加计划食品成功");
    }

    @GetMapping("/get")
    @ResponseBody
    public Result<List<PlanFoodVo>> getPlanFood(@RequestBody DietPlan dietPlan) {
        List<PlanFoodVo> list;
        list = (List<PlanFoodVo>) redisTemplate.opsForZSet().range("planFood" + dietPlan.getPlanId(),0, -1);
        if(list == null) {
            list = planFoodService.getByPlanId(dietPlan.getPlanId());
            for (int i = 0; i < list.size(); i++) {
                redisTemplate.opsForZSet().add("planFood" + dietPlan.getPlanId(), list.get(i), list.get(i).getFoodId());
            }
        }
        Result result = Result.success("成功获取计划食品");
        result.data(list);
        return result;
    }
}
