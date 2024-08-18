package com.example.dietAssistant.service.impl;

import com.example.dietAssistant.dto.FoodNutrient;
import com.example.dietAssistant.dto.IntakeFood;
import com.example.dietAssistant.dto.IntakeNutrient;
import com.example.dietAssistant.dto.UserIntake;
import com.example.dietAssistant.mapper.FoodNutrientMapper;
import com.example.dietAssistant.mapper.UserIntakeMapper;
import com.example.dietAssistant.service.UserIntakeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

@Service
public class UserIntakeServiceImpl implements UserIntakeService {

    @Autowired
    UserIntakeMapper userIntakeMapper;

    @Autowired
    FoodNutrientMapper foodNutrientMapper;

    @Autowired
    ThreadPoolExecutor nutrientThreadPool;

    @Autowired
    ThreadPoolExecutor foodThreadPool;

    @Override
    public void intakeNutrient(UserIntake userIntake) {
        //摄入营养
//        userIntake.setIntakeDate(LocalDate.now());
//        UserIntake result = userIntakeMapper.getNurientIntake(userIntake);
//        if(result == null)
//            userIntakeMapper.insertNutrient(userIntake);
//        else {
//            result.setAmount(result.getAmount() + userIntake.getAmount());
//            userIntakeMapper.updateNutrient(result);
//        }
        nutrientThreadPool.execute(() -> {
            userIntake.setIntakeDate(LocalDate.now());
            UserIntake result = userIntakeMapper.getNurientIntake(userIntake);
            if(result == null)
                userIntakeMapper.insertNutrient(userIntake);
            else {
                result.setAmount(result.getAmount() + userIntake.getAmount());
                userIntakeMapper.updateNutrient(result);
            }
        });
    }

    @Override
    public void intakeFood(UserIntake userIntake) {
        //摄入食物
//        List<FoodNutrient> nutrients = foodNutrientMapper.getNutrientById(userIntake.getFoodId());
//        UserIntake userIntake1 = new UserIntake();
//        userIntake.setIntakeDate(LocalDate.now());
//        userIntake1.setUserId(userIntake.getUserId());
//        userIntake1.setIntakeDate(LocalDate.now());
//        for(FoodNutrient foodNutrient: nutrients) {
//            userIntake1.setNutrientId(foodNutrient.getNutrientId());
//            userIntake1.setNutrientName(foodNutrient.getNutrientName());
//            if(foodNutrient.getPercent() > 1) {
//                userIntake1.setAmount(userIntake.getAmount() * foodNutrient.getPercent() / 100);
//                userIntake1.setUnit(userIntake.getUnit());
//            }
//            else {
//                userIntake1.setAmount(userIntake.getAmount() * foodNutrient.getPercent() * 10);
//                userIntake1.setUnit("毫克");
//            }
//            intakeNutrient(userIntake1);
//        }
//        UserIntake result = userIntakeMapper.getFoodIntake(userIntake);
//        if(result == null)
//            userIntakeMapper.insertFood(userIntake);
//        else {
//            result.setAmount(result.getAmount() + userIntake.getAmount());
//            userIntakeMapper.updateFood(result);
//        }
        foodThreadPool.execute(
            new Runnable() {
                @Override
                @Transactional
                public void run() {
                    List<FoodNutrient> nutrients = foodNutrientMapper.getNutrientById(userIntake.getFoodId());
                    UserIntake userIntake1 = new UserIntake();
                    userIntake.setIntakeDate(LocalDate.now());
                    userIntake1.setUserId(userIntake.getUserId());
                    userIntake1.setIntakeDate(LocalDate.now());
                    for(FoodNutrient foodNutrient: nutrients) {
                        userIntake1.setNutrientId(foodNutrient.getNutrientId());
                        userIntake1.setNutrientName(foodNutrient.getNutrientName());
                        if(foodNutrient.getPercent() > 1) {
                            userIntake1.setAmount(userIntake.getAmount() * foodNutrient.getPercent() / 100);
                            userIntake1.setUnit(userIntake.getUnit());
                        }
                        else {
                            userIntake1.setAmount(userIntake.getAmount() * foodNutrient.getPercent() * 10);
                            userIntake1.setUnit("毫克");
                        }
                        intakeNutrient(userIntake1);
                    }
                    UserIntake result = userIntakeMapper.getFoodIntake(userIntake);
                    if(result == null)
                        userIntakeMapper.insertFood(userIntake);
                    else {
                        result.setAmount(result.getAmount() + userIntake.getAmount());
                        userIntakeMapper.updateFood(result);
                    }
                }
            }
        );
    }

    @Override
    public List<IntakeFood> getIntakeFood(UserIntake userIntake) {
        //查询摄入食物
        return userIntakeMapper.getIntakeFood(userIntake);
    }

    @Override
    public List<IntakeNutrient> getIntakeNutrient(UserIntake userIntake) {
        //查询摄入营养
        return userIntakeMapper.getIntakeNutrient(userIntake);
    }
}
