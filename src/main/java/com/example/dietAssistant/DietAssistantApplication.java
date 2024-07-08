package com.example.dietAssistant;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@MapperScan("com.example.dietAssistant.mapper")
public class DietAssistantApplication {

    public static void main(String[] args) {
        SpringApplication.run(DietAssistantApplication.class, args);
    }

}
