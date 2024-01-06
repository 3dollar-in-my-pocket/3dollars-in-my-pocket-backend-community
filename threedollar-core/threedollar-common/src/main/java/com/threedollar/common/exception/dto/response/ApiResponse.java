package com.threedollar.common.exception.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.threedollar.common.exception.ErrorCode;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@EqualsAndHashCode
@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {

    public static final ApiResponse<String> OK = success(null);

    private boolean ok;

    private String error;

    private String message;

    private T data;

    private ApiResponse(boolean ok, String error, String message, T data) {
        this.ok = ok;
        this.error = error;
        this.message = message;
        this.data = data;
    }

    @NotNull
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(true, null, null, data);
    }

    @NotNull
    public static <T> ApiResponse<T> error(ErrorCode errorCode) {
        return new ApiResponse<>(false, errorCode.getCode(), errorCode.getMessage(), null);
    }

    @NotNull
    public static <T> ApiResponse<T> error(ErrorCode errorCode, String message) {
        return new ApiResponse<>(false, errorCode.getCode(), message, null);
    }


}
