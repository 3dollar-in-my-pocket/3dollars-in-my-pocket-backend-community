package com.threedollar.domain.stickeraction.repository;

import com.threedollar.domain.stickeraction.StickerAction;
import com.threedollar.domain.sticker.StickerGroup;

import java.util.List;
import java.util.Set;


public interface StickerActionRepositoryCustom {

    StickerAction getStickerActionByStickerGroupAndTargetIdAndAccountId(StickerGroup stickerGroup,
                                                                        String targetId,
                                                                        String accountId);



    List<StickerAction> getStickerActionByMe(String accountId,
                                             Set<String> targetIds,
                                             StickerGroup stickerGroup);

}
