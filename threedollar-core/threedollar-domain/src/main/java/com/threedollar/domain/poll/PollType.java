package com.threedollar.domain.poll;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum PollType {
    TASTE_VS_TASTE("맛대맛"),
    BEST_FOOD("가장 맛있는 음식")
    ;

    private final String description;


}
