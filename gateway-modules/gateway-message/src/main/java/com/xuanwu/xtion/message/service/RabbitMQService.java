package com.xuanwu.xtion.message.service;

import com.xuanwu.xtion.message.rabbitmq.config.ExchangeConfig;
import com.xuanwu.xtion.message.rabbitmq.config.RabbitConfig;

public interface RabbitMQService {

    RabbitConfig getDefaultRabbitConfig();

    RabbitConfig getRabbitConfig(int user);

    RabbitConfig getAdminRabbitConfig(int user);

    boolean declareExchange(ExchangeConfig exchangeConfig);

    boolean declareExchange(ExchangeConfig exchangeConfig, RabbitConfig rabbitConfig);

    boolean declareExchange(ExchangeConfig exchangeConfig, int user);
}
