package com.threedollar.common.exception;

import lombok.Getter;

@Getter
public enum HttpStatusCode {

    BAD_REQUEST(400),
    ;

    private final int status;

    HttpStatusCode(int status) {
        this.status = status;
    }
}
