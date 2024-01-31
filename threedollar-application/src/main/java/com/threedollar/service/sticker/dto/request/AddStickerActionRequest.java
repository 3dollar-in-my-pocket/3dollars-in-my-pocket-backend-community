package com.threedollar.service.sticker.dto.request;

import com.threedollar.domain.sticker.StickerGroup;
import com.threedollar.domain.stickeraction.StickerAction;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor
@Getter
public class AddStickerActionRequest {

    @NotBlank
    private String targetId;

    @NotBlank
    private String accountId;

    @NotEmpty
    private Set<Long> stickerIds;

    @Builder
    public AddStickerActionRequest(String targetId, String accountId, Set<Long> stickerIds) {
        this.targetId = targetId;
        this.accountId = accountId;
        this.stickerIds = stickerIds;
    }

    public StickerAction toEntity(StickerGroup stickerGroup) {
        return StickerAction.newInstance(stickerGroup, stickerIds, accountId, targetId);
    }
}
