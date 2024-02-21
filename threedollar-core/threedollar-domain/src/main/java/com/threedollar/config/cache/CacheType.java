package com.threedollar.config.cache;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CacheType {
    MEMBER_PROFILE("member", 12, 10000);

    private final String cacheName; // 이름

    private final int expiredAfterWrite; // 특정 기간 지난 후 자동으로 제거

    private final int maxSize; // 캐시에 포함할 수 있는 최대 엔트리 수

}
