package com.kither.controller;

import com.kither.pojo.User;
import com.kither.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(value = "userController", tags = "user", description = "用户controller")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "users", produces = "application/json;charset=utf-8")
    @ApiOperation(value = "findAll", tags = "user", notes = "查询所有user", httpMethod = "GET")
    public List<User> findAll() {
        return userService.findAll();
    }

    @GetMapping(value = "/user/{id}", produces = "application/json;charset=utf-8")
    @ApiOperation(value = "find", tags = "user", notes = "根据指定id查询student", httpMethod = "GET")
    @ApiImplicitParam(name = "id", value = "编号", paramType = "path", dataType = "int")
    public User find(@PathVariable("id") Integer id) {
        return userService.find(id);
    }
}
