package com.threedollar.domain;

import lombok.Getter;

@Getter
public enum AccountType {

    BOSS_ACCOUNT("사장님 계정"),
    USER_ACCOUNT("유저 계정"),
    ;

    private final String description;

    AccountType(String description) {
        this.description = description;
    }
}
