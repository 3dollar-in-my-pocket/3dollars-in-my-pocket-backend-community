package com.threedollar.service;

import com.threedollar.service.options.dto.request.OptionsRequest;
import com.threedollar.service.poll.dto.PollService;
import com.threedollar.service.poll.dto.request.AddPollRequest;
import com.threedollar.domain.poll.Poll;
import com.threedollar.domain.poll.PollType;
import com.threedollar.domain.poll.repository.PollRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PollServiceTest {

    @Autowired
    private PollService pollService;

    @Autowired
    private PollRepository pollRepository;

    @Test
    void 사용자가_커뮤니티에_투표를_올린다() {
        // given
        String title = "팥붕 vs 슈붕";
        String contents = "팥붕 대 슈붕 어떤걸 먹어야 좋을 까요?";
        PollType pollType = PollType.TASTE_VS_TASTE;
        String accountId = "1L";
        String accountType = "USER";
        LocalDateTime startTime = LocalDateTime.of(2023, 8, 31, 9, 0);
        LocalDateTime endTime = LocalDateTime.of(2023, 9, 15, 0, 0);


        OptionsRequest optionsRequest = new OptionsRequest("제목", "내용", "imageUrl");


        AddPollRequest request = AddPollRequest
                .builder()
                .title(title)
                .pollType(pollType)
                .content(contents)
                .accountId(accountId)
                .accountType(accountType)
                .startTime(startTime)
                .endTime(endTime)
                .optionsRequestList(List.of(optionsRequest))
                .build();

        // when
        pollService.addPoll(request);


        // then
        List<Poll> pollList = pollRepository.findAll();
        assertThat(pollList).hasSize(1);
        assertThat(title).isEqualTo(pollList.get(0).getTitle());
        assertThat(pollType).isEqualTo(pollList.get(0).getPollType());
        assertThat(contents).isEqualTo(pollList.get(0).getContent());
        assertThat(accountId).isEqualTo(pollList.get(0).getAccountId());
        assertThat(accountType).isEqualTo(pollList.get(0).getAccountType());
        assertThat(startTime).isEqualTo(pollList.get(0).getStartTime());
        assertThat(endTime).isEqualTo(pollList.get(0).getEndTime());


    }
}
