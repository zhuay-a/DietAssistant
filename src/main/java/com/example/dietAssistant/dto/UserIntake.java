package com.example.dietAssistant.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class UserIntake {

    private Integer userId;

    private Integer foodId;

    private Integer nutrientId;

    private LocalDate intakeDate;

    private String foodName;

    private String nutrientName;

    private Float amount;

    private String unit;
}
