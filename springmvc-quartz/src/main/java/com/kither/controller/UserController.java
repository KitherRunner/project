package com.kither.controller;

import com.kither.bean.User;
import com.kither.service.UserService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@MapperScan("com.kither.mapper")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("users")
    public List<User> findAll() {
        return userService.findAll();
    }
}
