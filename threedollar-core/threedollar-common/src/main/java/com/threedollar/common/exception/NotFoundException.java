package com.threedollar.common.exception;

public class NotFoundException extends BaseException {

    private static final ErrorCode DEFAULT_ERROR_CODE = ErrorCode.E404_NOT_FOUND;

    public NotFoundException(String message) {
        super(message, DEFAULT_ERROR_CODE);
    }

    public NotFoundException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }

    public NotFoundException(String message, ErrorCode errorCode, Throwable cause) {
        super(message, errorCode, cause);
    }

}
