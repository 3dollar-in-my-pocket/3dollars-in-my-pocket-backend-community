package com.threedollar.domain.sticker;

import lombok.Getter;

@Getter
public enum StickerGroup {

    POLL("투표"),
    REVIEW("리뷰"),
    COMMUNITY_COMMENT("커뮤니티 댓글")
    ;
    private final String description;

    StickerGroup(String description) {
        this.description = description;
    }

}
