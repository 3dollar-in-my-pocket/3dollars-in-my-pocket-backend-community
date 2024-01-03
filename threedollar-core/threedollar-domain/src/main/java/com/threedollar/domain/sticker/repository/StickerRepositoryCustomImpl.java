package com.threedollar.domain.sticker.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.threedollar.domain.sticker.Sticker;
import com.threedollar.domain.sticker.StickerGroup;
import com.threedollar.domain.sticker.StickerStatus;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.threedollar.domain.sticker.QSticker.sticker;

@RequiredArgsConstructor
public class StickerRepositoryCustomImpl implements StickerRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Sticker> getStickerByStickerGroup(StickerGroup stickerGroup) {
        return jpaQueryFactory.selectFrom(sticker)
                .where(sticker.stickerGroup.eq(stickerGroup),
                        sticker.status.eq(StickerStatus.ACTIVE))
                .fetch();
    }
}
