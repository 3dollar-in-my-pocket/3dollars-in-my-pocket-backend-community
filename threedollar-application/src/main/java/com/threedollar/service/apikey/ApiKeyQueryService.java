package com.threedollar.service.apikey;

import com.threedollar.common.exception.NotFoundException;
import com.threedollar.domain.apikey.ApiKey;
import com.threedollar.domain.apikey.ApiKeyRepository;
import com.threedollar.domain.apikey.ApiKeyStatus;
import com.threedollar.service.apikey.dto.response.ApiKeyResponse;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ApiKeyQueryService {

    private final ApiKeyRepository apiKeyRepository;

    @Cacheable(cacheNames = "apiKeys", key = "#key")
    @Transactional(readOnly = true)
    public ApiKeyResponse getApiKey(@NotNull String key, @NotNull ApiKeyStatus status) {
        ApiKey findApiKey = apiKeyRepository.findByApiKeyAndStatus(key, status);
        if (findApiKey == null) {
            throw new NotFoundException(String.format("해당하는 status(%s)의 ApiKey(%s)는 존재하지 않습니다.", status, key));
        }
        return ApiKeyResponse.from(findApiKey);
    }


}
