package com.example.dietAssistant.controller;

import com.example.dietAssistant.dto.Food;
import com.example.dietAssistant.result.Result;
import com.example.dietAssistant.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/food")
public class FoodController {

    @Autowired
    FoodService foodService;

    @Autowired
    RedisTemplate redisTemplate;

    @PostMapping("/add")
    @ResponseBody
    public Result<String> addFood(@RequestBody Food food) {
        foodService.add(food);
        //官方食品直接放入缓存里
        if(food.getIsOfficial() == true)
            redisTemplate.opsForValue().set("food" + food.getFoodId(), food);
        return Result.success("成功添加食品");
    }


}
