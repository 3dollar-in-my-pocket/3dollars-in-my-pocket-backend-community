package com.threedollar.service.sticker;

import com.threedollar.domain.sticker.Sticker;
import com.threedollar.domain.sticker.StickerGroup;
import com.threedollar.service.sticker.dto.response.TargetStickerAction;
import com.threedollar.service.sticker.dto.request.AddReactionRequest;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class StickerFacadeService {

    private final StickerActionService stickerActionService;

    private final StickerService stickerService;


    public void upsertSticker(AddReactionRequest request, @NotNull StickerGroup stickerGroup) {
        Set<Long> stickerIds = stickerService.getStickerListByStickerIdAndGroup(request.getStickerIds(), stickerGroup);
        stickerActionService.upsertSticker(request, stickerGroup, stickerIds);
    }


    public List<TargetStickerAction> getTargetStickers(@NotNull StickerGroup stickerGroup, String accountId, Set<String> targetIds) {
        List<Sticker> stickers = stickerService.getStickersByStickerGroup(stickerGroup);
        return stickerActionService.getStickerActionResponse(stickerGroup, accountId, targetIds, stickers);
    }


}