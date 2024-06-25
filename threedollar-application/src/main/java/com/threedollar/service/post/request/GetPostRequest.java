package com.threedollar.service.post.request;

import com.threedollar.domain.post.PostGroup;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class GetPostRequest {

    @NotBlank
    private String accountId;

    @NotBlank
    private String workspaceId;

    private PostGroup postGroup;

    private String targetId;

    public GetPostRequest(String accountId, String workspaceId, PostGroup postGroup, String targetId) {
        this.accountId = accountId;
        this.workspaceId = workspaceId;
        this.postGroup = postGroup;
        this.targetId = targetId;
    }
}
