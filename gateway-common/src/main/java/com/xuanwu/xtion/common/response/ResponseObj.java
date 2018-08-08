package com.xuanwu.xtion.common.response;

public class ResponseObj<T> {
    private int status;
    private T data;
    public ResponseObj() {}
    public ResponseObj(int status, T data) {
        this.status = status;
        this.data =data;
    }

    public int getStatus() {
        return status;
    }

    public T getData() {
        return data;
    }
}
