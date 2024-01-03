package com.threedollar.domain.sticker;

import lombok.Getter;

@Getter
public enum StickerGroup {

    POLL("투표"),
    REVIEW("리뷰")
    ;
    private final String description;

    StickerGroup(String description) {
        this.description = description;
    }

}
