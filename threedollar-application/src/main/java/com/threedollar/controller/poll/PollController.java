package com.threedollar.controller.poll;


import com.threedollar.common.dto.response.ApiResponse;
import com.threedollar.config.interceptor.ApiKeyContext;
import com.threedollar.config.resolver.RequestApiKey;
import com.threedollar.service.poll.dto.request.PollCreateRequest;
import com.threedollar.service.poll.PollService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class PollController {

    private final PollService pollService;

    @Operation(summary = "[투표] 투표를 합니다", description = "유저 혹은 사장님이 투표를 추가합니다.")
    @PostMapping("/v1/poll")
    public ApiResponse<Long> createPoll(@Valid @RequestBody PollCreateRequest request,
                                        @NotBlank @RequestParam String accountId,
                                        @RequestApiKey ApiKeyContext workspaceId) {
        Long pollId = pollService.addPoll(request, accountId, String.valueOf(workspaceId));
        return ApiResponse.success(pollId);
    }

    @Operation(summary = "[투표] 투표를 삭제합니다", description = "유저 혹은 사장님이 투표를 제거합니다.")
    @DeleteMapping("/v1/poll/{pollId}")
    public ApiResponse<String> deletePoll(@PathVariable Long pollId,
                                          @RequestParam String accountId,
                                          @RequestParam String targetId,
                                          @RequestApiKey ApiKeyContext workspaceId) {
        pollService.deletePoll(pollId, accountId, targetId, String.valueOf(workspaceId));
        return ApiResponse.OK;

    }


}
