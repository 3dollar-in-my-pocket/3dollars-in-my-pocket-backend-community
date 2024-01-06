package com.threedollar.controller;

import com.threedollar.common.exception.dto.response.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping("/ping")
    public ApiResponse<String> check() {
        return ApiResponse.OK;
    }

}
