package com.example.dietAssistant.mapper;

import com.example.dietAssistant.dto.NutrientPlan;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NutrientPlansMapper {

    void addPlan(NutrientPlan nutrientPlan);
}
