package com.threedollar.domain.reaction.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.threedollar.domain.reaction.Reaction;
import com.threedollar.domain.reaction.ReactionTarget;
import lombok.RequiredArgsConstructor;

import static com.threedollar.domain.reaction.QReaction.reaction;

@RequiredArgsConstructor
public class ReactionRepositoryCustomImpl implements ReactionRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Reaction getReactionByTargetAndAccountIdAndStickerId(ReactionTarget reactionTarget, String targetId, String accountId, String stickerId) {
        return jpaQueryFactory.selectFrom(reaction)
                .where(reaction.reactionTarget.eq(reactionTarget),
                        reaction.targetId.eq(targetId),
                        reaction.accountId.eq(accountId),
                        reaction.stickerId.eq(stickerId))
                .fetchOne();
    }
}
