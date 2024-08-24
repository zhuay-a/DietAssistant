package com.example.dietAssistant.service.impl;

import com.example.dietAssistant.dto.Nutrient;
import com.example.dietAssistant.dto.NutrientVO;
import com.example.dietAssistant.dto.PlanNutrients;
import com.example.dietAssistant.mapper.PlanNutrientsMapper;
import com.example.dietAssistant.result.Result;
import com.example.dietAssistant.service.PlanNutrientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Service
public class PlanNutrientsServiceImpl implements PlanNutrientsService {
    
    @Autowired
    PlanNutrientsMapper planNutrientsMapper;

    @Autowired
    RedisTemplate redisTemplate;
    
    @Override
    public void add(PlanNutrients planNutrients) {
        planNutrientsMapper.add(planNutrients);
        //把营养成分添加到缓存
        redisTemplate.opsForZSet().add("planNutrient" + planNutrients.getPlan_id(), planNutrients, planNutrients.getNutrient_id());
    }

    @Override
    public void modify(PlanNutrients planNutrients) {
        PlanNutrients last = planNutrientsMapper.getByPlanIdNutrientId(planNutrients);
        //更新缓存的计划营养成分
        planNutrientsMapper.modify(planNutrients);
        redisTemplate.opsForZSet().remove("planNutrient" + planNutrients.getPlan_id(), last);
        redisTemplate.opsForZSet().add("planNutrient" + planNutrients.getPlan_id(), planNutrients, planNutrients.getNutrient_id());
    }

    @Override
    public List<NutrientVO> getById(PlanNutrients planNutrients) {
        //查询计划包含的营养成分
        List<NutrientVO> list;
        list = (List<NutrientVO>) redisTemplate.opsForZSet().range("planNutrient" + planNutrients.getPlan_id(), 0, -1);
        if (list == null) {
            list = planNutrientsMapper.getById(planNutrients);
            for (int i = 0; i < list.size(); i++) {
                redisTemplate.opsForZSet().add("planNutrient" + planNutrients.getPlan_id(), planNutrients, planNutrients.getNutrient_id());
            }
        }

        return list;
    }
}
