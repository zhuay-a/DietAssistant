package com.example.dietAssistant.dto;

import lombok.Data;

@Data
public class PlanFoodVo {

    private Integer foodId;

    private String foodName;

    private Float targetAmount;

    private String unit;

    private String eatTime;
}
