package com.xuanwu.xtion.common.exception;

public enum AppErrorCode {

    JSON_PARSE_ERROR(1005, "json解析错误"),
    AUTHORITY_ERROR(1004, "验证失败"),
    INITIAL_ERROR(1003, "初始化失败"),

    BUSINESS_ERROR(20001, "业务运算错误"),
    PARAMS_ERROR(20002, "参数错误");

    private final int errorCode;

    private final String errorMsg;

    private AppErrorCode(int errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}
