package com.threedollar.service.sticker.dto.response;

import com.threedollar.domain.sticker.Sticker;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class StickerInfoDetail {

    private String stickerName;

    private Long stickerCount;

    private String imageUrl;

    private boolean selectedByMe;

    @Builder
    public StickerInfoDetail(String stickerName, Long stickerCount, String imageUrl, boolean selectedByMe) {
        this.stickerName = stickerName;
        this.stickerCount = stickerCount;
        this.imageUrl = imageUrl;
        this.selectedByMe = selectedByMe;
    }

    public static StickerInfoDetail of(Sticker sticker, Long stickerCount, boolean isSelected) {
        return StickerInfoDetail.builder()
                .stickerName(sticker.getName())
                .imageUrl(sticker.getImageUrl())
                .stickerCount(stickerCount)
                .selectedByMe(isSelected)
                .build();
    }

}
