package com.threedollar.domain.poll.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.threedollar.domain.poll.Poll;
import com.threedollar.domain.poll.PollType;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.threedollar.domain.poll.QPoll.poll;

@RequiredArgsConstructor
public class PollRepositoryCustomImpl implements PollRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Poll> findAllPollList(Long cursor, int size, PollType pollType) {
        return jpaQueryFactory.selectFrom(poll)
                .where(existedCursor(cursor),
                        poll.pollType.eq(pollType))
                .orderBy(poll.id.desc())
                .limit(size)
                .fetch();
    }

    private BooleanExpression existedCursor(Long cursor) {
        if (cursor == null) {
            return null;
        }
        return poll.id.lt(cursor);
    }

}
