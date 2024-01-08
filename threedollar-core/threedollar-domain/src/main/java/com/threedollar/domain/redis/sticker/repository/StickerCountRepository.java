package com.threedollar.domain.redis.sticker.repository;

import com.threedollar.domain.sticker.StickerGroup;

public interface StickerCountRepository {

    void incrByCount(StickerGroup stickerGroup, String targetId);

    long getValueByKey(StickerGroup stickerGroup, String targetId);

    void decrByCount(StickerGroup stickerGroup, String targetId);

}
