package com.threedollar.domain.reaction.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class ReactionRepositoryCustomImpl implements ReactionRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;
}

