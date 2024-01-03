package com.threedollar.domain.sticker;

import lombok.Getter;

@Getter
public enum StickerStatus {

    ACTIVE("활성화"),
    DELETED("삭제됨"),
    ;

    private final String description;

    StickerStatus(String description) {
        this.description = description;
    }

}
