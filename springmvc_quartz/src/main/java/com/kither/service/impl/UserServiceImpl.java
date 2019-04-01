package com.kither.service.impl;

import com.kither.bean.User;
import com.kither.mapper.UserMapper;
import com.kither.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> findAll() {
//        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        // userMapper.findAll().stream().forEach(user -> System.out.println(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) + user));
        return userMapper.findAll();
    }
}
