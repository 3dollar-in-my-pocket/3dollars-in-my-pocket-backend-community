package com.threedollar.common.enums;

import lombok.Getter;

@Getter
public enum HttpStatusCode {

    BAD_REQUEST(400),
    NOT_FOUND(404),
    ;

    private final int status;

    HttpStatusCode(int status) {
        this.status = status;
    }
}
