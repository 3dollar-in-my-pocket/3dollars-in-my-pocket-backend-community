package com.threedollar.domain.reaction.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.threedollar.domain.reaction.Reaction;
import com.threedollar.domain.sticker.StickerGroup;
import lombok.RequiredArgsConstructor;

import static com.threedollar.domain.reaction.QReaction.reaction;


@RequiredArgsConstructor
public class ReactionRepositoryCustomImpl implements ReactionRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Reaction getByReactionStickerGroupAndTargetIdAndAccountId(StickerGroup stickerGroup,
                                                                     String targetId,
                                                                     String accountId) {
        return jpaQueryFactory.selectFrom(reaction)
                .where(reaction.stickerGroup.eq(stickerGroup),
                        reaction.targetId.eq(targetId),
                        reaction.accountId.eq(accountId))
                .fetchOne();
    }
}

