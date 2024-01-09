package com.threedollar.service.sticker.dto.response;

import com.threedollar.domain.redis.sticker.repository.StickerCountRepository;
import com.threedollar.domain.sticker.Sticker;
import com.threedollar.domain.sticker.StickerGroup;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
public class TargetStickerReactionResponse {

    private String targetId;

    private StickerGroup stickerGroup;

    private List<StickerInfoDetail> stickerInfoDetailList;

    @Builder
    public TargetStickerReactionResponse(String targetId, StickerGroup stickerGroup, List<StickerInfoDetail> stickerInfoDetailList) {
        this.targetId = targetId;
        this.stickerGroup = stickerGroup;
        this.stickerInfoDetailList = stickerInfoDetailList;
    }

    public static TargetStickerReactionResponse of(boolean isSelected, String targetId, StickerGroup stickerGroup, StickerCountRepository stickerCountRepository, List<Sticker> stickers) {

        List<StickerInfoDetail> stickerInfoDetails = stickers.stream()
                .map(sticker -> StickerInfoDetail.of(isSelected, sticker, targetId, stickerCountRepository))
                .toList();

        return TargetStickerReactionResponse.builder()
                .targetId(targetId)
                .stickerGroup(stickerGroup)
                .stickerInfoDetailList(stickerInfoDetails)
                .build();

    }



}
