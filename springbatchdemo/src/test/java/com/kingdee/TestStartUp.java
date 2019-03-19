package com.kingdee;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Arrays;

@ContextConfiguration("classpath:spring/*.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@EnableWebMvc
public class TestStartUp {

    @Test
    public void testStartUp() {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/*.xml");
        Arrays.stream(context.getBeanDefinitionNames()).forEach(name -> System.out.println(name));
    }
}
