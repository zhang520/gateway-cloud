package com.xuanwu.xtion.message.service.impl;

import com.xuanwu.xtion.common.entity.InstantMessage;
import com.xuanwu.xtion.message.rabbitmq.RabbitRoutingTemplate;
import com.xuanwu.xtion.message.rabbitmq.config.RabbitConfig;
import com.xuanwu.xtion.message.service.InstantMessageService;
import com.xuanwu.xtion.message.service.RabbitMQService;
import org.springframework.amqp.rabbit.core.RabbitOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstantMessageServiceImpl implements InstantMessageService {

    @Autowired
    private RabbitMQService rabbitMQService;

    @Autowired
    private RabbitRoutingTemplate rabbitRoutingTemplate;

    @Override
    public boolean send(InstantMessage message, int user) {
        RabbitConfig rabbitConfig = rabbitMQService.getRabbitConfig(user);
        RabbitOperations rabbitOperations = rabbitRoutingTemplate.getRabbitOperations(rabbitConfig);
//        rabbitOperations.send();
    }

    @Override
    public List<Integer> sendBatch(InstantMessage message, List<Integer> users) {
        return users;
    }
}
