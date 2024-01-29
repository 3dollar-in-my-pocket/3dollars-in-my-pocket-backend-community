package com.threedollar.service.sticker.dto.response;

import com.threedollar.domain.sticker.StickerGroup;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
public class TargetStickerAction {

    private String targetId;

    private StickerGroup stickerGroup;

    private List<StickerInfoDetail> stickerInfoDetailList;

    @Builder
    public TargetStickerAction(String targetId, StickerGroup stickerGroup, List<StickerInfoDetail> stickerInfoDetailList) {
        this.targetId = targetId;
        this.stickerGroup = stickerGroup;
        this.stickerInfoDetailList = stickerInfoDetailList;
    }


}
