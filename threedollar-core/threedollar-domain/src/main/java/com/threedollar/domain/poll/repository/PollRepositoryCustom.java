package com.threedollar.domain.poll.repository;


import com.threedollar.domain.AccountType;
import com.threedollar.domain.poll.Poll;
import com.threedollar.domain.poll.PollCategory;

import java.util.List;

public interface PollRepositoryCustom {

    List<Poll> findAllPollList(Long cursor, int size, PollCategory pollCategory);

    Poll findByPollIdAndAccountAndWorkspaceId(Long pollId, AccountType accountType, String accountId, String workspaceId);


}
