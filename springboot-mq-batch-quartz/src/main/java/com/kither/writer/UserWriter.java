package com.kither.writer;

import com.kither.bean.Student;
import com.kither.service.StudentService;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("userWriter")
public class UserWriter implements ItemWriter<Student> {

    @Autowired
    private StudentService studentService;

    @Override
    public void write(List<? extends Student> list) throws Exception {
        for (Student student : list) {
            studentService.add(student);
        }
    }
}
