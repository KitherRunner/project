package com.kither.processor;

import com.kither.bean.Student;
import com.kither.bean.User;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component("userProcessor")
public class UserProcessor implements ItemProcessor<User, Student> {
    @Override
    public Student process(User user) throws Exception {
        Student student = new Student();
        student.setName("studnt - " + user.getName());
        student.setGender(user.getGender());
        student.setComment(user.getName() + " - " + LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        return student;
    }
}
