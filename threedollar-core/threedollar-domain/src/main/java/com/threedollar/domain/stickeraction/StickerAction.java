package com.threedollar.domain.stickeraction;

import com.threedollar.config.converter.SetLongArrayConverter;
import com.threedollar.domain.BaseEntity;
import com.threedollar.domain.sticker.StickerGroup;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Getter
@NoArgsConstructor
@Table(uniqueConstraints = {
    @UniqueConstraint(
        name = "uni_sticker_action_1",
        columnNames = {"accountId", "targetId", "stickerGroup", "workspaceId"}
    )
})
public class StickerAction extends BaseEntity {

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StickerGroup stickerGroup;

    @Column(nullable = false)
    private String workspaceId;

    @Convert(converter = SetLongArrayConverter.class)
    private Set<Long> stickerIds;

    @Column(nullable = false)
    private String accountId;

    @Column(nullable = false)
    private String targetId; // pollId, reviewId 등 ..


    @Builder
    public StickerAction(@NotNull StickerGroup stickerGroup,
                         @NotBlank String workspaceId,
                         @NotBlank Set<Long> stickerIds,
                         @NotBlank String accountId,
                         @NotBlank String targetId) {
        this.stickerGroup = stickerGroup;
        this.workspaceId = workspaceId;
        this.stickerIds = stickerIds;
        this.accountId = accountId;
        this.targetId = targetId;
    }

    public static StickerAction newInstance(StickerGroup stickerGroup, String workspaceId, Set<Long> stickerIds, String accountId, String targetId) {
        return StickerAction.builder()
            .stickerGroup(stickerGroup)
            .workspaceId(workspaceId)
            .stickerIds(stickerIds)
            .accountId(accountId)
            .targetId(targetId)
            .build();
    }

    public void update(Set<Long> stickerIds) {
        this.stickerIds = stickerIds;
    }

}
