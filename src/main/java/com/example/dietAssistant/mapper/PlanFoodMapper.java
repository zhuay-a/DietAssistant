package com.example.dietAssistant.mapper;

import com.example.dietAssistant.dto.PlanFood;
import com.example.dietAssistant.dto.PlanFoodVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PlanFoodMapper {

    void add(PlanFood planFood);

    @Select("select food_id, food_name, target_amount, unit, eat_time from planfoods where plan_id = #{planId}")
    List<PlanFoodVo> getByPlanId(Integer planId);
}
