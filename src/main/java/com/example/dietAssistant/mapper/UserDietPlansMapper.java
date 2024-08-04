package com.example.dietAssistant.mapper;

import com.example.dietAssistant.dto.UserNutrientSelect;
import com.example.dietAssistant.dto.UserPlanSelect;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserDietPlansMapper {
    void addFoodPlan(UserPlanSelect userPlanSelect);


    @Update("update userdietplans set selecte_status = true where user_id = #{userId} and dietplan_id = #{dietplanId}")
    void useFoodPlan(UserPlanSelect userPlanSelect);

    @Update("update userdietplans set selecte_status = false where user_id = #{userId} and selecte_status = true")
    void closeFoodPlan(Integer userId);
}
