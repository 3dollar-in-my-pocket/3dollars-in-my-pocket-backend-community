package com.threedollar.threedollardomain.domain.poll;

import lombok.Getter;

@Getter
public enum PollType {

    TASTE_VS_TASTE("맛대맛"),
    ;

    private final String description;

    PollType(String description) {
        this.description = description;
    }


}
