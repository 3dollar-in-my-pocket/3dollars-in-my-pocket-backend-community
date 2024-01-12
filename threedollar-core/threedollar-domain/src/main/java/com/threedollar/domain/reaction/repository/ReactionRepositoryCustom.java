package com.threedollar.domain.reaction.repository;

import com.threedollar.domain.reaction.StickerAction;
import com.threedollar.domain.sticker.StickerGroup;

import java.util.List;
import java.util.Map;


public interface ReactionRepositoryCustom {

    StickerAction getReactionByStickerGroupAndTargetIdAndAccountId(StickerGroup stickerGroup,
                                                                   String targetId,
                                                                   String accountId);


    Map<String, List<StickerAction>> getReactionByStickerGroupAndTargetIds(StickerGroup stickerGroup,
                                                                           List<String> targetIds);

}
