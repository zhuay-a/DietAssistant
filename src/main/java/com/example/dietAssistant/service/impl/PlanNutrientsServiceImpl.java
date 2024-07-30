package com.example.dietAssistant.service.impl;

import com.example.dietAssistant.dto.Nutrient;
import com.example.dietAssistant.dto.NutrientVO;
import com.example.dietAssistant.dto.PlanNutrients;
import com.example.dietAssistant.mapper.PlanNutrientsMapper;
import com.example.dietAssistant.result.Result;
import com.example.dietAssistant.service.PlanNutrientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Service
public class PlanNutrientsServiceImpl implements PlanNutrientsService {
    
    @Autowired
    PlanNutrientsMapper planNutrientsMapper;
    
    @Override
    public void add(@RequestBody PlanNutrients planNutrients) {
        planNutrientsMapper.add(planNutrients);
    }

    @Override
    public void modify(PlanNutrients planNutrients) {
        planNutrientsMapper.modify(planNutrients);
    }

    @Override
    public List<NutrientVO> getById(PlanNutrients planNutrients) {
        List<NutrientVO> list = planNutrientsMapper.getById(planNutrients);
        return list;
    }
}
