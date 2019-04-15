package com.kither.service;

import com.kither.bean.Student;

import java.util.List;


public interface StudentService {
    List<Student> findAll();

    Student find(Integer id);

    void add(Student student);
}
