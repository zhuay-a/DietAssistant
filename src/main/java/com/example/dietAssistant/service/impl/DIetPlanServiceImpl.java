package com.example.dietAssistant.service.impl;

import com.example.dietAssistant.dto.DietPlan;
import com.example.dietAssistant.mapper.DietPlanMapper;
import com.example.dietAssistant.service.DietPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DIetPlanServiceImpl implements DietPlanService {

    @Autowired
    DietPlanMapper dietPlanMapper;

    @Override
    public void add(DietPlan dietPlan) {
        dietPlanMapper.add(dietPlan);
    }
}
