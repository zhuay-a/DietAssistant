package com.example.dietAssistant.service.impl;

import com.example.dietAssistant.dto.FoodNutrient;
import com.example.dietAssistant.mapper.FoodNutrientMapper;
import com.example.dietAssistant.service.FoodNutrientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FoodNutrientServiceImpl implements FoodNutrientService {

    @Autowired
    FoodNutrientMapper foodNutrientMapper;

    @Override
    public void add(FoodNutrient foodNutrient) {
        foodNutrientMapper.add(foodNutrient);
    }
}
