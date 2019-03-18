package com.kither.writer;

import com.kither.pojo.Student;
import com.kither.pojo.User;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

public class UserWriter implements ItemWriter<Student> {
    @Override
    public void write(List<? extends Student> list) throws Exception {

    }
}
