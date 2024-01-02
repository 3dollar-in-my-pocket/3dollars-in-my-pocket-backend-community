package com.threedollar.controller;


import com.threedollar.common.exception.dto.response.ApiResponse;
import com.threedollar.domain.AccountType;
import com.threedollar.service.poll.dto.request.PollCreateRequest;
import com.threedollar.service.poll.service.PollService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@RestController
@RequiredArgsConstructor
public class PollController {

    private final PollService pollService;

    @Operation(summary = "[투표] 투표를 합니다", description = "유저 혹은 사장님이 투표를 추가합니다.")
    @PostMapping("/v1/poll")
    public ApiResponse<Long> createPoll(@Valid PollCreateRequest request,
                                          @NotNull AccountType accountType,
                                          @NotBlank String requestId) {
        Long pollId = pollService.addPoll(request, accountType, requestId);
        return ApiResponse.success(pollId);
    }


}
