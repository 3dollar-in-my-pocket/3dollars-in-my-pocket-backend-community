package com.threedollar.domain.stickeraction.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.threedollar.domain.stickeraction.StickerAction;
import com.threedollar.domain.sticker.StickerGroup;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Set;

import static com.threedollar.domain.stickeraction.QStickerAction.stickerAction;

@RequiredArgsConstructor
public class StickerActionRepositoryCustomImpl implements StickerActionRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public StickerAction getReactionByStickerGroupAndTargetIdAndAccountId(StickerGroup stickerGroup,
                                                                          String targetId,
                                                                          String accountId) {
        return jpaQueryFactory.selectFrom(stickerAction)
                .where(stickerAction.stickerGroup.eq(stickerGroup),
                        stickerAction.targetId.eq(targetId),
                        stickerAction.accountId.eq(accountId))
                .fetchOne();
    }

    @Override
    public List<StickerAction> getStickerActionByMe(String accountId, Set<String> targetIds, StickerGroup stickerGroup) {
        return jpaQueryFactory.selectFrom(stickerAction)
                .where(stickerAction.stickerGroup.eq(stickerGroup),
                        stickerAction.targetId.in(targetIds),
                        stickerAction.accountId.eq(accountId))
                .fetch();
    }


}

