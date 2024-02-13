package com.threedollar.service.sticker;

import com.threedollar.common.exception.NotFoundException;
import com.threedollar.domain.sticker.Sticker;
import com.threedollar.domain.sticker.StickerGroup;
import com.threedollar.service.sticker.dto.response.TargetStickerAction;
import com.threedollar.service.sticker.dto.request.AddStickerActionRequest;
import jakarta.validation.constraints.NotBlank;
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


    public void upsertSticker(AddStickerActionRequest request, @NotNull StickerGroup stickerGroup, String workspaceId) {
        Set<Long> stickerIds = stickerService.getStickerListByStickerIdAndGroup(request.getStickerIds(), stickerGroup);
        if (stickerIds.size() != request.getStickerIds().size()) {
            throw new NotFoundException(String.format("(%s)에 해당하는 스티커는 사용할 수 없습니다.", request.getStickerIds()));
        }
        stickerActionService.upsertSticker(request, stickerGroup, stickerIds, workspaceId);
    }

    public void deleteSticker(@NotNull StickerGroup stickerGroup, @NotBlank String workspaceId, String targetId, String accountId) {
        stickerActionService.deleteStickers(stickerGroup, workspaceId, targetId, accountId);
    }


    public List<TargetStickerAction> getTargetStickers(@NotNull StickerGroup stickerGroup, @NotBlank String workspaceId, String accountId, Set<String> targetIds) {
        List<Sticker> stickers = stickerService.getStickersByStickerGroup(stickerGroup);
        return stickerActionService.getStickerActionResponse(stickerGroup, workspaceId, accountId, targetIds, stickers);
    }


}
