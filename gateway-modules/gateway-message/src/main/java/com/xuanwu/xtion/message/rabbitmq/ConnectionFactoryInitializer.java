package com.xuanwu.xtion.message.rabbitmq;

import com.xuanwu.xtion.message.rabbitmq.config.RabbitConfig;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;

public interface ConnectionFactoryInitializer {

    ConnectionFactory initConnectionFactory(RabbitConfig rabbitConfig);

}
