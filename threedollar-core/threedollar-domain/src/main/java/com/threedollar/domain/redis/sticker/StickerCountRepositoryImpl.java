package com.threedollar.domain.redis.sticker;

import com.threedollar.domain.redis.StringRedisRepository;
import com.threedollar.domain.redis.sticker.repository.StickerCountRepository;
import com.threedollar.domain.sticker.StickerGroup;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class StickerCountRepositoryImpl implements StickerCountRepository {

    private final StringRedisRepository<StickerCountKey, Long> stickerRedisRepository;

    @Override
    public void incrByCount(StickerGroup stickerGroup, String targetId) {
        StickerCountKey key = StickerCountKey.builder()
                .stickerGroup(stickerGroup)
                .targetId(targetId)
                .build();
        stickerRedisRepository.incr(key);
    }

    @Override
    public long getValueByKey(StickerGroup stickerGroup, String targetId) {
        StickerCountKey key = StickerCountKey.builder()
                .stickerGroup(stickerGroup)
                .targetId(targetId)
                .build();
        return stickerRedisRepository.get(key);
    }

    @Override
    public void decrByCount(StickerGroup stickerGroup, String targetId) {
        StickerCountKey key = StickerCountKey.builder()
                .stickerGroup(stickerGroup)
                .targetId(targetId)
                .build();
        stickerRedisRepository.decr(key);
    }
}
