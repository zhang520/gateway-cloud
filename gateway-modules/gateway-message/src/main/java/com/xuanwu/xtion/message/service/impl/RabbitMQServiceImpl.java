package com.xuanwu.xtion.message.service.impl;

import com.xuanwu.xtion.message.rabbitmq.RabbitRoutingAdminTemplate;
import com.xuanwu.xtion.message.rabbitmq.RabbitRoutingTemplate;
import com.xuanwu.xtion.message.rabbitmq.config.ExchangeConfig;
import com.xuanwu.xtion.message.rabbitmq.config.RabbitConfig;
import com.xuanwu.xtion.message.service.RabbitMQService;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQServiceImpl implements RabbitMQService, InitializingBean {

    @Value("${rabbit.host}")
    private String host;

    @Value("${rabbit.port}")
    private int port;

    @Value("${rabbit.username}")
    private String username;

    @Value("${rabbit.password}")
    private String password;

    @Value("${rabbit.virtual-host}")
    private String virtualHost;

    private volatile RabbitConfig defaultConfig;

    @Autowired
    private RabbitRoutingTemplate rabbitRoutingTemplate;

    @Autowired
    private RabbitRoutingAdminTemplate rabbitRoutingAdminTemplate;

    @Override
    public RabbitConfig getDefaultRabbitConfig() {
        return defaultConfig;
    }

    @Override
    public boolean declareExchange(ExchangeConfig exchangeConfig) {
        return declareExchange(exchangeConfig, defaultConfig);
    }

    @Override
    public RabbitConfig getRabbitConfig(int user) {
        return null;
    }

    @Override
    public RabbitConfig getAdminRabbitConfig(int user) {
        return null;
    }

    @Override
    public boolean declareExchange(ExchangeConfig exchangeConfig, RabbitConfig rabbitConfig) {
        AmqpAdmin amqpAdmin = rabbitRoutingAdminTemplate.getAmqpAdmin(defaultConfig);
        Exchange exchange = null;
        switch (exchangeConfig.getType()) {
            case ExchangeTypes.TOPIC:
                exchange = new TopicExchange(exchangeConfig.getName());
            case ExchangeTypes.DIRECT:
                exchange = new DirectExchange(exchangeConfig.getName());
            case ExchangeTypes.FANOUT:
                exchange = new FanoutExchange(exchangeConfig.getName());
            case ExchangeTypes.HEADERS:
                exchange = new HeadersExchange(exchangeConfig.getName());
            default:
                exchange = new TopicExchange(exchangeConfig.getName());
        }
        amqpAdmin.declareExchange(exchange);
        return true;
    }

    @Override
    public boolean declareExchange(ExchangeConfig exchangeConfig, int user) {
        return false;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        defaultConfig = new RabbitConfig(host, port, username, password, virtualHost);
        System.out.println(defaultConfig.toString());
    }
}
