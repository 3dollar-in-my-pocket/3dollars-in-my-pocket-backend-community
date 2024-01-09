package com.threedollar.domain.redis.sticker;

import com.threedollar.common.exception.util.JsonUtils;
import com.threedollar.domain.redis.StringRedisKey;
import com.threedollar.domain.sticker.StickerGroup;
import lombok.Builder;
import lombok.Getter;

import java.time.Duration;

@Getter
public class StickerCountKey implements StringRedisKey<StickerCountKey, Long> {

    private static final long DEFAULT_VALUE = 0L;

    private final StickerGroup stickerGroup;

    private final String targetId;

    private final Long stickerId;

    @Builder
    public StickerCountKey(StickerGroup stickerGroup, String targetId, Long stickerId) {
        this.stickerGroup = stickerGroup;
        this.targetId = targetId;
        this.stickerId = stickerId;
    }

    @Override
    public String getKey() {
        return "stickerGroup:" + stickerGroup + "," + "targetId:" + targetId + "," + "stickerId:" + stickerId;
    }

    @Override
    public Long deserializeValue(String value) {
        if (value == null) {
            return DEFAULT_VALUE;
        } try {
            return Long.valueOf(value);
        } catch (Exception e) {
            throw new IllegalArgumentException(String.format("역직렬화 중 에러가 발생하였습니다. value: (%s)", value));
        }
    }

    @Override
    public String serializeValue(Long value) {
        return JsonUtils.toJson(value);
    }

    @Override
    public Duration getTtl() {
        return null;
    }
}
