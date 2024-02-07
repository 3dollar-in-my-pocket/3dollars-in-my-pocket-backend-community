package com.threedollar.controller.sticker;

import com.threedollar.common.dto.response.ApiResponse;
import com.threedollar.domain.sticker.StickerGroup;
import com.threedollar.service.sticker.StickerFacadeService;
import com.threedollar.service.sticker.dto.response.TargetStickerAction;
import com.threedollar.service.sticker.dto.request.AddStickerActionRequest;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@RestController
public class StickerController {

    private final StickerFacadeService stickerFacadeService;

    @Operation(summary = "[스티커] 스티커를 추가합니다", description = "스티커가 이미 저장 되어있을 경우에는 제거 됩니다")
    @PostMapping("/v1/sticker-group/{stickerGroup}/stickers")
    public ApiResponse<String> createStickerAction(@Valid @RequestBody AddStickerActionRequest request,
                                                   @PathVariable StickerGroup stickerGroup) {
        stickerFacadeService.upsertSticker(request, stickerGroup);
        return ApiResponse.OK;
    }

    @Operation(summary = "[스티커] 스티커를 제거합니다", description = "스티커를 제거합니다")
    @DeleteMapping("/v1/sticker-group/{stickerGroup}/stickers")
    public ApiResponse<String> deleteStickerAction(@PathVariable StickerGroup stickerGroup,
                                                   @RequestParam String workspaceId,
                                                   @RequestParam String targetId,
                                                   @RequestParam String accountId) {
        stickerFacadeService.deleteSticker(stickerGroup, workspaceId, targetId, accountId);
        return ApiResponse.OK;
    }

    @Operation(summary = "[스티커] 타겟들에 대한 스티커들을 조회합니다")
    @GetMapping("/v1/sticker-group/{stickerGroup}/stickers")
    public ApiResponse<List<TargetStickerAction>> getTargetStickerActions(@PathVariable StickerGroup stickerGroup,
                                                                          @RequestParam String workspaceId,
                                                                          @RequestParam Set<String> targetIds,
                                                                          @RequestParam(required = false) String accountId) {
        return ApiResponse.success(stickerFacadeService.getTargetStickers(stickerGroup, workspaceId, accountId, targetIds));
    }

}
