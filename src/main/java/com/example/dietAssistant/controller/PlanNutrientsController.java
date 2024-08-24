package com.example.dietAssistant.controller;

import com.example.dietAssistant.dto.Nutrient;
import com.example.dietAssistant.dto.NutrientVO;
import com.example.dietAssistant.dto.PlanNutrients;
import com.example.dietAssistant.result.Result;
import com.example.dietAssistant.service.PlanNutrientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/planNutrients")
public class PlanNutrientsController {

    @Autowired
    PlanNutrientsService planNutrientsService;

    @PostMapping("/add")
    @ResponseBody
    public Result<String> addPlanNutrients(@RequestBody PlanNutrients planNutrients) {
        planNutrientsService.add(planNutrients);
        return Result.success("添加计划营养成分成功");
    }

    @PutMapping("/modify")
    @ResponseBody
    public Result<String> modify(@RequestBody PlanNutrients planNutrients) {
        planNutrientsService.modify(planNutrients);
        //修改缓存里面的营养成分
        return Result.success("修改计划营养成功");
    }

    @GetMapping("/get")
    @ResponseBody
    public Result<List<NutrientVO>> getNutrientList(@RequestBody PlanNutrients planNutrients) {
        List<NutrientVO> list = planNutrientsService.getById(planNutrients);
        Result result = Result.success("查询计划营养成功").data(list);
        return result;
    }
}
