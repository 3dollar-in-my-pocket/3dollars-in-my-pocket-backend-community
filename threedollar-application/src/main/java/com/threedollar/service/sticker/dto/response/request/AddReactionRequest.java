package com.threedollar.service.sticker.dto.response.request;

import com.threedollar.domain.reaction.StickerAction;
import com.threedollar.domain.sticker.StickerGroup;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
public class AddReactionRequest {

    @NotBlank
    private String targetId;

    @NotBlank
    private String accountId;

    @NotNull
    private List<Long> stickerIds;

    @Builder
    public AddReactionRequest(String targetId, String accountId, List<Long> stickerIds) {
        this.targetId = targetId;
        this.accountId = accountId;
        this.stickerIds = stickerIds;
    }

    public StickerAction toEntity(StickerGroup stickerGroup) {
        return StickerAction.newInstance(stickerGroup, stickerIds, accountId, targetId);
    }
}
