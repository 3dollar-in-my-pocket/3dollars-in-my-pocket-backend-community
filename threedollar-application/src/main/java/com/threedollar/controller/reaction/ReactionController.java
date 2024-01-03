package com.threedollar.controller.reaction;

import com.threedollar.common.exception.dto.response.ApiResponse;
import com.threedollar.service.reaction.ReactionService;
import com.threedollar.service.reaction.dto.request.AddReactionRequest;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class ReactionController {

    private final ReactionService reactionService;

    @Operation(summary = "[리액션] 리액션을 추가합니다", description = "리액션이 이미 저장 되어있을 경우 제거 됩니다")
    @PostMapping("/v1/reaction")
    public ApiResponse<Long> createReaction(@Valid @RequestBody AddReactionRequest request) {
        return ApiResponse.success(reactionService.addSticker(request));
    }


}
