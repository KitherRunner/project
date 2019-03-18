package com.kither.listener;

import com.kither.pojo.User;
import org.springframework.batch.core.ItemProcessListener;

public class ProcessorListener implements ItemProcessListener<User, User> {
    @Override
    public void beforeProcess(User user) {
        System.out.println("转换前： " + user);
    }

    @Override
    public void afterProcess(User user, User user2) {
        System.out.println("转换后： " + user);
    }

    @Override
    public void onProcessError(User user, Exception e) {
        System.out.println("转换报错： " + user + ", 异常： " + e.getMessage());
    }
}
