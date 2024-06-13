package com.threedollar.domain.post;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PostType {

    IMAGE("사진"),
    TEXT("글"),
    ;

    private final String description;
}
