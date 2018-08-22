package com.xuanwu.xtion.message.rabbitmq;

import com.xuanwu.xtion.common.exception.AppErrorCode;
import com.xuanwu.xtion.common.exception.AppException;
import com.xuanwu.xtion.message.rabbitmq.config.RabbitConfig;
import org.springframework.amqp.rabbit.connection.SimpleResourceHolder;
import org.springframework.amqp.rabbit.core.RabbitOperations;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class RabbitRoutingTemplate {

    private Map<String, RabbitOperations> rabbitOperationsHolder = new ConcurrentHashMap<String, RabbitOperations>();

    private volatile RabbitTemplate rabbitTemplate;

    private Lock target = new ReentrantLock();

    public RabbitRoutingTemplate() {
        this(new RabbitRoutingConnectionFactory());
    }

    public RabbitRoutingTemplate(RabbitRoutingConnectionFactory routingConnectionFactory) {
        rabbitTemplate = new RabbitTemplate(routingConnectionFactory);
    }

    public RabbitTemplate getRabbitTemplate() {
        return rabbitTemplate;
    }

    public RabbitOperations getRabbitOperations(final RabbitConfig rabbitConfig) {
        RabbitOperations rabbitOperations = rabbitOperationsHolder.get(rabbitConfig.toString());
        if (rabbitOperations == null) {
            try {
                target.lock();
                rabbitOperations = rabbitOperationsHolder.get(rabbitConfig.toString());
                if (rabbitOperations == null) {
                    rabbitOperations = (RabbitOperations) Proxy.newProxyInstance(rabbitTemplate.getClass().getClassLoader(), new Class[] { RabbitOperations.class }, (proxy, method, args) -> {
                        SimpleResourceHolder.bind(rabbitTemplate.getConnectionFactory(), rabbitConfig);
                        Object result = method.invoke(rabbitTemplate, args);
                        SimpleResourceHolder.unbind(rabbitTemplate.getConnectionFactory());
                        return result;
                    });
                    rabbitOperationsHolder.put(rabbitConfig.toString(), rabbitOperations);
                }
            } catch (Exception e) {
                throw new AppException(AppErrorCode.INITIAL_ERROR, e);
            } finally {
                target.unlock();
            }
        }
        return rabbitOperations;
    }
}
