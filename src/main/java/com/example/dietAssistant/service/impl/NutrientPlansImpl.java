package com.example.dietAssistant.service.impl;

import com.example.dietAssistant.dto.NutrientPlan;
import com.example.dietAssistant.mapper.NutrientPlansMapper;
import com.example.dietAssistant.service.NutrientPlansService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NutrientPlansImpl implements NutrientPlansService {

    @Autowired
    NutrientPlansMapper nutrientPlansMapper;

    public void addPlan(NutrientPlan nutrientPlan) {
        nutrientPlansMapper.addPlan(nutrientPlan);
    }
}
