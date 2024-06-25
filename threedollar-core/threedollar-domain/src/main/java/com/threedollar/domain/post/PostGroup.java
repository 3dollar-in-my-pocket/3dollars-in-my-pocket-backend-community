package com.threedollar.domain.post;

import lombok.Getter;

@Getter
public enum PostGroup {

    NEWS_POST("소식")
    ;

    private final String description;

    PostGroup(String description) {
        this.description = description;
    }

}
