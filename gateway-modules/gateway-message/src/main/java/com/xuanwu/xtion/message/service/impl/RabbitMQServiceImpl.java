package com.xuanwu.xtion.message.service.impl;

import com.xuanwu.xtion.common.cache.Cache;
import com.xuanwu.xtion.common.constant.CacheConstant;
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

    @Autowired
    private Cache cache;

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
        RabbitConfig rabbitConfig = (RabbitConfig) cache.get(CacheConstant.RABBIT_CONFIG_CACHE_KEY.value(), String.valueOf(user));
        return rabbitConfig == null ? this.getDefaultRabbitConfig() : rabbitConfig;
    }

    @Override
    public RabbitConfig getAdminRabbitConfig(int user) {
        RabbitConfig rabbitConfig = this.getRabbitConfig(user);
        if (rabbitConfig != defaultConfig) {
            rabbitConfig = (RabbitConfig) cache.get(CacheConstant.RABBIT_CONFIG_CACHE_KEY.value(), new StringBuilder().append(rabbitConfig.getHost()).append(rabbitConfig.getPort())
                    .append(rabbitConfig.getVirtualHost()).toString());
        } else {

        }
        return rabbitConfig;
    }

    @Override
    public boolean saveRabbitConfig(int user, RabbitConfig rabbitConfig) {
        // save db.
        cache.put(CacheConstant.RABBIT_CONFIG_CACHE_KEY.value(), String.valueOf(user), rabbitConfig);
        return true;
    }

    @Override
    public boolean declareExchange(ExchangeConfig exchangeConfig, RabbitConfig rabbitConfig) {
        AmqpAdmin amqpAdmin = rabbitRoutingAdminTemplate.getAmqpAdmin(rabbitConfig);
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
        RabbitConfig adminConfig = this.getAdminRabbitConfig(user);
        return declareExchange(exchangeConfig, adminConfig);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        defaultConfig = new RabbitConfig(host, port, username, password, virtualHost);
        System.out.println(defaultConfig.toString());
    }
}
