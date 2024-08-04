package com.example.dietAssistant.mapper;

import com.example.dietAssistant.dto.FoodNutrient;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FoodNutrientMapper {
    void add(FoodNutrient foodNutrient);

    @Select("select * from foodnutrients where food_id = #{foodId}")
    List<FoodNutrient> getNutrientById(Integer foodId);
}
