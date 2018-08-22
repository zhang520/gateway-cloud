package com.xuanwu.xtion.message.rabbitmq.initializer;

import com.xuanwu.xtion.message.rabbitmq.ConnectionFactoryInitializer;
import com.xuanwu.xtion.message.rabbitmq.config.RabbitConfig;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;

public class SimpleConnectionFactoryInitializer implements ConnectionFactoryInitializer {
    @Override
    public ConnectionFactory initConnectionFactory(RabbitConfig rabbitConfig) {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost(rabbitConfig.getHost());
        connectionFactory.setPort(rabbitConfig.getPort().intValue());
        connectionFactory.setUsername(rabbitConfig.getUsername());
        connectionFactory.setPassword(rabbitConfig.getPassword());
        connectionFactory.setVirtualHost(rabbitConfig.getVirtualHost());
        connectionFactory.setPublisherConfirms(rabbitConfig.getPublisherConfirms() == null ? false : rabbitConfig.getPublisherConfirms().booleanValue());
        return connectionFactory;
    }
}
