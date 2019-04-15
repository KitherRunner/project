package com.kither.service.impl;

import com.kither.bean.Student;
import com.kither.mapper.StudentMapper;
import com.kither.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public List<Student> findAll() {
        List<Student> students = studentMapper.findAll();
        if (students == null || students.size() == 0) {
            return new ArrayList<>();
        }
        return students;
    }

    @Override
    public Student find(Integer id) {
        Student student = studentMapper.find(id);
        return student == null ? new Student() : student;
    }

    @Override
    public void add(Student student) {
        studentMapper.add(student);
    }
}
