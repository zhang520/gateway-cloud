package com.xuanwu.xtion.common.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class RestHelper {

    public static ResponseEntity<ResponseObj> success(Object data) {
        return ResponseEntity.ok(new ResponseObj(1, data));
    }

    public static ResponseEntity<ResponseObj> success() {
        return success(null);
    }

    public static ResponseEntity<ResponseObj> failure(HttpStatus status, Object data) {
        return new ResponseEntity<ResponseObj>(new ResponseObj(0, data), status);
    }

    public static ResponseEntity<ResponseObj> failure(HttpStatus status) {
        return failure(status, null);
    }

    public static ResponseEntity<ResponseObj> failure(Object data) {
        return failure(HttpStatus.INTERNAL_SERVER_ERROR, data);
    }

    public static ResponseEntity<ResponseObj> failure() {
        return failure(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
