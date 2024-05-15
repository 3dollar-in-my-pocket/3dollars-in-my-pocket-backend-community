package com.threedollar.domain.poll.repository;


import com.threedollar.domain.poll.Poll;
import com.threedollar.domain.poll.PollCategory;

import java.util.List;

public interface PollRepositoryCustom {

    List<Poll> findAllPollList(Long cursor, int size, PollCategory pollCategory);

    Poll findByPollIdAndAccountIdAndTargetIdAndWorkspaceId(Long pollId, String accountId, String targetId, String workspaceId);


}
