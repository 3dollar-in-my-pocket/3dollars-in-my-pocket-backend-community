package com.threedollar.service.reaction.dto.request;

import com.threedollar.domain.reaction.Reaction;
import com.threedollar.domain.reaction.ReactionTarget;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
public class AddReactionRequest {

    @NotNull
    private ReactionTarget reactionTarget;

    @NotBlank
    private String targetId;

    @NotBlank
    private String stickerId;

    @NotBlank
    private String accountId;

    @Builder
    public AddReactionRequest(ReactionTarget reactionTarget, String targetId, String stickerId, String accountId) {
        this.reactionTarget = reactionTarget;
        this.targetId = targetId;
        this.stickerId = stickerId;
        this.accountId = accountId;
    }

    public Reaction toEntity() {
        return Reaction.newInstance(reactionTarget, stickerId, accountId, targetId);
    }
}
