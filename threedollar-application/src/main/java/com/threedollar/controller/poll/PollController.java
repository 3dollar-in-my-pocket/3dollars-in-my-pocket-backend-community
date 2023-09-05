package com.threedollar.controller.poll;

import com.threedollar.common.exception.dto.response.ApiResponse;
import com.threedollar.domain.poll.PollType;
import com.threedollar.service.poll.dto.PollService;
import com.threedollar.service.poll.dto.request.AddPollRequest;
import com.threedollar.service.poll.dto.response.AllPollResponse;
import com.threedollar.service.poll.dto.response.PollTypeResponse;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class PollController {

    private final static String defaultPollType = "DEFAULT_POLL_TYPE";
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

    @ApiOperation("[투표] 투표 메인 화면을 최신순으로 조회합니다")
    @GetMapping("/community/v1/poll")
    public ApiResponse<List<AllPollResponse>> getAllPoll(@RequestParam(required = false) Long cursor,
                                                         @RequestParam int size,
                                                         @RequestParam(required = false, defaultValue = defaultPollType) PollType pollType) {
        return ApiResponse.success(pollService.getAllPollResponse(cursor, size, pollType));
    }


}