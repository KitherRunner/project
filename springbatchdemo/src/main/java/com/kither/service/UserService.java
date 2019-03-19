package com.kither.service;

import com.kither.pojo.User;

import java.util.List;


public interface UserService {

    List<User> findAll();

    User find(Integer id);

    void add(User user);
}
