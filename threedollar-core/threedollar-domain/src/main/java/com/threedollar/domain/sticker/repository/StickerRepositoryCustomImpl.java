package com.threedollar.domain.sticker.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.threedollar.domain.sticker.Sticker;
import com.threedollar.domain.sticker.StickerGroup;
import com.threedollar.domain.sticker.StickerStatus;
import lombok.RequiredArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.threedollar.domain.sticker.QSticker.sticker;

@RequiredArgsConstructor
public class StickerRepositoryCustomImpl implements StickerRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Sticker> getStickerByStickerGroup(StickerGroup stickerGroup) {
        return jpaQueryFactory.selectFrom(sticker)
            .where(sticker.stickerGroup.eq(stickerGroup),
                sticker.status.eq(StickerStatus.ACTIVE))
            .fetch();
    }

    @Override
    public Sticker getStickerByIdAndStickerGroup(Long stickerId,
                                                 StickerGroup stickerGroup) {
        return jpaQueryFactory.selectFrom(sticker)
            .where(
                sticker.stickerGroup.eq(stickerGroup),
                sticker.status.eq(StickerStatus.ACTIVE))
            .fetchOne();
    }

    @Override
    public Set<Long> getStickerByIdsAndStickerGroup(Set<Long> stickerIds, StickerGroup stickerGroup) {
        return new HashSet<>(jpaQueryFactory.select(sticker.id)
            .from(sticker)
            .where(
                sticker.id.in(stickerIds),
                sticker.stickerGroup.eq(stickerGroup),
                sticker.status.eq(StickerStatus.ACTIVE)
            ).fetch());
    }

}
