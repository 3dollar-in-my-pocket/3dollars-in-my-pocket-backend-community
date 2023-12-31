package com.threedollar.domain.sticker;


import com.threedollar.domain.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@NoArgsConstructor
public class Sticker extends BaseEntity {

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StickerGroup stickerGroup;

    @Column(nullable = false)
    private String imageUrl;

    @Column(nullable = false)
    private StickerStatus status;

    private int priority;

    @Builder
    public Sticker(StickerGroup stickerGroup, String imageUrl, StickerStatus status, int priority) {
        this.stickerGroup = stickerGroup;
        this.imageUrl = imageUrl;
        this.status = status;
        this.priority = priority;
    }

    public static Sticker newInstance(String imageUrl, StickerGroup stickerGroup) {
        return Sticker.builder()
                .imageUrl(imageUrl)
                .stickerGroup(stickerGroup)
                .status(StickerStatus.ACTIVE)
                .build();
    }

}
