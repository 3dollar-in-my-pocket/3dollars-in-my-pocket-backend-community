package com.threedollar.domain.sticker;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public enum StickerGroup {

    POLL_COMMENT("투표댓글"),
    REVIEW("리뷰"),
    ;
    private final String description;

    StickerGroup(String description) {
        this.description = description;
    }

    public List<StickerGroup> getStickerGroups() {
        return Arrays.asList(StickerGroup.values());
    }


}
