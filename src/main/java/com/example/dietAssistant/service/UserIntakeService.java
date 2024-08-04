package com.example.dietAssistant.service;

import com.example.dietAssistant.dto.IntakeFood;
import com.example.dietAssistant.dto.IntakeNutrient;
import com.example.dietAssistant.dto.UserIntake;

import java.util.List;

public interface UserIntakeService {
    void intakeNutrient(UserIntake userIntake);

    void intakeFood(UserIntake userIntake);

    List<IntakeFood> getIntakeFood(UserIntake userIntake);

    List<IntakeNutrient> getIntakeNutrient(UserIntake userIntake);
}
