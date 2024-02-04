package com.threedollar.common.enums;

import lombok.Getter;

@Getter
public enum CommunityHttpHeaders {

    API_KEY("X-Community-Api-Key"),
    ;

    private final String headerName;

    CommunityHttpHeaders(String headerName) {
        this.headerName = headerName;
    }

}
