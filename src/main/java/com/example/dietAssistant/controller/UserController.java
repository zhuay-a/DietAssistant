package com.example.dietAssistant.controller;

import com.example.dietAssistant.dto.UserDTO;
import com.example.dietAssistant.dto.UserVO;
import com.example.dietAssistant.result.Result;
import com.example.dietAssistant.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/User")
@Controller
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;
    //新增员工
    @PostMapping("/add")
    @ResponseBody
    public Result<String> addUser(@RequestBody UserDTO userDTO) {
        log.info("新增用户:{}", userDTO);
        System.out.println("111");
        userService.addUser(userDTO);
        return Result.success("创建用户成功");
    }

    //用户登录
    @GetMapping("/login")
    @ResponseBody
    public Result<Object> userLogin(@RequestBody UserDTO userDTO) {
        log.info("用户登录:{}", userDTO);
        UserVO userVO = userService.login(userDTO);

        if(userVO.getJwt().equals(""))
            return Result.fail("登录失败").data(userVO);
        else
            return Result.success("登录成功").data(userVO);
    }

    @GetMapping("/test")
    @ResponseBody
    public String test() {
        return "1111";
    }

    //修改用户信息
    @PutMapping("/modify")
    @ResponseBody
    public Result<String> modifyUser(@RequestBody UserDTO userDTO) {
        userService.modify(userDTO);
        return Result.success("更新成功");
    }
}
