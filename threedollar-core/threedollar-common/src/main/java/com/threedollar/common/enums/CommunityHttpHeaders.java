package com.threedollar.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CommunityHttpHeaders {

    API_KEY("X-Community-Api-Key"),
    ;

    private final String headerName;

}
