package com.example.dietAssistant.mapper;

import com.example.dietAssistant.dto.Nutrient;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NutrientMapper {

    void addNutrient(Nutrient nutrient);

    void updateNutrient(Nutrient nutrient);
}
