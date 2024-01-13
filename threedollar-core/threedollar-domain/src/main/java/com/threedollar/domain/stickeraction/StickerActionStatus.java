package com.threedollar.domain.stickeraction;

import lombok.Getter;

@Getter
public enum StickerActionStatus {

    ACTIVE("활성화"),
    DELETED("삭제됨"),
    ;

    private final String description;

    StickerActionStatus(String description) {
        this.description = description;
    }

}
