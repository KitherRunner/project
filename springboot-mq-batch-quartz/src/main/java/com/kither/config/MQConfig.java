package com.kither.config;

import com.kither.mq.Customer;
import com.kither.mq.ManulalCustomer;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class MQConfig {

    @Value("${spring.rabbitmq.host}")
    private String host;

    @Value("${spring.rabbitmq.port}")
    private int port;

    @Value("${spring.rabbitmq.username}")
    private String username;

    @Value("${spring.rabbitmq.password}")
    private String password;

    // 点播模式消费者
    @Autowired
    private Customer customer;

    //广播/订阅模式消费者
    @Autowired
    private ManulalCustomer manulalCustomer;

    @Bean
    // 使用下面这个注解注入host等值报错：Error:java: java.lang.StackOverflowError
    // @ConfigurationProperties(prefix = "spring.rabbitmq") // 注入host，port，username，password
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(host, port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        // 如果想要使用回调机制，需要设置此属性
        connectionFactory.setPublisherConfirms(true); // 设置成功时回调
        connectionFactory.setPublisherReturns(true); // 设置失败时回调
        return connectionFactory;
    }

    // 设置rabbitTemplate，用于接收以及发送消息
    // 类型必须是 prototype(原型)
    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setEncoding("UTF-8");
        rabbitTemplate.setConnectionFactory(connectionFactory);
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter()); // 设置使用json格式
        return rabbitTemplate;
    }

    // 设置RabbitAdmin，通过admin可以使用配置生成转换器和队列机器绑定关系
    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

    // 设置点播转换器
    @Bean
    public Exchange directExchange() {
        // 设置一对一形式的转换器
        return new DirectExchange("kitherExhange", true, false);
    }

    // 设置队列
    @Bean
    public Queue queue() {
        return new Queue("kitherQueue");
    }

    // 消息监听类，提供监听获取mq发送的消息，并执行监听类里的 onMessage 方法来消费消息
    @Bean
    public MessageListenerContainer messageListenerContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer(connectionFactory);
        simpleMessageListenerContainer.setMessageListener(customer); // 设置实际的监听类(消息的消费类)
        simpleMessageListenerContainer.setAcknowledgeMode(AcknowledgeMode.AUTO); // 设置自动确认消息消费成功，自动删除队列中的消息
        simpleMessageListenerContainer.setQueueNames("kitherQueue"); // 设置监听的队列
        return simpleMessageListenerContainer;
    }

    // 设置转换器和队列的绑定关系
    @Bean
    public Binding binding() {
        // 通过指定路由key关联转换器和队列(一对一需要发送的routingKey与此处设置的完全一致)
        return BindingBuilder.bind(queue()).to(directExchange()).with("sendDirect").noargs();
    }

    // 设置与手动ack队列绑定的转换器(广播/订阅模式)
    @Bean
    public Exchange manualTopicExchange() {
        // 设置广播/订阅形式的转换器
        return new TopicExchange("manualExchange", true, false);
    }

    // 设置手动ack的队列
    @Bean
    public Queue manualQueue() {
        return new Queue("manualQueue");
    }


    // 监听获取mq发送的消息
    @Bean
    public MessageListenerContainer topicMessageListenerContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer(connectionFactory);
        simpleMessageListenerContainer.setMessageListener(manulalCustomer); // 设置实际的监听类(消息的消费类)
        simpleMessageListenerContainer.setAcknowledgeMode(AcknowledgeMode.MANUAL); // 设置手动确认消息消费成功，手动确认后删除队列中的消息
        simpleMessageListenerContainer.setQueueNames("manualQueue"); // 设置监听的队列
        return simpleMessageListenerContainer;
    }


    // 设置手动应答的转换器与队列的绑定关系
    @Bean
    public Binding manualBinding() {
        // # 匹配一个或多个单词
        return BindingBuilder.bind(manualQueue()).to(manualTopicExchange()).with("manual.#").noargs();
    }
}
