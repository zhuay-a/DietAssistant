package com.example.dietAssistant.dto;

import lombok.Data;

@Data
public class IntakeNutrient {
    private Integer nutrientId;

    private String nutrientName;

    private Float amount;

    private String unit;
}
