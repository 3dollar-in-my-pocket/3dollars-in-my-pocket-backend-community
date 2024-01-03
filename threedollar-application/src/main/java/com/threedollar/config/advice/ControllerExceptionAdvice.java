package com.threedollar.config.advice;

import com.threedollar.common.exception.dto.response.ApiResponse;
import com.threedollar.common.exception.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;



import static com.threedollar.common.exception.ErrorCode.E400_INVALID;

@Slf4j
@RestControllerAdvice
public class ControllerExceptionAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    private ApiResponse<Object> handleBadRequest(BindException e) {
        log.error(e.getMessage(), e);
        return ApiResponse.error(E400_INVALID, e.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    private ApiResponse<Object> handleNotFound(NotFoundException e) {
        log.error(e.getErrorCode().getMessage(), e);
        return ApiResponse.error(e.getErrorCode(), e.getMessage());
    }

}
