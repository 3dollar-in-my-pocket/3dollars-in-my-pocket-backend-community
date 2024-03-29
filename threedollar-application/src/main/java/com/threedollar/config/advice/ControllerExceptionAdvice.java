package com.threedollar.config.advice;

import com.threedollar.common.dto.response.ApiResponse;
import com.threedollar.common.exception.BaseException;
import com.threedollar.common.exception.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @ExceptionHandler(BaseException.class)
    private ResponseEntity<ApiResponse<Object>> handleBaseException(BaseException e) {
        log.error(e.getErrorCode().getMessage(), e);
        return ResponseEntity.status(e.getErrorCode().getStatus())
            .body(ApiResponse.error(e.getErrorCode()));
    }

    @ExceptionHandler(Throwable.class)
    private ResponseEntity<ApiResponse<Object>> handleThrowable(Throwable throwable) {
        if (throwable.getCause() instanceof BaseException baseException) {
            log.error(throwable.getMessage(), throwable);
            return ResponseEntity.status(baseException.getErrorCode().getStatus())
                .body(ApiResponse.error(baseException.getErrorCode()));
        }

        log.error(throwable.getMessage(), throwable);
        return ResponseEntity.internalServerError()
            .body(ApiResponse.error(ErrorCode.E500_INTERNAL_SERVER));
    }

}
