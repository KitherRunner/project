package com.kither.config;

import com.kither.mq.Customer;
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
import org.springframework.boot.context.properties.ConfigurationProperties;
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

    @Autowired
    private Customer customer;

    @Bean
    // 使用下面这个注解注入host等值报错：Error:java: java.lang.StackOverflowError
    // @ConfigurationProperties(prefix = "spring.rabbitmq") // 注入host，port，username，password
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(host, port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        return connectionFactory;
    }

    // 设置rabbitTemplate，用于接收以及发送消息
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

    // 设置转换器
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
        simpleMessageListenerContainer.setAcknowledgeMode(AcknowledgeMode.AUTO); // 设置自动确认消息消费成功
        simpleMessageListenerContainer.setQueueNames("kitherQueue"); // 设置监听的队列
        return simpleMessageListenerContainer;
    }

    // 设置转换器和队列的绑定关系
    @Bean
    public Binding binding() {
        // 通过路由key关联转换器和队列(一对一需要发送的routingKey与此处设置的完全一致)
        return BindingBuilder.bind(queue()).to(directExchange()).with("sendDirect").noargs();
    }
}
