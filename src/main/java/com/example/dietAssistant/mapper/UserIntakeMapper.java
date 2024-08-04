package com.example.dietAssistant.mapper;

import com.example.dietAssistant.dto.IntakeFood;
import com.example.dietAssistant.dto.IntakeNutrient;
import com.example.dietAssistant.dto.UserIntake;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserIntakeMapper {

    void intakeNutrient(UserIntake userIntake);

    @Select("select * from usernutrientintake where user_id = #{userId} and nutrient_id = #{nutrientId} and intake_date = #{intakeDate}")
    UserIntake getNurientIntake(UserIntake userIntake);

    void insertNutrient(UserIntake userIntake);

    void updateNutrient(UserIntake userIntake);

    @Select("select * from userfoodintake where user_id = #{userId} and food_id = #{foodId} and intake_date = #{intakeDate}")
    UserIntake getFoodIntake(UserIntake userIntake);

    void insertFood(UserIntake userIntake);

    void updateFood(UserIntake result);

    @Select("select food_id, food_name, amount, unit from userfoodintake where user_id = #{userId} and intake_date = #{intakeDate}")
    List<IntakeFood> getIntakeFood(UserIntake userIntake);

    @Select("select nutrient_id, nutrient_name, amount, unit from usernutrientintake where user_id = #{userId} and intake_date = #{intakeDate}")
    List<IntakeNutrient> getIntakeNutrient(UserIntake userIntake);
}
