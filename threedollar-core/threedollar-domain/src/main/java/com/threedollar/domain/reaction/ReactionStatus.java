package com.threedollar.domain.reaction;

import lombok.Getter;

@Getter
public enum ReactionStatus {

    ACTIVE("활성화"),
    DELETED("삭제됨"),
    ;

    private final String description;

    ReactionStatus(String description) {
        this.description = description;
    }

}
