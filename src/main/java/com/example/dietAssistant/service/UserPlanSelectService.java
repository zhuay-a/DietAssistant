package com.example.dietAssistant.service;

import com.example.dietAssistant.dto.UserNutrientSelect;
import com.example.dietAssistant.dto.UserPlanSelect;

public interface UserPlanSelectService {
    void addFoodPlan(UserPlanSelect userPlanSelect);

    void addNutrientPlan(UserNutrientSelect userNutrientSelect);

    void useFoodPlan(UserPlanSelect userPlanSelect);

    void useNutrientPlan(UserNutrientSelect userNutrientSelect);
}
