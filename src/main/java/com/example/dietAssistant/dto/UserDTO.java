package com.example.dietAssistant.dto;

import lombok.Data;

@Data
public class UserDTO {
    private Integer id;

    private String username;

    private String password;

    private Integer age;

    private String gender;
}
