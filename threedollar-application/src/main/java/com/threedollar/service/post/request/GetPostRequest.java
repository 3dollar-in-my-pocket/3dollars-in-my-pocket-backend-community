package com.threedollar.service.post.request;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class GetPostRequest {

    @Nullable
    private String accountId;

    @NotBlank
    private String workspaceId;

    @NotBlank
    private String targetId;

    public GetPostRequest(String accountId, String workspaceId, String targetId) {
        this.accountId = accountId;
        this.workspaceId = workspaceId;
        this.targetId = targetId;
    }
}
