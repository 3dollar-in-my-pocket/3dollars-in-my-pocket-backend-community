package com.threedollar.domain.reaction;

import com.threedollar.domain.BaseEntity;
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
    private ReactionTarget reactionTarget;

    @Column(nullable = false)
    private String stickerId;

    @Column(nullable = false)
    private String accountId;

    @Column(nullable = false)
    private String targetId; // poll, review 등 ..

    @Column(nullable = false)
    private ReactionStatus status;

    @Builder
    public Reaction(@NotNull ReactionTarget reactionTarget,
                    @NotBlank String stickerId,
                    @NotBlank String accountId,
                    @NotBlank String targetId,
                    @NotNull ReactionStatus status) {
        this.reactionTarget = reactionTarget;
        this.stickerId = stickerId;
        this.accountId = accountId;
        this.targetId = targetId;
        this.status = status;
    }

    public static Reaction newInstance(ReactionTarget reactionTarget, String stickerId, String accountId, String targetId) {
        return Reaction.builder()
                .reactionTarget(reactionTarget)
                .stickerId(stickerId)
                .accountId(accountId)
                .targetId(targetId)
                .status(ReactionStatus.ACTIVE)
                .build();
    }
}
