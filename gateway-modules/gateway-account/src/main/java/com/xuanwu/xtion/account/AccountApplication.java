package com.xuanwu.xtion.account;

import com.xuanwu.xtion.common.config.JerseyConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@EnableEurekaClient
@SpringBootApplication
public class AccountApplication {

    @Bean
    public JerseyConfig jerseyConfig() { return new JerseyConfig().packages("com.xuanwu.xtion.account.rest"); }

    public static void main(String... args) {
        SpringApplication.run(AccountApplication.class, args);
    }

}
