package com.kither.processor;

import com.kither.pojo.Student;
import com.kither.pojo.User;
import org.springframework.batch.item.ItemProcessor;

public class UserProcessor implements ItemProcessor<User, Student> {
    @Override
    public Student process(User user) throws Exception {
        return null;
    }
}
