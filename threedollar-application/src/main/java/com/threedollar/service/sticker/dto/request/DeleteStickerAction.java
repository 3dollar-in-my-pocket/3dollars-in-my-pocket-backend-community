package com.threedollar.service.sticker.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Getter
public class DeleteStickerAction {

    @NotBlank
    private String targetId;

    @NotBlank(message = "로그인 한 후 이용해주세요.")
    private String accountId;

    @Builder
    public DeleteStickerAction(String targetId, String accountId) {
        this.targetId = targetId;
        this.accountId = accountId;
    }
}
