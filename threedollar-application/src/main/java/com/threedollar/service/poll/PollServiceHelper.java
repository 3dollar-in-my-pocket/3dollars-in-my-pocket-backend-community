package com.threedollar.service.poll;

import com.threedollar.common.exception.NotFoundException;
import com.threedollar.domain.AccountType;
import com.threedollar.domain.poll.Poll;
import com.threedollar.domain.poll.repository.PollRepository;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PollServiceHelper {

    public static Poll getPollByIdAndAccountType(PollRepository pollRepository, Long pollId, AccountType accountType, String accountId) {
        Poll poll = pollRepository.findByPollIdAndAccount(pollId, accountType, accountId);
        if(poll == null) {
            throw new NotFoundException(String.format("(%s)에 해당하는 투표는 존재하지 않습니다.", pollId));
        }
        return poll;
    }
}
