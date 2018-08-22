package com.xuanwu.xtion.message.service;

import com.xuanwu.xtion.common.entity.InstantMessage;

import java.util.List;

public interface InstantMessageService {

    /**
     * send one message to user
     * @param message
     * @param user user's id
     * @return
     */
    boolean send(InstantMessage message, int user);

    /**
     * send batch.
     * @param message
     * @param users some of user's id
     * @return
     */
    List<Integer> sendBatch(InstantMessage message, List<Integer> users);
}
