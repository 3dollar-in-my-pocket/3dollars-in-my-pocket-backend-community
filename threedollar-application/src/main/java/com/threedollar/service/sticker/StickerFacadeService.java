package com.threedollar.service.sticker;

import com.threedollar.domain.sticker.StickerGroup;
import com.threedollar.service.sticker.dto.response.StickerInfoResponse;
import com.threedollar.service.sticker.dto.response.TargetStickerReactionResponse;
import com.threedollar.service.sticker.dto.response.request.AddReactionRequest;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
public class StickerFacadeService {

    private final StickerActionService stickerActionService;

    private final StickerService stickerService;

    public void upsertSticker(AddReactionRequest request, @NotNull StickerGroup stickerGroup) {
        stickerActionService.upsertSticker(request, stickerGroup);
    }

    public List<StickerInfoResponse> getStickerList(@NotNull StickerGroup stickerGroup) {
        return stickerService.getStickerList(stickerGroup);
    }

    public List<TargetStickerReactionResponse> getTargetStickerReactionResponse(@NotNull StickerGroup stickerGroup, String accountId, Set<String> targetIds) {
        return stickerActionService.getStickerReactionResponse(stickerGroup, accountId, targetIds);
    }

}
