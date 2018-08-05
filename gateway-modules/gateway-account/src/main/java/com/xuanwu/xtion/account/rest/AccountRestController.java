package com.xuanwu.xtion.account.rest;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Component
@Produces(MediaType.APPLICATION_JSON)
public class AccountRestController implements InitializingBean {

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.print("xxxx");
    }

    @GET
    @Path("/get/{userId}")
    public Object getUser(@PathParam("userId") String userId) {
        return userId;
    }

}
