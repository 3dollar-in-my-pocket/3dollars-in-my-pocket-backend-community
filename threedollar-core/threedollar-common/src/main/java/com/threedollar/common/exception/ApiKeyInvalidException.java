package com.threedollar.common.exception;

public class ApiKeyInvalidException extends BaseException {

    public ApiKeyInvalidException(String message) {
        super(message, ErrorCode.E401_INVALID_API_KEY);
    }
}
