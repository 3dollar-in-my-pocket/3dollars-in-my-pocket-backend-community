package com.threedollar.service.sticker;

import com.threedollar.domain.sticker.Sticker;
import com.threedollar.domain.sticker.StickerGroup;
import com.threedollar.service.sticker.dto.request.AddStickerActionRequest;
import com.threedollar.service.sticker.dto.response.TargetStickerAction;
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


    public void upsertSticker(@NotNull String workspaceId, AddStickerActionRequest request, @NotNull StickerGroup stickerGroup) {
        Set<Long> stickerIds = stickerService.getStickerListByStickerIdAndGroup(workspaceId, request.getStickerIds(), stickerGroup);
        stickerActionService.upsertSticker(workspaceId, request, stickerGroup, stickerIds);
    }


    public List<TargetStickerAction> getTargetStickers(@NotNull String workspaceId, @NotNull StickerGroup stickerGroup, String accountId, Set<String> targetIds) {
        List<Sticker> stickers = stickerService.getStickersByStickerGroup(workspaceId, stickerGroup);
        return stickerActionService.getStickerActionResponse(workspaceId, stickerGroup, accountId, targetIds, stickers);
    }


}
