package com.threedollar.config.interceptor;

import com.threedollar.common.enums.CommunityHttpHeaders;
import com.threedollar.common.exception.ApiKeyInvalidException;
import com.threedollar.common.exception.NotFoundException;
import com.threedollar.domain.apikey.ApiKeyStatus;
import com.threedollar.service.apikey.ApiKeyQueryService;
import com.threedollar.service.apikey.dto.response.ApiKeyResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import static com.threedollar.config.interceptor.ApiKeyContext.SESSION_KEY;

@Component
@RequiredArgsConstructor
public class ApiKeyHandleInterceptor implements HandlerInterceptor {

    private final ApiKeyQueryService apiKeyQueryService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String requestApiKey = request.getHeader(CommunityHttpHeaders.API_KEY.getHeaderName());
        if (StringUtils.isBlank(requestApiKey)) {
            throw new ApiKeyInvalidException("Api-Key 헤더가 비어있습니다.");
        }

        try {
            ApiKeyResponse apiKey = apiKeyQueryService.getApiKey(requestApiKey, ApiKeyStatus.ACTIVE);
            request.setAttribute(SESSION_KEY, ApiKeyContext.from(apiKey));
            return true;
        } catch (NotFoundException exception) {
            throw new ApiKeyInvalidException(String.format("등록되지 않은 Api-Key(%s)가 요청 되었습니다.", requestApiKey));
        }
    }
}
