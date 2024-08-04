package com.example.dietAssistant.service.impl;

import com.example.dietAssistant.dto.Food;
import com.example.dietAssistant.mapper.FoodMapper;
import com.example.dietAssistant.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FoodServiceImpl implements FoodService {

    @Autowired
    FoodMapper foodMapper;

    @Override
    public void add(Food food) {
        foodMapper.add(food);
    }
}
