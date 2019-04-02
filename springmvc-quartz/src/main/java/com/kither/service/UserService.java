package com.kither.service;

import com.kither.bean.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    void findUsers();
}
