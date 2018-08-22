package com.xuanwu.xtion.message.rabbitmq;

import com.xuanwu.xtion.common.exception.AppErrorCode;
import com.xuanwu.xtion.common.exception.AppException;
import com.xuanwu.xtion.message.rabbitmq.config.RabbitConfig;
import com.xuanwu.xtion.message.rabbitmq.initializer.SimpleConnectionFactoryInitializer;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.SimpleRoutingConnectionFactory;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class RabbitRoutingConnectionFactory extends SimpleRoutingConnectionFactory {

    private ConnectionFactoryInitializer initializer;

    private Lock target = new ReentrantLock();

    public RabbitRoutingConnectionFactory() {
        this(new SimpleConnectionFactoryInitializer());
    }

    public RabbitRoutingConnectionFactory(ConnectionFactoryInitializer initializer) {
        this.initializer = initializer;
    }

    @Override
    protected ConnectionFactory determineTargetConnectionFactory() {
        Object lookupKey = super.determineCurrentLookupKey();
        ConnectionFactory connectionFactory = null;
        if (lookupKey != null) {
            connectionFactory = this.getTargetConnectionFactory(lookupKey);
        }
        if (connectionFactory == null) {
            connectionFactory = super.determineTargetConnectionFactory();
        }
        return connectionFactory;
    }

    @Override
    public ConnectionFactory getTargetConnectionFactory(Object key) {
        ConnectionFactory connectionFactory = super.getTargetConnectionFactory(key.toString());
        if (connectionFactory == null) {
            try {
                target.lock();
                connectionFactory = super.getTargetConnectionFactory(key.toString());
                if (connectionFactory == null) {
                    RabbitConfig rabbitConfig = (RabbitConfig) key;
                    connectionFactory = initializer.initConnectionFactory(rabbitConfig);
                    super.addTargetConnectionFactory(key.toString(), connectionFactory);
                }
            } catch (Exception e) {
                throw new AppException(AppErrorCode.INITIAL_ERROR, e);
            } finally {
                target.unlock();
            }
        }
        return connectionFactory;
    }
}
