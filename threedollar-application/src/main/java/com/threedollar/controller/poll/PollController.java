package com.threedollar.controller.poll;

import com.threedollar.common.exception.dto.response.ApiResponse;
import com.threedollar.service.poll.dto.PollService;
import com.threedollar.service.poll.dto.request.AddPollRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class PollController {

    private final PollService pollService;

    @PostMapping("/community/v1/poll")
    public ApiResponse<String> addPoll(@Valid @RequestBody AddPollRequest request) {
        pollService.addPoll(request);
        return ApiResponse.OK;
    }


}
