package com.example.dietAssistant.dto;

import lombok.Data;

@Data
public class NutrientVO {

    private String nutrientName;

    private Float targetAmount;

    private String unit;
}
