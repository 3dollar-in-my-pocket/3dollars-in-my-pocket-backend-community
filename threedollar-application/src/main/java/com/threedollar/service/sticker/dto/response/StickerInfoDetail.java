package com.threedollar.service.sticker.dto.response;

import com.threedollar.domain.sticker.Sticker;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class StickerInfoDetail {

    private Long stickerId;

    private Long stickerCount;

    private String imageUrl;

    private int priority;

    private boolean selectedByMe;

    @Builder
    public StickerInfoDetail(Long stickerId, Long stickerCount, String imageUrl, int priority, boolean selectedByMe) {
        this.stickerId = stickerId;
        this.stickerCount = stickerCount;
        this.imageUrl = imageUrl;
        this.priority = priority;
        this.selectedByMe = selectedByMe;
    }

    public static StickerInfoDetail of(Sticker sticker, Long stickerCount, boolean isSelected) {
        return StickerInfoDetail.builder()
                .stickerId(sticker.getId())
                .priority(sticker.getPriority())
                .imageUrl(sticker.getImageUrl())
                .stickerCount(stickerCount)
                .selectedByMe(isSelected)
                .build();
    }

}
