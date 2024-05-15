package com.threedollar.service.poll;

import com.threedollar.domain.poll.Poll;
import com.threedollar.domain.poll.repository.PollRepository;
import com.threedollar.service.poll.dto.request.PollCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


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


}
