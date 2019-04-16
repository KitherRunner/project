package com.kither.mq;

import com.google.gson.Gson;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// 生产消息
@Component
public class Producer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(String routingKey, Object message) {
        rabbitTemplate.convertAndSend("kitherExhange", routingKey, message);
        System.out.println("发送消息： routingKey = " + routingKey + " ,message = " + new Gson().toJson(message));
    }

}
