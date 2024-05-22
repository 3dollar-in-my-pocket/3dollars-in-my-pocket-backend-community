package com.threedollar.domain.poll.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.threedollar.domain.poll.Poll;
import com.threedollar.domain.poll.PollStatus;
import lombok.RequiredArgsConstructor;


import static com.threedollar.domain.poll.QPoll.poll;

@RequiredArgsConstructor
public class PollRepositoryCustomImpl implements PollRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;

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


}
