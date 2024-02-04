package com.threedollar.domain.apikey;

public enum ApiKeyStatus {

    ACTIVE("활성화된 키"),
    PAUSED("중지된 키"),
    DELETED("삭제된 키"),
    ;

    ApiKeyStatus(String description) {
    }

}
