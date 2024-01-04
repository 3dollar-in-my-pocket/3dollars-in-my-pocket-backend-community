package com.threedollar.domain.sticker.repository;

import com.threedollar.domain.sticker.Sticker;
import com.threedollar.domain.sticker.StickerGroup;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface StickerRepositoryCustom {

    List<Sticker> getStickerByStickerGroup(@NotNull StickerGroup stickerGroup);

    Sticker getStickerById(@NotNull Long stickerId);

}
