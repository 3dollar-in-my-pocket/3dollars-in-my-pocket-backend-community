package com.threedollar.domain.reaction.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.threedollar.domain.reaction.Reaction;
import com.threedollar.domain.sticker.StickerGroup;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.threedollar.domain.reaction.QReaction.reaction;


@RequiredArgsConstructor
public class ReactionRepositoryCustomImpl implements ReactionRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Reaction getReactionByStickerGroupAndTargetIdAndAccountId(StickerGroup stickerGroup,
                                                                     String targetId,
                                                                     String accountId) {
        return jpaQueryFactory.selectFrom(reaction)
                .where(reaction.stickerGroup.eq(stickerGroup),
                        reaction.targetId.eq(targetId),
                        reaction.accountId.eq(accountId))
                .fetchOne();
    }


    @Override
    public Map<String, List<Reaction>> getReactionByStickerGroupAndTargetIds(StickerGroup stickerGroup, List<String> targetIds) {
        Map<String, List<Reaction>> reactionMap = new HashMap<>();
        for (String targetId : targetIds) {
            reactionMap.put(targetId, getReactionsByTargetId(targetId, stickerGroup));
        }
        return reactionMap;
    }

    private List<Reaction> getReactionsByTargetId(String targetId, StickerGroup stickerGroup) {
        return jpaQueryFactory.selectFrom(reaction)
                .where(reaction.targetId.eq(targetId),
                        reaction.stickerGroup.eq(stickerGroup))
                .fetch();
    }

}

