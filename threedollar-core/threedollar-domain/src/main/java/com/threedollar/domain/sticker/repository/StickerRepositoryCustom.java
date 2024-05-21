package com.threedollar.domain.sticker.repository;

import com.threedollar.domain.sticker.Sticker;
import com.threedollar.domain.sticker.StickerGroup;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public interface StickerRepositoryCustom {

    List<Sticker> getStickerByStickerGroupAndWorkspaceIdAndTargetIds(@NotNull StickerGroup stickerGroup, String workspaceId);

}
