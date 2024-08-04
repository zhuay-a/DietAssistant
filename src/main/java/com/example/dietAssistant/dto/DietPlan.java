package com.example.dietAssistant.dto;

import lombok.Data;

@Data
public class DietPlan {
    private Integer planId;

    private Integer nutrientPlanId;

    private String name;

    private String description;

    private Boolean isOfficial;

    private String createUser;

    private Long updatedAt;
}
