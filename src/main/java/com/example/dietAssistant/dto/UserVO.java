package com.example.dietAssistant.dto;

import lombok.Data;

@Data
public class UserVO {

    private Integer id;

    private String username;

    private String password;

    private String jwt;

}
