package com.example.dietAssistant.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Nutrient {
    private Integer nutrient_id;

    private String name;

    private String description;

    private LocalDateTime creat_at;

    private LocalDateTime update_at;

    private LocalDateTime DeleteTime;
}
