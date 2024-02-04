package com.threedollar.domain.stickeraction;

import com.threedollar.common.util.JsonUtils;
import com.threedollar.domain.sticker.StickerGroup;
import com.threedollar.infra.redis.StringRedisKey;
import lombok.Builder;
import lombok.Getter;

import java.time.Duration;

@Getter
public class StickerActionCountKey implements StringRedisKey<StickerActionCountKey, Long> {

    private static final long DEFAULT_VALUE = 0L;

    private final String workspaceId;

    private final StickerGroup stickerGroup;

    private final String targetId;

    private final Long stickerId;

    @Builder
    public StickerActionCountKey(String workspaceId, StickerGroup stickerGroup, String targetId, Long stickerId) {
        this.workspaceId = workspaceId;
        this.stickerGroup = stickerGroup;
        this.targetId = targetId;
        this.stickerId = stickerId;
    }

    public static StickerActionCountKey of(String workspaceId, StickerGroup stickerGroup, String targetId, Long stickerId) {
        return StickerActionCountKey.builder()
            .workspaceId(workspaceId)
            .stickerId(stickerId)
            .stickerGroup(stickerGroup)
            .targetId(targetId)
            .build();
    }

    @Override
    public String getKey() {
        return "stickerAction:workspace:" + workspaceId + "," + "stickerGroup:" + stickerGroup + "," + "targetId:" + targetId + "," + "stickerId:" + stickerId;
    }

    @Override
    public Long deserializeValue(String value) {
        if (value == null) {
            return DEFAULT_VALUE;
        }
        try {
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
