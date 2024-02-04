package com.threedollar.domain.apikey;

import com.threedollar.common.exception.BaseException;
import com.threedollar.common.exception.ErrorCode;

public class ApiKeyInvalidException extends BaseException {

    public ApiKeyInvalidException(String message) {
        super(message, ErrorCode.E401_INVALID_API_KEY);
    }

}
