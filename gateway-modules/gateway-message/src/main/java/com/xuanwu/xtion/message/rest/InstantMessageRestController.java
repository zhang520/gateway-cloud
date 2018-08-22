package com.xuanwu.xtion.message.rest;

import com.xuanwu.xtion.common.entity.InstantMessage;
import com.xuanwu.xtion.common.response.ResponseObj;
import com.xuanwu.xtion.common.response.RestHelper;
import com.xuanwu.xtion.message.service.InstantMessageService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/im/")
public class InstantMessageRestController {

    private InstantMessageService instantMessageService;

    @ResponseBody
    @RequestMapping(value = "send", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseObj> send(@RequestBody InstantMessage message, @RequestBody int user) {
        return RestHelper.success(instantMessageService.send(message, user));
    }

}
