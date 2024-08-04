package com.example.dietAssistant.dto;

import lombok.Data;

@Data
public class Food {

    private Integer foodId;

    private String name;

    private String description;

    private Boolean isOfficial;

    private Integer createdUser;

    private Long createdTime;

    private Long updatedTime;
}
