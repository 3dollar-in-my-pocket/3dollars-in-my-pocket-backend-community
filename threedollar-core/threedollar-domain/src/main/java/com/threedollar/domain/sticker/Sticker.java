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

    @Column(nullable = false, length = 100)
    private String workspaceId;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(length = 200)
    private String imageUrl;

    @Column(nullable = false, length = 100)
    @Enumerated(EnumType.STRING)
    private StickerStatus status;


    @Column(length = 20)
    private int priority;

    @Builder
    public Sticker(StickerGroup stickerGroup, String workspaceId, String name, String imageUrl, StickerStatus status, int priority) {
        this.stickerGroup = stickerGroup;
        this.workspaceId = workspaceId;
        this.name = name;
        this.imageUrl = imageUrl;
        this.status = status;
        this.priority = priority;
    }

    public static Sticker newInstance(String imageUrl, String workspaceId, String name, StickerGroup stickerGroup) {
        return Sticker.builder()
            .imageUrl(imageUrl)
            .workspaceId(workspaceId)
            .name(name)
            .stickerGroup(stickerGroup)
            .status(StickerStatus.ACTIVE)
            .build();
    }

}
