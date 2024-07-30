package com.example.dietAssistant.controller;

import com.example.dietAssistant.dto.Nutrient;
import com.example.dietAssistant.result.Result;
import com.example.dietAssistant.service.NutrientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/Nutrient")
public class NutrientController {

    @Autowired
    private NutrientService nutrientService;

    @PostMapping("/add")
    @ResponseBody
    public Result<String> addNutrient(@RequestBody Nutrient nutrient) {
        nutrientService.addNutrient(nutrient);
        return Result.success("添加营养成功");
    }

    @PutMapping("/update")
    @ResponseBody
    public Result<String> updateNutrient(@RequestBody Nutrient nutrient) {
        nutrientService.updateNutrient(nutrient);
        return Result.success("营养信息更新成功");
    }
}
