package com.threedollar.service.sticker.dto.response;

import com.threedollar.domain.sticker.Sticker;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class StickerInfoResponse {

    private String groupName;

    private String imageUrl;

    private int priority;

    @Builder
    public StickerInfoResponse(String groupName, String imageUrl, int priority) {
        this.groupName = groupName;
        this.imageUrl = imageUrl;
        this.priority = priority;
    }

    public static StickerInfoResponse of(Sticker sticker) {
        return StickerInfoResponse.builder()
                .groupName(sticker.getStickerGroup().name())
                .imageUrl(sticker.getImageUrl())
                .priority(sticker.getPriority())
                .build();
    }
}
