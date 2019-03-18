package com.kither.service;

import com.kither.pojo.Student;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentService {
    List<Student> findAll();

    Student find(Integer id);

    void add(Student student);
}
