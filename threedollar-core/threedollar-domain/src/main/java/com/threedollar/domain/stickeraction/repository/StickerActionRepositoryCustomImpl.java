package com.threedollar.domain.stickeraction.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.threedollar.domain.stickeraction.StickerAction;
import com.threedollar.domain.sticker.StickerGroup;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.threedollar.domain.stickeraction.QReaction.reaction;


@RequiredArgsConstructor
public class StickerActionRepositoryCustomImpl implements StickerActionRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public StickerAction getReactionByStickerGroupAndTargetIdAndAccountId(StickerGroup stickerGroup,
                                                                          String targetId,
                                                                          String accountId) {
        return jpaQueryFactory.selectFrom(reaction)
                .where(reaction.stickerGroup.eq(stickerGroup),
                        reaction.targetId.eq(targetId),
                        reaction.accountId.eq(accountId))
                .fetchOne();
    }


    @Override
    public Map<String, List<StickerAction>> getReactionByStickerGroupAndTargetIds(StickerGroup stickerGroup, List<String> targetIds) {
        Map<String, List<StickerAction>> reactionMap = new HashMap<>();
        for (String targetId : targetIds) {
            reactionMap.put(targetId, getReactionsByTargetId(targetId, stickerGroup));
        }
        return reactionMap;
    }

    private List<StickerAction> getReactionsByTargetId(String targetId, StickerGroup stickerGroup) {
        return jpaQueryFactory.selectFrom(reaction)
                .where(reaction.targetId.eq(targetId),
                        reaction.stickerGroup.eq(stickerGroup))
                .fetch();
    }

}

