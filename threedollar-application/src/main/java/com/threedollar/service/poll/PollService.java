package com.threedollar.service.poll;

import com.threedollar.domain.poll.Poll;
import com.threedollar.domain.poll.PollCategory;
import com.threedollar.domain.poll.repository.PollRepository;
import com.threedollar.service.poll.dto.request.PollCreateRequest;
import com.threedollar.service.poll.dto.response.AllPollResponse;
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
    public Long addPoll(PollCreateRequest request, String accountId, String workspaceId) {
        Poll poll = request.toEntity(accountId, workspaceId);
        return pollRepository.save(poll).getId();
    }

    @Transactional
    public void deletePoll(Long pollId, String accountId, String targetId, String workspaceId) {
        Poll poll = PollServiceHelper.getPollByIdAndAccountTypeAndWorkspaceId(pollRepository, pollId, accountId, targetId, workspaceId);
        poll.delete();
    }



    @Transactional(readOnly = true)
    public List<AllPollResponse> getAllPollResponse(Long cursor, int size, PollCategory pollCategory) {
        int checkSize = size + 1;

        List<Poll> pollList = pollRepository.findAllPollList(cursor, checkSize, pollCategory);
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
