package com.threedollar.domain.post;

import lombok.Getter;

@Getter
public enum PostGroup {

    BOSS_NEWS("사장님 소식")
    ;

    private final String description;

    PostGroup(String description) {
        this.description = description;
    }

}
