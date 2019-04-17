package com.kither.mq;

import com.kither.bean.User;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

// 生成消息并受到应答rabbitmq消息队列，告知消息成功消费
// 要使用这种回调机制需要将Template模版的设publisherConfirms 或publisherReturns 属性设置为true，此外ConnectionFactory要配置为CachingConnectionFactory。
// 如果消息没有到exchange,则confirm回调,ack=false
// 如果消息到达exchange,则confirm回调,ack=true
// exchange到queue成功,则不回调return
// exchange到queue失败,则回调return(需设置mandatory=true,否则不回回调,消息就丢了)
@Component
public class ManualProducer implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public ManualProducer() {
        rabbitTemplate.setConfirmCallback(this); // 成功回调函数
        rabbitTemplate.setReturnCallback(this); // 失败回调函数
    }

    // 具体发生消息
    public void send(String routingKey, User user) {
        CorrelationData correlationData = new CorrelationData();
        correlationData.setId(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend("kitherExhange", routingKey, user, correlationData);
    }

    // 确认回调函数
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        String id = correlationData.getId();
        if (ack) {
            System.out.println("correlationDataId: " + id);
        } else {
            System.out.println("cause: " + cause);
        }
    }

    // 失败return回调函数
    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        System.out.println("失败回调：replyCode = " + replyCode + " replyText = " + replyText + " exchange = " + exchange + " routingKey = " + routingKey + " message = " + message.getBody());
    }
}
