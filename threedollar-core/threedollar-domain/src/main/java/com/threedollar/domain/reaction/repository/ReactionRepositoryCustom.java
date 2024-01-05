package com.threedollar.domain.reaction.repository;

import com.threedollar.domain.reaction.Reaction;
import com.threedollar.domain.sticker.StickerGroup;


public interface ReactionRepositoryCustom {

    Reaction getByReactionStickerGroupAndTargetIdAndAccountId(StickerGroup stickerGroup,
                                                                           String targetId,
                                                                           String accountId);

}
