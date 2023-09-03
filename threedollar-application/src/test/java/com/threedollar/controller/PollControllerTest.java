package com.threedollar.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.threedollar.controller.poll.PollController;
import com.threedollar.domain.poll.PollType;
import com.threedollar.service.poll.dto.PollService;
import com.threedollar.service.poll.dto.request.AddPollRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PollController.class)
class PollControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @MockBean
    private PollService pollService;


    @Test
    void 투표를_등록한다() throws Exception {
        // given
        AddPollRequest request = AddPollRequest
                .builder()
                .accountId("1")
                .title("title")
                .accountType("USER")
                .content("title")
                .startTime(LocalDateTime.of(2023, 9, 3,22,3))
                .endTime(LocalDateTime.of(2023, 12, 31, 0, 0))
                .pollType(PollType.TASTE_VS_TASTE)
                .build();



        // when & then
        mockMvc.perform(post("/community/v1/poll")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

}
