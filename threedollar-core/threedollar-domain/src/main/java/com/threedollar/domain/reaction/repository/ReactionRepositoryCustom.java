package com.threedollar.domain.reaction.repository;

import com.threedollar.domain.reaction.Reaction;
import com.threedollar.domain.sticker.StickerGroup;

import java.util.List;
import java.util.Map;


public interface ReactionRepositoryCustom {

    Reaction getReactionByStickerGroupAndTargetIdAndAccountId(StickerGroup stickerGroup,
                                                              String targetId,
                                                              String accountId);


    Map<String, List<Reaction>> getReactionByStickerGroupAndTargetIds(StickerGroup stickerGroup,
                                                                      List<String> targetIds);

}
