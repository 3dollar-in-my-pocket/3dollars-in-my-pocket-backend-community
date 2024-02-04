package com.threedollar.service.apikey;

import com.threedollar.IntegrationTest;
import com.threedollar.common.exception.NotFoundException;
import com.threedollar.domain.apikey.ApiKey;
import com.threedollar.domain.apikey.ApiKeyRepository;
import com.threedollar.domain.apikey.ApiKeyStatus;
import com.threedollar.service.apikey.dto.response.ApiKeyResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ApiKeyQueryServiceTest extends IntegrationTest {

    @Autowired
    private ApiKeyQueryService apiKeyQueryService;

    @Autowired
    private ApiKeyRepository apiKeyRepository;

    @AfterEach
    void cleanUp() {
        apiKeyRepository.deleteAll();
    }

    @Test
    void Api_Key를_조회한다() {
        // given
        String key = "api-key";

        ApiKey apiKey = ApiKey.builder()
            .workspaceId("user-service-local")
            .description("유저 서비스 (로컬)")
            .apiKey(key)
            .status(ApiKeyStatus.ACTIVE)
            .build();
        apiKeyRepository.save(apiKey);

        // when
        ApiKeyResponse sut = apiKeyQueryService.getApiKey(key, ApiKeyStatus.ACTIVE);

        // then
        assertThat(sut.getApiKey()).isEqualTo(key);
        assertThat(sut.getWorkspaceId()).isEqualTo(apiKey.getWorkspaceId());
        assertThat(sut.getDescription()).isEqualTo(apiKey.getDescription());
    }

    @Test
    void 존재하지_않는_API_Key인경우_조회에_실패한다() {
        // given
        String key = "api-key";

        // when & then
        assertThatThrownBy(() -> apiKeyQueryService.getApiKey(key, ApiKeyStatus.ACTIVE)).isInstanceOf(NotFoundException.class);
    }

    @Test
    void 활성화된_API_Key_조회시_비활성화된_API_Key인경우_조회에_실패한다() {
        // given
        String key = "api-key";

        ApiKey apiKey = ApiKey.builder()
            .workspaceId("user-service-local")
            .description("유저 서비스 (로컬)")
            .apiKey(key)
            .status(ApiKeyStatus.DELETED)
            .build();
        apiKeyRepository.save(apiKey);

        // when & then
        assertThatThrownBy(() -> apiKeyQueryService.getApiKey(key, ApiKeyStatus.ACTIVE)).isInstanceOf(NotFoundException.class);
    }

}
