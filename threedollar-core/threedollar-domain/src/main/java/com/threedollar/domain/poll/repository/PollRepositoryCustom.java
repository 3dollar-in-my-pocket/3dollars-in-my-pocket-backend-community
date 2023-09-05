package com.threedollar.domain.poll.repository;


import com.threedollar.domain.poll.Poll;
import com.threedollar.domain.poll.PollType;

import java.util.List;

public interface PollRepositoryCustom {

    List<Poll> findAllPollList(Long cursor, int size, PollType pollType);



}
