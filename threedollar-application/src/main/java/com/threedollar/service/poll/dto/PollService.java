package com.threedollar.service.poll.dto;

import com.threedollar.domain.poll.Poll;
import com.threedollar.domain.poll.PollType;
import com.threedollar.domain.poll.repository.PollRepository;
import com.threedollar.service.poll.dto.request.AddPollRequest;
import com.threedollar.service.poll.dto.response.AllPollResponse;
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
    public Long addPoll(AddPollRequest request) {
        Poll poll = request.toEntity();
        return pollRepository.save(poll).getId();
    }

    public List<PollTypeResponse> getPollTypes() {
        return PollType.pollTypeList().stream()
                .map(PollTypeResponse::of)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<AllPollResponse> getAllPollResponse(Long cursor, int size, PollType pollType) {
        int checkSize = size + 1;

        List<Poll> pollList = pollRepository.findAllPollList(cursor, checkSize, pollType);
        if (pollList.size() == checkSize) {

        }

        pollList.get(size);


        boolean validateEmpty = validateEmpty(pollList);
        return pollList.stream()
                .map(AllPollResponse::of)
                .collect(Collectors.toList());
    }

    private boolean validateEmpty(List<Poll> pollList) {
        return !pollList.isEmpty();
    }


}
