package com.example.dietAssistant.service.impl;

import com.example.dietAssistant.JWT.JWTUtil;
import com.example.dietAssistant.dto.User;
import com.example.dietAssistant.dto.UserDTO;
import com.example.dietAssistant.dto.UserVO;
import com.example.dietAssistant.mapper.UserMapper;
import com.example.dietAssistant.properties.JwtProperties;
import com.example.dietAssistant.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    //配置jwt熟悉的bean
    @Autowired
    private JwtProperties jwtProperties;

    //添加用户
    @Override
    public Boolean addUser(UserDTO userDTO) {
        userMapper.addUser(userDTO);
        return Boolean.TRUE;
    }

    //用户登录
    @Override
    public UserVO login(UserDTO userDTO) {
        UserVO userVO = new UserVO();
        User user = userMapper.getByUserName(userDTO.getUsername());
        BeanUtils.copyProperties(user, userVO);
        if(user == null || !userVO.getPassword().equals(userDTO.getPassword()))
        {
            userVO.setJwt("");
            return userVO;
        }
        Map<String, Object> map = new HashMap<>();
        map.put(jwtProperties.getUserid(), user.getId());

        userVO.setJwt(JWTUtil.creatJWT(map, jwtProperties.getTtl(), jwtProperties.getSecretkey()));
        return userVO;
    }
}
