package com.xuanwu.xtion.message.rest;

import com.xuanwu.xtion.common.entity.MessageInfo;
import com.xuanwu.xtion.common.response.ResponseObj;
import com.xuanwu.xtion.common.response.RestHelper;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
public class MessageRestController {

    @RequestMapping(value = "/send", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseObj> send(@RequestBody String message) {
        MessageInfo messageInfo = new MessageInfo();
        messageInfo.setId(UUID.randomUUID().toString());
        messageInfo.setContent(message);
        List<MessageInfo> infos = new ArrayList<MessageInfo>();
        return RestHelper.success(infos);
    }

}
