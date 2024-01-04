package com.threedollar.domain.reaction.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.threedollar.domain.reaction.Reaction;
import com.threedollar.domain.sticker.StickerGroup;
import lombok.RequiredArgsConstructor;

import static com.threedollar.domain.reaction.QReaction.reaction;

@RequiredArgsConstructor
public class ReactionRepositoryCustomImpl implements ReactionRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Reaction getReactionByTargetAndAccountIdAndStickerId(StickerGroup stickerGroup, String targetId, String accountId, Long stickerId) {
        return jpaQueryFactory.selectFrom(reaction)
                .where(
                        reaction.targetId.eq(targetId),
                        reaction.accountId.eq(accountId),
                        reaction.stickerId.eq(stickerId))
                .fetchOne();
    }
}
