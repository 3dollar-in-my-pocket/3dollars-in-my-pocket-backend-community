package com.threedollar.domain.reaction;

import lombok.Getter;

@Getter
public enum ReactionTarget {

    POLL("투표"),
    REVIEW("리뷰"),
    ;

    private final String description;

    ReactionTarget(String description) {
        this.description = description;
    }
}
