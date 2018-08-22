package com.xuanwu.xtion.zipkin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.sleuth.zipkin.stream.EnableZipkinStreamServer;

@EnableAutoConfiguration
@SpringBootApplication
@EnableZipkinStreamServer
public class ZipkinApplication {

    public static void main(String... args) {
        SpringApplication.run(ZipkinApplication.class, args);
    }

}
