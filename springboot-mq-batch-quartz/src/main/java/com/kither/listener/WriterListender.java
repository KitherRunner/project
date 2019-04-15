package com.kither.listener;

import com.kither.bean.User;
import org.springframework.batch.core.ItemWriteListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WriterListender implements ItemWriteListener<User> {
    @Override
    public void beforeWrite(List<? extends User> list) {
        System.out.println("写数据前： ");
        list.stream().forEach(user -> System.out.println(user));
    }

    @Override
    public void afterWrite(List<? extends User> list) {
        System.out.println("写数据后： ");
        list.stream().forEach(user -> System.out.println(user));
    }

    @Override
    public void onWriteError(Exception e, List<? extends User> list) {
        System.out.println("写数据异常： " + e.getMessage());
        list.stream().forEach(user -> System.out.println(user));
    }
}
