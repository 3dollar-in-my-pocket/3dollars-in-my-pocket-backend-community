package com.threedollar.controller;


import com.threedollar.common.exception.dto.response.ApiResponse;
import com.threedollar.domain.AccountType;
import com.threedollar.service.poll.dto.request.PollCreateRequest;
import com.threedollar.service.poll.service.PollService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
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
    public ApiResponse<Long> createPoll(@Valid @RequestBody PollCreateRequest request,
                                          @NotNull @RequestParam AccountType accountType,
                                          @NotBlank @RequestParam String accountId) {
        Long pollId = pollService.addPoll(request, accountType, accountId);
        return ApiResponse.success(pollId);
    }

    @Operation(summary = "[투표] 투표를 삭제합니다", description = "유저 혹은 사장님이 투표를 제거합니다.")
    @DeleteMapping("/v1/poll/{pollId}")
    public ApiResponse<String> deletePoll(@PathVariable Long pollId,
                                          @RequestParam AccountType accountType,
                                          @RequestParam String accountId) {
        pollService.deletePoll(pollId, accountType, accountId);
        return ApiResponse.OK;

    }


}
