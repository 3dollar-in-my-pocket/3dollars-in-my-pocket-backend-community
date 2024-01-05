package com.threedollar.domain.sticker.repository;

import com.threedollar.domain.sticker.Sticker;
import com.threedollar.domain.sticker.StickerGroup;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public interface StickerRepositoryCustom {

    List<Sticker> getStickerByStickerGroup(@NotNull StickerGroup stickerGroup);

    Sticker getStickerByIdAndStickerGroup(@NotNull Long stickerId, @NotNull StickerGroup stickerGroup);

    List<Long> getStickerByIdsAndStickerGroup(@NotNull List<Long> stickerIds, @NotNull StickerGroup stickerGroup);

}
