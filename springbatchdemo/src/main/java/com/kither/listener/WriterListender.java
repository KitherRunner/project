package com.kither.listener;

import com.kither.pojo.Student;
import org.springframework.batch.core.ItemWriteListener;

import java.util.List;

public class WriterListender implements ItemWriteListener<Student> {
    @Override
    public void beforeWrite(List<? extends Student> list) {
        System.out.println("写数据前： ");
        list.stream().forEach(student -> System.out.println(student));
    }

    @Override
    public void afterWrite(List<? extends Student> list) {
        System.out.println("写数据后： ");
        list.stream().forEach(student -> System.out.println(student));
    }

    @Override
    public void onWriteError(Exception e, List<? extends Student> list) {
        System.out.println("写数据异常： " + e.getMessage());
        list.stream().forEach(student -> System.out.println(student));
    }
}
