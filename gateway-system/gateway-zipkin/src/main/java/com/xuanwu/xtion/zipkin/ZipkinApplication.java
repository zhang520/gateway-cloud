package com.xuanwu.xtion.zipkin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import zipkin.server.internal.EnableZipkinServer;

@EnableAutoConfiguration
@SpringBootApplication
@EnableZipkinServer
public class ZipkinApplication {

    public static void main(String... args) {
        SpringApplication.run(ZipkinApplication.class, args);
    }

}
