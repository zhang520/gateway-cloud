package com.xuanwu.xtion.message.service;

import com.xuanwu.xtion.message.rabbitmq.config.ExchangeConfig;
import com.xuanwu.xtion.message.rabbitmq.config.RabbitConfig;

public interface RabbitMQService {

    /**
     * get default rabbit config.
     * @return
     */
    RabbitConfig getDefaultRabbitConfig();

    /**
     * get rabbit config by user's id.
     * @param user
     * @return
     */
    RabbitConfig getRabbitConfig(int user);

    /**
     * get admin rabbit config of user's rabbit broker.
     * @param user
     * @return
     */
    RabbitConfig getAdminRabbitConfig(int user);

    /**
     * declare a exchange with default admin rabbit config.
     * @param exchangeConfig
     * @return
     */
    boolean declareExchange(ExchangeConfig exchangeConfig);

    /**
     * declare a exchange with the admin rabbit config.
     * @param exchangeConfig
     * @param rabbitConfig
     * @return
     */
    boolean declareExchange(ExchangeConfig exchangeConfig, RabbitConfig rabbitConfig);

    /**
     * firt find the admin config of this user's rabbit broker, then declare a exchange with this admin rabbit config which find by the user just soon.
     * @param exchangeConfig
     * @param user
     * @return
     */
    boolean declareExchange(ExchangeConfig exchangeConfig, int user);

    /**
     * save this user's special rabbit config info
     * @param user
     * @param rabbitConfig
     * @return
     */
    boolean saveRabbitConfig(int user, RabbitConfig rabbitConfig);
}
