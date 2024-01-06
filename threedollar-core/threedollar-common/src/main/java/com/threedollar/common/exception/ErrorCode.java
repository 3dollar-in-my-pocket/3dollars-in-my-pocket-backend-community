package com.threedollar.common.exception;

public enum ErrorCode {
    E400_INVALID(HttpStatusCode.BAD_REQUEST, "invalid_request", "잘못된 요청입니다"),
    E404_NOT_FOUND(HttpStatusCode.NOT_FOUND, "not_found", "찾을 수 없습니다"),
    ;

    private final HttpStatusCode httpStatusCode;

    private final String code;

    private final String message;

    ErrorCode(HttpStatusCode httpStatusCode, String code, String message) {
        this.httpStatusCode = httpStatusCode;
        this.code = code;
        this.message = message;
    }

    public int getStatus() {
        return httpStatusCode.getStatus();
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }


}
