package com.threedollar.controller.sticker;

import com.threedollar.common.exception.dto.response.ApiResponse;
import com.threedollar.domain.sticker.StickerGroup;
import com.threedollar.service.sticker.StickerService;
import com.threedollar.service.sticker.dto.response.StickerInfoResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class StickerController {

    private final StickerService stickerService;

    @Operation(summary = "[스티커] 스티커를 조회합니다")
    @GetMapping("/v1/stickers")
    public ApiResponse<List<StickerInfoResponse>> getSticker(@RequestParam StickerGroup group) {
        return ApiResponse.success(stickerService.getStickerList(group));
    }
}
