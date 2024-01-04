package com.threedollar.domain.reaction;

import com.threedollar.domain.BaseEntity;
import com.threedollar.domain.sticker.StickerGroup;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@NoArgsConstructor
public class Reaction extends BaseEntity {

    @Column(nullable = false)
    private StickerGroup stickerGroup;

    @Column(nullable = false)
    private Long stickerId;

    @Column(nullable = false)
    private String accountId;

    @Column(nullable = false)
    private String targetId; // poll, review 등 ..

    @Column(nullable = false)
    private ReactionStatus status;

    @Builder
    public Reaction(@NotNull StickerGroup stickerGroup,
                    @NotBlank Long stickerId,
                    @NotBlank String accountId,
                    @NotBlank String targetId,
                    @NotNull ReactionStatus status) {
        this.stickerGroup = stickerGroup;
        this.stickerId = stickerId;
        this.accountId = accountId;
        this.targetId = targetId;
        this.status = status;
    }

    public static Reaction newInstance(StickerGroup stickerGroup, Long stickerId, String accountId, String targetId) {
        return Reaction.builder()
                .stickerGroup(stickerGroup)
                .stickerId(stickerId)
                .accountId(accountId)
                .targetId(targetId)
                .status(ReactionStatus.ACTIVE)
                .build();
    }
}
