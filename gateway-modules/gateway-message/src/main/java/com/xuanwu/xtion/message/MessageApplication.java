package com.xuanwu.xtion.message;

import com.xuanwu.xtion.common.config.ControllerAopConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@EnableEurekaClient
@SpringBootApplication
@EnableAspectJAutoProxy
@ComponentScan("com.xuanwu.xtion")
@EnableFeignClients("com.xuanwu.xtion.common.rpc")
public class MessageApplication {

    @Bean ControllerAopConfig injectControllerAop() { return new ControllerAopConfig(); }

    public static void main(String... args) {
        SpringApplication.run(MessageApplication.class, args);
    }

}
