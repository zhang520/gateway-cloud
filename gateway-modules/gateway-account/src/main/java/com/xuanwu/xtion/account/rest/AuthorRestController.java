package com.xuanwu.xtion.account.rest;

import com.xuanwu.xtion.common.response.ResponseObj;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class AuthorRestController {

    @RequestMapping(value = "/author", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Object author() {
        return new ResponseObj(1, "ddd");
    }

}
