package com.example.dietAssistant.dto;

import lombok.Data;

@Data
public class PlanFood {

    private Integer planId;

    private Integer foodId;

    private String foodName;

    private Float targetAmount;

    private String unit;

    private String eatTime;
}
