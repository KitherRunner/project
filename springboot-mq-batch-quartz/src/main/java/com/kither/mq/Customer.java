package com.kither.mq;

import com.google.gson.Gson;
import com.kither.bean.User;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// 消费消息需要实现MessageListener类实现提供监听获取消息
@Component
public class Customer implements MessageListener {

    @Override
    public void onMessage(Message message) {
        try {
            String msg = new String(message.getBody(), "UTF-8");
            System.out.println("消费消息成功： msg = " + msg + " ,user = " + new Gson().fromJson(msg, User.class));
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("消费消息失败");
        }

    }
}
