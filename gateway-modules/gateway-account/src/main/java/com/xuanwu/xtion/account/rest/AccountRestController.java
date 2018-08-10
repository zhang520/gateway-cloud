package com.xuanwu.xtion.account.rest;

import com.xuanwu.xtion.account.service.KafkaService;
import com.xuanwu.xtion.common.entity.MessageInfo;
import com.xuanwu.xtion.common.response.ResponseObj;
import com.xuanwu.xtion.common.response.RestHelper;
import com.xuanwu.xtion.common.rpc.MessageServiceRpc;
import com.xuanwu.xtion.common.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AccountRestController {

    @Autowired
    private MessageServiceRpc messageServiceRpc;

    @Autowired
    private KafkaService kafkaService;

    @ResponseBody
    @RequestMapping(value = "/get/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseObj> getUser(@PathVariable String userId) {
        return RestHelper.success(userId);
    }

    @RequestMapping(value = "/say", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseObj> say() {
        ResponseObj<List<MessageInfo>> response = messageServiceRpc.send("hello");
        List<MessageInfo> infos = response == null ? null : response.getData();
        kafkaService.sendMsg("hello");
        return RestHelper.success(JsonUtil.getJson(infos));
    }
}
