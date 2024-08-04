package com.example.dietAssistant.mapper;

import com.example.dietAssistant.dto.UserNutrientSelect;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserNutrientPlansMapper {
    void addNutrientPlan(UserNutrientSelect userNutrientSelect);

    @Update("update usernutrientplans set selecte_status = false where user_id = #{userId} and selecte_status = false")
    void closeNutrientPlan(Integer userId);

    @Update("update usernutrientplans set selecte_status = true where user_id = #{userId} and nutrientplan_id = #{nutrientplanId}")
    void useNutrientPlan(UserNutrientSelect userNutrientSelect);
}
