package com.threedollar.domain.stickeraction;

import com.threedollar.StringArrayConverter;
import com.threedollar.domain.BaseEntity;
import com.threedollar.domain.sticker.StickerGroup;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name="reaction", uniqueConstraints = {
        @UniqueConstraint(
                name="uni_reaction_1",
                columnNames = {"accountId","targetId","stickerGroup"}
        )
})
public class StickerAction extends BaseEntity {

    @Column(nullable = false)
    private StickerGroup stickerGroup;

    @Convert(converter = StringArrayConverter.class)
    private List<Long> stickerIds;

    @Column(nullable = false)
    private String accountId;

    @Column(nullable = false)
    private String targetId; // pollId, reviewId 등 ..


    @Builder
    public StickerAction(@NotNull StickerGroup stickerGroup,
                         @NotBlank List<Long> stickerIds,
                         @NotBlank String accountId,
                         @NotBlank String targetId) {
        this.stickerGroup = stickerGroup;
        this.stickerIds = stickerIds;
        this.accountId = accountId;
        this.targetId = targetId;
    }

    public static StickerAction newInstance(StickerGroup stickerGroup, List<Long> stickerIds, String accountId, String targetId) {
        return StickerAction.builder()
                .stickerGroup(stickerGroup)
                .stickerIds(stickerIds)
                .accountId(accountId)
                .targetId(targetId)
                .build();
    }

    public void update(List<Long> stickerIds) {
        this.stickerIds = stickerIds;
    }

}
