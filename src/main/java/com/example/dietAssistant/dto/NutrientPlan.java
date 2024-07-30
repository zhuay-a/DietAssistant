package com.example.dietAssistant.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NutrientPlan {
    private Integer plan_id;

    private String name;

    private String description;

    private Boolean is_official;

    private Integer created_user;

    private Long created_time;

    private Long updated_time;
}
