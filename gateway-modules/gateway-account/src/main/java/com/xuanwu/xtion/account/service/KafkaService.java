package com.xuanwu.xtion.account.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;

//@EnableBinding(Source.class)
public class KafkaService {

    @Autowired
    private Source source;

    public void sendMsg(String message) {
        try {
            source.output().send(MessageBuilder.withPayload(message).build());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
