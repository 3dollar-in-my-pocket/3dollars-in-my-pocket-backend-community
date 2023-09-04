package com.threedollar.controller.poll;

import com.threedollar.common.exception.dto.response.ApiResponse;
import com.threedollar.service.poll.dto.PollService;
import com.threedollar.service.poll.dto.request.AddPollRequest;
import com.threedollar.service.poll.dto.response.PollTypeResponse;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class PollController {

    private final PollService pollService;

    @ApiOperation("[투표] 투표를 합니다")
    @PostMapping("/community/v1/poll")
    public ApiResponse<String> addPoll(@Valid @RequestBody AddPollRequest request) {
        pollService.addPoll(request);
        return ApiResponse.OK;
    }

    @ApiOperation("[투표] 투표의 형태를 조회합니다")
    @PostMapping("/community/v1/poll-type")
    public ApiResponse<List<PollTypeResponse>> getPollTypeList() {
        return ApiResponse.success(pollService.getPollTypes());
    }



}
