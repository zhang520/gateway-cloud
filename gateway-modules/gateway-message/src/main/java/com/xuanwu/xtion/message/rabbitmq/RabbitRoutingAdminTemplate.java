package com.xuanwu.xtion.message.rabbitmq;

import com.xuanwu.xtion.common.exception.AppErrorCode;
import com.xuanwu.xtion.common.exception.AppException;
import com.xuanwu.xtion.message.rabbitmq.config.RabbitConfig;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.rabbit.connection.SimpleResourceHolder;
import org.springframework.amqp.rabbit.core.RabbitAdmin;

import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class RabbitRoutingAdminTemplate {

    private Map<String, AmqpAdmin> amqpAdminHolder = new ConcurrentHashMap<>();

    private volatile RabbitAdmin rabbitAdmin;

    public Lock target = new ReentrantLock();

    public RabbitRoutingAdminTemplate(RabbitRoutingConnectionFactory connectionFactory) {
        this.rabbitAdmin = new RabbitAdmin(connectionFactory);
    }

    public RabbitAdmin getRabbitAdmin() {
        return rabbitAdmin;
    }

    public AmqpAdmin getAmqpAdmin(RabbitConfig rabbitConfig) {
        AmqpAdmin amqpAdmin = amqpAdminHolder.get(rabbitConfig.toString());
        if (amqpAdmin == null) {
            try {
                target.lock();
                amqpAdmin = amqpAdminHolder.get(rabbitConfig.toString());
                if (amqpAdmin == null) {
                    amqpAdmin = (AmqpAdmin) Proxy.newProxyInstance(rabbitAdmin.getClass().getClassLoader(), new Class[] { AmqpAdmin.class }, (proxy, method, args) -> {
                        SimpleResourceHolder.bind(rabbitAdmin.getRabbitTemplate().getConnectionFactory(), rabbitConfig);
                        Object result = method.invoke(rabbitAdmin, args);
                        SimpleResourceHolder.unbind(rabbitAdmin.getRabbitTemplate().getConnectionFactory());
                        return result;
                    });
                    amqpAdminHolder.put(rabbitConfig.toString(), amqpAdmin);
                }
            } catch (Exception e) {
                throw new AppException(AppErrorCode.INITIAL_ERROR, e);
            } finally {
                target.unlock();
            }
        }
        return amqpAdmin;
    }
}
