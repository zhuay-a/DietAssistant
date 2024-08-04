package com.example.dietAssistant.mapper;

import com.example.dietAssistant.dto.Food;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FoodMapper {
    void add(Food food);
}
