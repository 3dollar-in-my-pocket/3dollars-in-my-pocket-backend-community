package com.threedollar.domain.poll.repository;


import com.threedollar.domain.poll.Poll;

public interface PollRepositoryCustom {

    Poll findByPollIdAndAccountIdAndTargetIdAndWorkspaceId(Long pollId, String accountId, String targetId, String workspaceId);


}
