package com.kither.springbootmqbatchquartz;

import com.kither.bean.User;
import com.kither.mq.Producer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootMqBatchQuartzApplicationTests {

    @Autowired
    private Producer producer;

    @Test
    public void contextLoads() {
        producer.send("sendDirect", new User(1, "张三", "男"));
    }

}
