package com.example.dietAssistant.dto;

import lombok.Data;

@Data
public class UserNutrientSelect {

    private Integer userId;

    private Integer nutrientplanId;

    private String nutrientplanName;

    private Boolean selectStatus;
}
