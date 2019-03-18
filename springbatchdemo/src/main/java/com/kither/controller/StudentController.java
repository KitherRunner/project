package com.kither.controller;

import com.kither.pojo.Student;
import com.kither.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("students")
    public List<Student> findAll() {
        return studentService.findAll();
    }

    @GetMapping("/student/{id}")
    public Student find(@PathVariable("id") Integer id) {
        return studentService.find(id);
    }
}
