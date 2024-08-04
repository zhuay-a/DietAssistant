package com.example.dietAssistant.dto;

import lombok.Data;

@Data
public class IntakeFood {

    private Integer foodId;

    private String foodName;

    private Float amount;

    private String unit;
}
