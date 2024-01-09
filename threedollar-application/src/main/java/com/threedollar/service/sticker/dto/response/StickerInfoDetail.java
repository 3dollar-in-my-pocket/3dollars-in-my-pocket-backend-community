package com.threedollar.service.sticker.dto.response;

import com.threedollar.domain.redis.sticker.repository.StickerCountRepository;
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

    public static StickerInfoDetail of(boolean isSelected, Sticker sticker, String targetId, StickerCountRepository stickerCountRepository) {
        Long count = stickerCountRepository.getValueByKey(sticker.getStickerGroup(), targetId, sticker.getId());
        return StickerInfoDetail.builder()
                .stickerId(sticker.getId())
                .priority(sticker.getPriority())
                .imageUrl(sticker.getImageUrl())
                .stickerCount(count)
                .selectedByMe(isSelected)
                .build();
    }

}
