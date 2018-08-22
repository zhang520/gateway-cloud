package com.xuanwu.xtion.message.service.impl;

import com.xuanwu.xtion.common.entity.InstantMessage;
import com.xuanwu.xtion.message.service.InstantMessageService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstantMessageServiceImpl implements InstantMessageService {


    @Override
    public boolean send(InstantMessage message, int user) {
        return false;
    }

    @Override
    public List<Integer> sendBatch(InstantMessage message, List<Integer> users) {
        return users;
    }
}
