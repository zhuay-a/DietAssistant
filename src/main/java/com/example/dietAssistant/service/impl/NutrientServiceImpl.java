package com.example.dietAssistant.service.impl;

import com.example.dietAssistant.dto.Nutrient;
import com.example.dietAssistant.mapper.NutrientMapper;
import com.example.dietAssistant.service.NutrientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class NutrientServiceImpl implements NutrientService {

    @Autowired
    private NutrientMapper nutrientMapper;

    @Override
    public void addNutrient(Nutrient nutrient) {
        nutrient.setCreat_at(LocalDateTime.now());
        nutrient.setUpdate_at(LocalDateTime.now());
        nutrientMapper.addNutrient(nutrient);
    }

    @Override
    public void updateNutrient(Nutrient nutrient) {
        nutrientMapper.updateNutrient(nutrient);
    }
}
