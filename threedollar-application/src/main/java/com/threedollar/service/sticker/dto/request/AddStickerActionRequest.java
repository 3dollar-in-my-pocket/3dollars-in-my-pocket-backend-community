package com.threedollar.service.sticker.dto.request;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor
@Getter
public class AddStickerActionRequest {

    @NotBlank
    private String targetId;

    @Nullable
    private String accountId;

    @NotBlank
    private Set<String> stickerNames;

    @Builder
    public AddStickerActionRequest(String targetId, String accountId, Set<String> stickerNames) {
        this.targetId = targetId;
        this.accountId = accountId;
        this.stickerNames = stickerNames;
    }

}
