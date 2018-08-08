package com.xuanwu.xtion.common.rpc.fallback;

import com.xuanwu.xtion.common.entity.MessageInfo;
import com.xuanwu.xtion.common.response.ResponseObj;
import com.xuanwu.xtion.common.rpc.MessageServiceRpc;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MessageServiceFallback extends DefaultFallback implements MessageServiceRpc {
    @Override
    public ResponseObj<List<MessageInfo>> send(String message) {
        // catch exception
        recordLog();
        return null;
    }
}
