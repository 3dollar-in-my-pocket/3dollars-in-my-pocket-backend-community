package com.threedollar.service.poll.dto;

import com.threedollar.domain.poll.Poll;
import com.threedollar.domain.poll.PollType;
import com.threedollar.domain.poll.repository.PollRepository;
import com.threedollar.service.poll.dto.request.AddPollRequest;
import com.threedollar.service.poll.dto.response.PollTypeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PollService {

    private final PollRepository pollRepository;

    @Transactional
    public void addPoll(AddPollRequest request) {
        Poll poll = request.toEntity();
        pollRepository.save(poll);
    }

    public List<PollTypeResponse> getPollTypes() {
        return PollType.pollTypeList().stream()
                .map(PollTypeResponse::of)
                .collect(Collectors.toList());
    }


}
