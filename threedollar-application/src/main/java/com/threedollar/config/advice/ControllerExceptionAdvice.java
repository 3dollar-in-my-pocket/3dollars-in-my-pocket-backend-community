package com.threedollar.config.advice;

import com.threedollar.common.exception.dto.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import static com.threedollar.common.exception.ErrorCode.E400_INVALID;
import static java.util.stream.Collectors.joining;

@Slf4j
@RequiredArgsConstructor
@RestControllerAdvice
public class ControllerExceptionAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    private ApiResponse<Object> handleBadRequest(BindException exception) {
        String errorMessage = exception.getBindingResult().getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(joining("\n"));
        log.error("BindException: {}", errorMessage);
        return ApiResponse.error(E400_INVALID, errorMessage);
    }

}
