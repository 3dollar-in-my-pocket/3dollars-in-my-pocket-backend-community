package com.threedollar.domain.reaction.repository;

import com.threedollar.domain.reaction.Reaction;
import com.threedollar.domain.reaction.ReactionTarget;

public interface ReactionRepositoryCustom {

    Reaction getReactionByTargetAndAccountIdAndStickerId(ReactionTarget reactionTarget,
                                                         String targetId,
                                                         String accountId,
                                                         String stickerId);
}
