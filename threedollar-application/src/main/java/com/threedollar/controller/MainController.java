package com.threedollar.controller;

import com.threedollar.common.dto.response.ApiResponse;
import com.threedollar.config.interceptor.ApiKeyContext;
import com.threedollar.config.resolver.RequestApiKey;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class MainController {

    @GetMapping("/ping")
    public ApiResponse<String> check() {
        return ApiResponse.OK;
    }

    @Operation(summary = "[API-Key] 유효한 Api-Key인지 검증합니다 (테스트 목적)")
    @GetMapping("/v1/api-key")
    public ApiResponse<String> checkApiKey(
        @RequestApiKey ApiKeyContext apiKeyContext
    ) {
        log.info("workspaceId: {}", apiKeyContext.getWorkspaceId());
        return ApiResponse.OK;
    }

}
