package com.example.dietAssistant.service.impl;

import com.example.dietAssistant.dto.UserNutrientSelect;
import com.example.dietAssistant.dto.UserPlanSelect;
import com.example.dietAssistant.mapper.UserDietPlansMapper;
import com.example.dietAssistant.mapper.UserNutrientPlansMapper;
import com.example.dietAssistant.service.UserPlanSelectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserPlanSelectServiceImpl implements UserPlanSelectService {

    @Autowired
    UserDietPlansMapper userDietPlansMapper;

    @Autowired
    UserNutrientPlansMapper userNutrientPlansMapper;

    @Override
    public void addFoodPlan(UserPlanSelect userPlanSelect) {
        userDietPlansMapper.addFoodPlan(userPlanSelect);
    }

    @Override
    public void addNutrientPlan(UserNutrientSelect userNutrientSelect) {
        userNutrientPlansMapper.addNutrientPlan(userNutrientSelect);
    }

    @Override
    public void useFoodPlan(UserPlanSelect userPlanSelect) {
        userDietPlansMapper.closeFoodPlan(userPlanSelect.getUserId());
        userDietPlansMapper.useFoodPlan(userPlanSelect);
    }

    @Override
    public void useNutrientPlan(UserNutrientSelect userNutrientSelect) {
        userNutrientPlansMapper.closeNutrientPlan(userNutrientSelect.getUserId());
        userNutrientPlansMapper.useNutrientPlan(userNutrientSelect);
    }
}
