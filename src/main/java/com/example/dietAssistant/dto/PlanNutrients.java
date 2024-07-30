package com.example.dietAssistant.dto;

import lombok.Data;

@Data
public class PlanNutrients {
    private Integer plan_id;

    private Integer nutrient_id;

    private Float target_amount;

    private String unit;

    private String nutrient_name;
}
