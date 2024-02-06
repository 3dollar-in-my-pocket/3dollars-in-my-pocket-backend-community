package com.threedollar.config.interceptor;

import com.threedollar.service.apikey.dto.response.ApiKeyResponse;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@Getter
public class ApiKeyContext {

    public static final String SESSION_KEY = "API_KEY_CONTEXT";

    @NotNull
    private final String workspaceId;

    private ApiKeyContext(@NotNull String workspaceId) {
        this.workspaceId = workspaceId;
    }

    @NotNull
    public static ApiKeyContext from(@NotNull ApiKeyResponse apiKey) {
        return new ApiKeyContext(apiKey.getWorkspaceId());
    }
}
