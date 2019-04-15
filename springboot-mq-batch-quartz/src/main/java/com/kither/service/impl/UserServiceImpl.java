package com.kither.service.impl;

import com.kither.bean.User;
import com.kither.mapper.UserMapper;
import com.kither.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> findAll() {
        List<User> users = userMapper.findAll();
        if (users == null || users.size() == 0) {
            return new ArrayList<>();
        }
        return users;
    }

    @Override
    public User find(Integer id) {
        User user = userMapper.find(id);
        return user == null ? new User() : user;
    }

    @Override
    public void add(User user) {
        userMapper.add(user);
    }
}
