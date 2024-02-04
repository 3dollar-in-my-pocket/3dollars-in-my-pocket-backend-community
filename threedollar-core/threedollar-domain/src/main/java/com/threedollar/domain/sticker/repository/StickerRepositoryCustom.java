package com.threedollar.domain.sticker.repository;

import com.threedollar.domain.sticker.Sticker;
import com.threedollar.domain.sticker.StickerGroup;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.Set;

public interface StickerRepositoryCustom {

    List<Sticker> getStickerByStickerGroup(@NotNull String workspaceId, @NotNull StickerGroup stickerGroup);

    Sticker getStickerByIdAndStickerGroup(@NotNull String workspaceId, @NotNull Long stickerId, @NotNull StickerGroup stickerGroup);

    Set<Long> getStickerByIdsAndStickerGroup(@NotNull String workspaceId, @NotNull Set<Long> stickerIds, @NotNull StickerGroup stickerGroup);

}
