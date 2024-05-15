package com.threedollar.domain.poll.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.threedollar.domain.poll.Poll;
import com.threedollar.domain.poll.PollCategory;
import com.threedollar.domain.poll.PollStatus;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.threedollar.domain.poll.QPoll.poll;

@RequiredArgsConstructor
public class PollRepositoryCustomImpl implements PollRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Poll> findAllPollList(Long cursor, int size, PollCategory pollCategory) {
        return jpaQueryFactory.selectFrom(poll)
                .where(existedCursor(cursor),
                        poll.pollCategory.eq(pollCategory))
                .orderBy(poll.id.desc())
                .limit(size)
                .fetch();
    }

    @Override
    public Poll findByPollIdAndAccountIdAndTargetIdAndWorkspaceId(Long pollId, String accountId, String targetId, String workspaceId) {
        return jpaQueryFactory.selectFrom(poll)
                .where(poll.id.eq(pollId),
                        poll.accountId.eq(accountId),
                        poll.workspaceId.eq(workspaceId),
                        poll.targetId.eq(targetId),
                        poll.pollStatus.eq(PollStatus.ACTIVE))
                .fetchOne();
    }

    private BooleanExpression existedCursor(Long cursor) {
        if (cursor == null) {
            return null;
        }
        return poll.id.lt(cursor);
    }

}
