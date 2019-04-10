package com.kither.listener;

import com.kither.pojo.Student;
import com.kither.pojo.User;
import org.springframework.batch.core.ItemProcessListener;

public class ProcessorListener implements ItemProcessListener<User, Student> {
    @Override
    public void beforeProcess(User user) {
        System.out.println("转换前： " + user);
    }

    @Override
    public void afterProcess(User user, Student stu) {
        System.out.println("转换后： " + " user; " + user + " stu: " + stu);
    }

    @Override
    public void onProcessError(User user, Exception e) {
        System.out.println("转换报错： " + user + ", 异常： " + e.getMessage());
    }
}
