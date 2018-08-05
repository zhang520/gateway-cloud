package com.xuanwu.xtion.common.response;

import javax.ws.rs.core.Response;

public class RestHelper {

    public static Response success(Object data) {
        return Response.ok().entity(new ResponseObj(1, data)).build();
    }

    public static Response success() {
        return success(null);
    }

    public static Response failure(Response.Status status, Object data) {
        return Response.status(status).entity(new ResponseObj(0, data)).build();
    }

    public static Response failure(Response.Status status) {
        return failure(status, null);
    }

    public static Response failure(Object data) {
        return failure(Response.Status.INTERNAL_SERVER_ERROR, data);
    }

    public static Response failure() {
        return failure(Response.Status.INTERNAL_SERVER_ERROR);
    }
}
