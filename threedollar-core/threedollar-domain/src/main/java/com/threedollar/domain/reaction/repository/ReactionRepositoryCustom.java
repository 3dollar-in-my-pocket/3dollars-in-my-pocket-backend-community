package com.threedollar.domain.reaction.repository;

import com.threedollar.domain.reaction.Reaction;
import com.threedollar.domain.sticker.StickerGroup;

public interface ReactionRepositoryCustom {

    Reaction getReactionByTargetAndAccountIdAndStickerId(StickerGroup stickerGroup,
                                                         String targetId,
                                                         String accountId,
                                                         Long stickerId);
}
