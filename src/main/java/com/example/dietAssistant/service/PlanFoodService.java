package com.example.dietAssistant.service;

import com.example.dietAssistant.dto.PlanFood;
import com.example.dietAssistant.dto.PlanFoodVo;

import java.util.List;

public interface PlanFoodService {
    void add(PlanFood planFood);

    List<PlanFoodVo> getByPlanId(Integer planId);
}
