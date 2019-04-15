package com.kither.listener;

import com.kither.bean.User;
import org.springframework.batch.core.ItemReadListener;
import org.springframework.stereotype.Component;

@Component
public class ReaderListender implements ItemReadListener<User> {
    @Override
    public void beforeRead() {
        System.out.println("读取前");
    }

    @Override
    public void afterRead(User user) {
        System.out.println("读取后： " + user);
    }

    @Override
    public void onReadError(Exception e) {
        System.out.println("读取异常： " + e.getMessage());
    }
}
