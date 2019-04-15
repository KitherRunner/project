package com.kither.processor;

import com.kither.pojo.Student;
import com.kither.pojo.User;
import org.springframework.batch.item.ItemProcessor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UserProcessor implements ItemProcessor<User, Student> {
    @Override
    public Student process(User user) throws Exception {
        Student stu = new Student();
        stu.setName(user.getName());
        stu.setGender(user.getGender());
        stu.setComment(user.getName() + " - " + LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        return stu;
    }
}
