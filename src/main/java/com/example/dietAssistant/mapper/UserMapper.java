package com.example.dietAssistant.mapper;

import com.example.dietAssistant.dto.User;
import com.example.dietAssistant.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
public interface UserMapper {
    void addUser(UserDTO userDTO);

    @Select("select * from users where id = #{userId}")
    User getById(Integer userId);

    @Select("select * from users where username = #{username}")
    User getByUserName(String username);
}
