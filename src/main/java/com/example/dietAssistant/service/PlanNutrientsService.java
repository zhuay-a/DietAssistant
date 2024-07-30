package com.example.dietAssistant.service;

import com.example.dietAssistant.dto.Nutrient;
import com.example.dietAssistant.dto.NutrientVO;
import com.example.dietAssistant.dto.PlanNutrients;

import java.util.List;

public interface PlanNutrientsService {
    public void add(PlanNutrients planNutrients);

    void modify(PlanNutrients planNutrients);

    List<NutrientVO> getById(PlanNutrients planNutrients);
}
