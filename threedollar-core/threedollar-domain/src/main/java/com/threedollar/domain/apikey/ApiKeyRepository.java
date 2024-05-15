package com.threedollar.domain.apikey;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApiKeyRepository extends JpaRepository<ApiKey, Long> {

    @Nullable
    ApiKey findByApiKeyAndStatus(@NotNull String apiKey, @NotNull ApiKeyStatus status);
}
