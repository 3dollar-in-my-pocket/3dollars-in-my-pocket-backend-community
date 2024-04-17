package com.threedollar.controller;

import com.threedollar.common.dto.response.ApiResponse;
import com.threedollar.common.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.availability.ApplicationAvailability;
import org.springframework.boot.availability.LivenessState;
import org.springframework.boot.availability.ReadinessState;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class HealthCheckController {

    private final ApplicationAvailability applicationAvailability;

    @GetMapping("/health/liveness")
    public ResponseEntity<ApiResponse<String>> livenessCheck() {
        if (LivenessState.CORRECT != applicationAvailability.getLivenessState()) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(ApiResponse.error(ErrorCode.E503_SERVICE_UNAVAILABLE));
        }
        return ResponseEntity.ok(ApiResponse.OK);
    }

    @GetMapping({"/ping", "/health/readiness"})
    public ResponseEntity<ApiResponse<String>> readinessCheck() {
        if (ReadinessState.ACCEPTING_TRAFFIC != applicationAvailability.getReadinessState()) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(ApiResponse.error(ErrorCode.E503_SERVICE_UNAVAILABLE));
        }
        return ResponseEntity.ok(ApiResponse.OK);
    }

}
