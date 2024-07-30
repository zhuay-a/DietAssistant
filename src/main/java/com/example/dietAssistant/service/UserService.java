package com.example.dietAssistant.service;

import com.example.dietAssistant.dto.UserDTO;
import com.example.dietAssistant.dto.UserVO;

public interface UserService {
    Boolean addUser(UserDTO userDTO);

    UserVO login(UserDTO userDTO);

    void modify(UserDTO userDTO);
}
