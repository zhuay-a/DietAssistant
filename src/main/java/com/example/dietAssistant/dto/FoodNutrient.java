package com.example.dietAssistant.dto;

import lombok.Data;

@Data
public class FoodNutrient {
    private Integer foodId;

    private Integer nutrientId;

    private Float percent;

    private String foodName;

    private String nutrientName;

    private Long createdTime;

    private Long updateTime;
}
