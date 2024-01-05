package com.threedollar.common.exception.dto.response;

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
public class ApiResponse<T>{

    public static final ApiResponse<String> OK = success("OK");

    private String resultCode;

    private String message;

    private T data;

    public ApiResponse(String resultCode, String message, T data) {
        this.resultCode = resultCode;
        this.message = message;
        this.data = data;
    }

    @NotNull
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>("", "", data);
    }

    @NotNull
    public static <T> ApiResponse<T> error(ErrorCode errorCode) {
        return new ApiResponse<>(errorCode.getCode(), errorCode.getMessage(), null);
    }

    @NotNull
    public static <T> ApiResponse<T> error(ErrorCode errorCode, String message) {
        return new ApiResponse<>(errorCode.getCode(), message, null);
    }


}
