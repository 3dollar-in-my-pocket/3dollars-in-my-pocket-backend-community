package com.threedollar.domain.sticker;

import lombok.Getter;

@Getter
public enum StickerType {

    POLL("투표"),

    ;
    private final String description;

    StickerType(String description) {
        this.description = description;
    }

}
