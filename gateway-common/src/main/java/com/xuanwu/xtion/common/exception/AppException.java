package com.xuanwu.xtion.common.exception;

public class AppException extends RuntimeException {

    private final int errorCode;

    public AppException(AppErrorCode appErrorCode) {
        super(appErrorCode.getErrorMsg());
        this.errorCode = appErrorCode.getErrorCode();
    }

    public AppException(AppErrorCode appErrorCode, Throwable cause) {
        super(appErrorCode.getErrorMsg(), cause);
        this.errorCode = appErrorCode.getErrorCode();
    }

    public AppException(AppErrorCode appErrorCode, String causeby) {
        super(causeby);
        this.errorCode = appErrorCode.getErrorCode();
    }

    @Override
    public Throwable fillInStackTrace() {
        return this;
    }
}
