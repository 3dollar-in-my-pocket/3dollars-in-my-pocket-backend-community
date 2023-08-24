package com.threedollar.threedollarservice.controller.poll;

import com.threedollar.threedollarservice.service.poll.PollService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor
@Getter
@RequiredArgsConstructor
public class PollController {

    private PollService pollService;

    public PollController(PollService pollService) {
        this.pollService = pollService;
    }
}
