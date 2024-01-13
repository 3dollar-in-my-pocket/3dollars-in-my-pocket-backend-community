package com.threedollar.domain.stickeraction.repository;

import com.threedollar.domain.stickeraction.StickerAction;
import com.threedollar.domain.sticker.StickerGroup;

import java.util.List;
import java.util.Map;


public interface StickerActionRepositoryCustom {

    StickerAction getReactionByStickerGroupAndTargetIdAndAccountId(StickerGroup stickerGroup,
                                                                   String targetId,
                                                                   String accountId);


    Map<String, List<StickerAction>> getReactionByStickerGroupAndTargetIds(StickerGroup stickerGroup,
                                                                           List<String> targetIds);

}
