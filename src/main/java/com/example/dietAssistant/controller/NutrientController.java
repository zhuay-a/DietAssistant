package com.example.dietAssistant.controller;

import com.example.dietAssistant.dto.Nutrient;
import com.example.dietAssistant.result.Result;
import com.example.dietAssistant.service.NutrientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Controller
@RequestMapping("/Nutrient")
public class NutrientController {

    @Autowired
    NutrientService nutrientService;

    @Autowired
    RedisTemplate redisTemplate;

    @PostMapping("/add")
    @ResponseBody
    public Result<String> addNutrient(@RequestBody Nutrient nutrient) {
        nutrientService.addNutrient(nutrient);
        //把营养成分都加到缓存里，营养成分只能由官方增加
        redisTemplate.opsForValue().set("nutrient" + nutrient.getNutrient_id(), nutrient);
        return Result.success("添加营养成功");
    }

    @PutMapping("/update")
    @ResponseBody
    public Result<String> updateNutrient(@RequestBody Nutrient nutrient) {
        nutrientService.updateNutrient(nutrient);
        // 这里其实不用异步，直接同步执行数据库更新和删除操作,更新数据时缓存为空可能会出现数据库缓存不一致问题
        Nutrient date = (Nutrient) redisTemplate.opsForValue().get("nutrient" + nutrient.getNutrient_id());
        if(date != null) {
            LocalDateTime deleteTime = LocalDateTime.now().plus(500, ChronoUnit.MILLIS);
            date.setDeleteTime(deleteTime);
            redisTemplate.opsForValue().set("nutrient" + nutrient.getNutrient_id(), date);
        }
        return Result.success("营养信息更新成功");
    }
}
