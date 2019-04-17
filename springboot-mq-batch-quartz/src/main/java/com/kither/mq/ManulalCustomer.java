package com.kither.mq;

import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

// 手动消费消息，实现 ChannelAwareMessageListener 来实现手动应答消息队列
@Component
public class ManulalCustomer implements ChannelAwareMessageListener {

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        // 消费消息
        System.out.println("手动消费消息： " + new String(message.getBody(), "UTF-8"));

        // 应答消息队列，使得消息队列确认后删除对应消息
        // 如果为true，确认之前接受到的消息；如果为false，只确认当前消息。如果为true就表示连续取得多条消息才发会确认，和计算机网络的中tcp协议接受分组的累积确认十分相似，能够提高效率
        // 确认了后队列才会删除对应的消息
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }
}
