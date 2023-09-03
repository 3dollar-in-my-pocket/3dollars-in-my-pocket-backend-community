package com.threedollar.service.poll.dto;

import com.threedollar.domain.poll.Poll;
import com.threedollar.domain.poll.repository.PollRepository;
import com.threedollar.service.poll.dto.request.AddPollRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PollService {

    private final PollRepository pollRepository;

    @Transactional
    public void addPoll(AddPollRequest request) {
        Poll poll = request.toEntity();
        pollRepository.save(poll);
    }

}
