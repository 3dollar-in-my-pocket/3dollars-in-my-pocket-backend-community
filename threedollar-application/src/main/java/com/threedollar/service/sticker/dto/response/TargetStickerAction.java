package com.threedollar.service.sticker.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
public class TargetStickerAction {

    private String targetId;

    private List<StickerInfoDetail> stickers;

    @Builder
    public TargetStickerAction(String targetId, List<StickerInfoDetail> stickers) {
        this.targetId = targetId;
        this.stickers = stickers;
    }

}
