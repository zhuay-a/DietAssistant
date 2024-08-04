package com.example.dietAssistant.service.impl;

import com.example.dietAssistant.dto.PlanFood;
import com.example.dietAssistant.dto.PlanFoodVo;
import com.example.dietAssistant.mapper.PlanFoodMapper;
import com.example.dietAssistant.service.PlanFoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanFoodServiceImpl implements PlanFoodService {

    @Autowired
    PlanFoodMapper planFoodMapper;

    @Override
    public void add(PlanFood planFood) {
        planFoodMapper.add(planFood);
    }

    @Override
    public List<PlanFoodVo> getByPlanId(Integer planId) {
        return planFoodMapper.getByPlanId(planId);
    }
}
