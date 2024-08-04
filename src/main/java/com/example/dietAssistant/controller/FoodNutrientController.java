package com.example.dietAssistant.controller;

import com.example.dietAssistant.dto.FoodNutrient;
import com.example.dietAssistant.result.Result;
import com.example.dietAssistant.service.FoodNutrientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/foodnutrient")
public class FoodNutrientController {

    @Autowired
    FoodNutrientService foodNutrientService;

    @PostMapping("/add")
    @ResponseBody
    public Result<String> addFoodNutrient(@RequestBody FoodNutrient foodNutrient) {
        foodNutrientService.add(foodNutrient);
        return Result.success("添加食品营养含量成功");
    }
}
