package com.kither.springbootmqbatchquartz;

import com.kither.bean.User;
import com.kither.mq.ManualProducer;
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

    @Autowired
    private ManualProducer manualProducer;

    // 点播模式
    @Test
    public void contextLoads() {
        producer.send("sendDirect", new User(1, "张三", "男"));
    }

    // 广播/订阅模式
    @Test
    public void send() {
        manualProducer.send("manual.Topic.send", new User(2, "张三", "男"));
    }

}
