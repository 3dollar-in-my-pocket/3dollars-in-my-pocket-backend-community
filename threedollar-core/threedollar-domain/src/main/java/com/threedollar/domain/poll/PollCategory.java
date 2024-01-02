package com.threedollar.domain.poll;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Getter
public enum PollCategory {
    TASTE_VS_TASTE("맛짱투표", "그만싸워 얘덜아... 먹을걸로 왜그래..."),
    BEST_FOOD("가장 맛있는 음식", "참맛을 찾아서 ....")
    ;

    private final String title;

    private final String content;

    public static List<PollCategory> pollTypeList() {
        return List.of(PollCategory.values());
    }


}
