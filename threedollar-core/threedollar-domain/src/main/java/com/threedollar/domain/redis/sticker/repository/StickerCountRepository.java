package com.threedollar.domain.redis.sticker.repository;

import com.threedollar.domain.sticker.StickerGroup;

public interface StickerCountRepository {

    void incrByCount(StickerGroup stickerGroup, String targetId, Long stickerId);

    long getValueByKey(StickerGroup stickerGroup, String targetId, Long stickerId);

    void decrByCount(StickerGroup stickerGroup, String targetId, Long stickerId);

}
