package com.kither.controller;

import com.kither.bean.Student;
import com.kither.service.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@MapperScan("com.kither.mapper")
@Api(value = "StudentController", tags = "student", description = "学生controller")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping(value = "students", produces = "application/json;charset=utf-8")
    @ApiOperation(value = "findAll", tags = "student", notes = "查询所有student", httpMethod = "GET")
    public List<Student> findAll() {
        return studentService.findAll();
    }

    @GetMapping(value = "student/{id}", produces = "application/json;charset=utf-8")
    @ApiOperation(value = "find", tags = "student", notes = "根据指定id查询student", httpMethod = "GET")
    @ApiImplicitParam(name = "id", value = "编号", paramType = "path", dataType = "int")
    public Student find(@PathVariable("id") Integer id) {
        return studentService.find(id);
    }
}
