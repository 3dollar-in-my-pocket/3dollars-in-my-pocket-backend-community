package com.threedollar.domain.poll;

import lombok.Getter;

@Getter
public enum PollStatus {

    ACTIVE("활성화"),
    DELETED("삭제됨"),
    ;

    private final String description;

    PollStatus(String description) {
        this.description = description;
    }
}
