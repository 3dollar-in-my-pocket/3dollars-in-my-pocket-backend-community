package com.threedollar.service.sticker;

import com.threedollar.domain.sticker.Sticker;
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
        Set<Long> stickerIds = stickerService.getStickerListByStickerIdAndGroup(request.getStickerIds(), stickerGroup);
        stickerActionService.upsertSticker(request, stickerGroup, stickerIds);
    }

    public List<StickerInfoResponse> getStickerList(@NotNull StickerGroup stickerGroup) {
        return stickerService.getStickerList(stickerGroup);
    }

    public List<TargetStickerReactionResponse> getTargetStickerReactionResponse(@NotNull StickerGroup stickerGroup, String accountId, Set<String> targetIds) {
        List<Sticker> stickers = stickerService.getStickersByStickerGroup(stickerGroup);
        return stickerActionService.getStickerReactionResponse(stickerGroup, accountId, targetIds, stickers);
    }


}
