package com.xuanwu.xtion.message;

import com.xuanwu.xtion.common.config.ControllerAopConfig;
import com.xuanwu.xtion.message.rabbitmq.RabbitRoutingAdminTemplate;
import com.xuanwu.xtion.message.rabbitmq.RabbitRoutingConnectionFactory;
import com.xuanwu.xtion.message.rabbitmq.RabbitRoutingTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableEurekaClient
@SpringBootApplication
@EnableAspectJAutoProxy
@ComponentScan("com.xuanwu.xtion")
@EnableFeignClients("com.xuanwu.xtion.common.rpc")
public class MessageApplication {

    @Bean
    RabbitRoutingConnectionFactory injectRabbitRoutingConnectionFactory() { return new RabbitRoutingConnectionFactory(); }

    @Bean
    RabbitRoutingAdminTemplate injectRabbitRoutingAdminTemplate(RabbitRoutingConnectionFactory connectionFactory) { return new RabbitRoutingAdminTemplate(connectionFactory); }

    @Bean
    RabbitRoutingTemplate injectRabbitRoutingTemplate(RabbitRoutingConnectionFactory connectionFactory) { return new RabbitRoutingTemplate(connectionFactory); }

    @Bean ControllerAopConfig injectControllerAop() { return new ControllerAopConfig(); }

    public static void main(String... args) {
        SpringApplication.run(MessageApplication.class, args);
    }

}
