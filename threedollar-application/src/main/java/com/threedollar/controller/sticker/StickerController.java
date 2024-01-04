package com.threedollar.controller.sticker;

import com.threedollar.common.exception.dto.response.ApiResponse;
import com.threedollar.domain.sticker.StickerGroup;
import com.threedollar.service.reaction.dto.request.AddReactionRequest;
import com.threedollar.service.sticker.StickerService;
import com.threedollar.service.sticker.dto.response.StickerInfoResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class StickerController {

    private final StickerService stickerService;

    @Operation(summary = "[스티커] 스티커를 조회합니다", description = "그룹에 해당하는 스티커를 조회합니다")
    @GetMapping("/v1/sticker-group/{stickerGroup}/stickers")
    public ApiResponse<List<StickerInfoResponse>> getSticker(@PathVariable StickerGroup stickerGroup) {
        return ApiResponse.success(stickerService.getStickerList(stickerGroup));
    }

    @Operation(summary = "[스티커] 스티커를 추가합니다", description = "스티커가 이미 저장 되어있을 경우에는 제거 됩니다")
    @PostMapping("/v1/sticker-group/{stickerGroup}/sticker/{stickerId}/stickers")
    public ApiResponse<Long> createReaction(@Valid @RequestBody AddReactionRequest request,
                                            @PathVariable StickerGroup stickerGroup,
                                            @PathVariable String targetId) {
        return ApiResponse.success(stickerService.addSticker(request, stickerGroup));
    }

}
