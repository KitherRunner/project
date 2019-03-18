package com.kither.service;

import com.kither.mapper.UserMapper;
import com.kither.pojo.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    List<User> findAll();

    User find(Integer id);

    void add(User user);
}
