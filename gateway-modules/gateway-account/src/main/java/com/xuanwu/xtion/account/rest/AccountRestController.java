package com.xuanwu.xtion.account.rest;

import com.xuanwu.xtion.common.response.ResponseObj;
import com.xuanwu.xtion.common.response.RestHelper;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountRestController {

    @ResponseBody
    @RequestMapping(value = "/get/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseObj> getUser(@PathVariable String userId) {
        return RestHelper.success(userId);
    }

    @RequestMapping(value = "/say", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseObj> say() {
        return RestHelper.success("hello");
    }

    @ResponseBody
    @RequestMapping(value = "/hello", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseObj hello() {
        return new ResponseObj(1, "hello");
    }
}
