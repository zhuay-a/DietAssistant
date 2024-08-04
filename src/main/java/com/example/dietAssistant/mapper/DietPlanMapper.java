package com.example.dietAssistant.mapper;

import com.example.dietAssistant.dto.DietPlan;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DietPlanMapper {
    void add(DietPlan dietPlan);
}
