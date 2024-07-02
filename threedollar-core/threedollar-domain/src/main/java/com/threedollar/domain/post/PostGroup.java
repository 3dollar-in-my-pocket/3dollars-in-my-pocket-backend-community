package com.threedollar.domain.post;

import lombok.Getter;

@Getter
public enum PostGroup {

    STORE_NEWS("가게 소식")
    ;

    private final String description;

    PostGroup(String description) {
        this.description = description;
    }

}
