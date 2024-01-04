package com.threedollar.service.reaction.dto.request;

import com.threedollar.domain.reaction.Reaction;
import com.threedollar.domain.sticker.StickerGroup;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Getter
public class AddReactionRequest {

    @NotBlank
    private String targetId;

    @NotBlank
    private Long stickerId;

    @NotBlank
    private String accountId;

    @Builder
    public AddReactionRequest(String targetId, Long stickerId, String accountId) {
        this.targetId = targetId;
        this.stickerId = stickerId;
        this.accountId = accountId;
    }

    public Reaction toEntity(StickerGroup stickerGroup) {
        return Reaction.newInstance(stickerGroup, stickerId, accountId, targetId);
    }
}
