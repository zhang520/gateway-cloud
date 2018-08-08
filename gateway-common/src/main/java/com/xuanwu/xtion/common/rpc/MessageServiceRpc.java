package com.xuanwu.xtion.common.rpc;

import com.xuanwu.xtion.common.entity.MessageInfo;
import com.xuanwu.xtion.common.response.ResponseObj;
import com.xuanwu.xtion.common.rpc.fallback.MessageServiceFallback;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value = "gateway-message", fallback = MessageServiceFallback.class)
public interface MessageServiceRpc {
    @RequestMapping(value = "/send", method = RequestMethod.PUT)
    public ResponseObj<List<MessageInfo>> send(String message);
}
