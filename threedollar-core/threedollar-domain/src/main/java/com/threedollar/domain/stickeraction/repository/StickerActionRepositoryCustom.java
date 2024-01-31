package com.threedollar.domain.stickeraction.repository;

import com.threedollar.domain.stickeraction.StickerAction;
import com.threedollar.domain.sticker.StickerGroup;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.Set;


public interface StickerActionRepositoryCustom {

    StickerAction getStickerActionByStickerGroupAndTargetIdAndAccountId(@NotNull StickerGroup stickerGroup,
                                                                        @NotBlank String targetId,
                                                                        @NotBlank String accountId);



    List<StickerAction> getStickerActionByMe(String accountId,
                                             Set<String> targetIds,
                                             StickerGroup stickerGroup);

}
