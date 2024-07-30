package com.example.dietAssistant.mapper;

import com.example.dietAssistant.dto.Nutrient;
import com.example.dietAssistant.dto.NutrientVO;
import com.example.dietAssistant.dto.PlanNutrients;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PlanNutrientsMapper {
    void add(PlanNutrients planNutrients);

    void modify(PlanNutrients planNutrients);

    @Select("select nutrient_name, target_amount, unit from plannutrients where plan_id = #{plan_id}")
    List<NutrientVO> getById(PlanNutrients planNutrients);
}
