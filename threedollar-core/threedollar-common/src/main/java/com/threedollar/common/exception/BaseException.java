package com.threedollar.common.exception;

import lombok.Getter;


@Getter
public abstract class BaseException extends RuntimeException{

    private final ErrorCode errorCode;

    protected BaseException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    protected BaseException(String message, ErrorCode errorCode, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }


}
