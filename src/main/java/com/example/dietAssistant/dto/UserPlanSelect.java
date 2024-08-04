package com.example.dietAssistant.dto;

import lombok.Data;

@Data
public class UserPlanSelect {

    private Integer userId;

    private Integer dietplanId;

    private String dietplanName;

    private Boolean selectStatus;
}
