package com.xuanwu.xtion.common.response;

public class ResponseObj {
    private int status;
    private Object data;
    public ResponseObj(int status) {
        this.status = status;
    }
    public ResponseObj(int status, Object data) {
        this.status = status;
        this.data =data;
    }
}
