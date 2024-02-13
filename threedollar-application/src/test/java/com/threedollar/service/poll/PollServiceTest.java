package com.threedollar.service.poll;

import com.threedollar.IntegrationTest;
import com.threedollar.domain.poll.Poll;
import com.threedollar.domain.poll.PollCategory;
import com.threedollar.domain.poll.PollStatus;
import com.threedollar.domain.poll.repository.PollRepository;
import com.threedollar.service.poll.dto.request.PollCreateRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PollServiceTest extends IntegrationTest {

    @Autowired
    private PollService pollService;

    @Autowired
    private PollRepository pollRepository;

    @AfterEach
    void cleanUp() {
        pollRepository.deleteAll();
    }

    @Test
    void 투표를_추가합니다() {
        // given
        PollCategory pollCategory = PollCategory.BEST_FOOD;
        String accountId = "1L";
        String title = "제목";
        String targetId = "10";
        String content = "내용";
        String workspaceId = "3";
        LocalDateTime startDateTime = LocalDateTime.of(2024, 1, 2, 19, 0);
        LocalDateTime endDateTime = LocalDateTime.of(2024, 1, 31, 18, 59);

        PollCreateRequest request = PollCreateRequest.builder()
            .pollCategory(pollCategory)
            .targetId(targetId)
            .startTime(startDateTime)
            .endTime(endDateTime)
            .title(title)
            .content(content)
            .options(Collections.emptyList())
            .build();

        // when
        pollService.addPoll(request, accountId, workspaceId);

        // then
        List<Poll> polls = pollRepository.findAll();
        assertThat(polls).hasSize(1);
        assertPoll(polls.get(0), pollCategory, accountId, title, content, workspaceId, targetId);

    }

    @Test
    void 투표를_삭제합니다() {
        // given
        PollCategory pollCategory = PollCategory.BEST_FOOD;
        String accountId = "1L";
        String workspaceId = "3";
        String title = "제목";
        String content = "내용";
        String targetId = "10";
        LocalDateTime startDateTime = LocalDateTime.of(2024, 1, 2, 19, 0);
        LocalDateTime endDateTime = LocalDateTime.of(2024, 1, 31, 18, 59);
        Poll poll = pollRepository.save(Poll.newInstance(pollCategory, workspaceId, targetId, title, content, accountId, startDateTime, endDateTime));

        // when
        pollService.deletePoll(poll.getId(), accountId, targetId, workspaceId);

        // then
        List<Poll> polls = pollRepository.findAll();
        assertThat(polls).hasSize(1);
        assertThat(polls.get(0).getPollStatus()).isEqualTo(PollStatus.DELETED);

    }


    private void assertPoll(Poll poll, PollCategory pollCategory, String accountId, String title, String content, String workspaceId, String targetId) {
        assertThat(poll.getPollCategory()).isEqualTo(pollCategory);
        assertThat(poll.getAccountId()).isEqualTo(accountId);
        assertThat(poll.getTitle()).isEqualTo(title);
        assertThat(poll.getContent()).isEqualTo(content);
        assertThat(poll.getWorkspaceId()).isEqualTo(workspaceId);
        assertThat(poll.getTargetId()).isEqualTo(targetId);
    }
}
