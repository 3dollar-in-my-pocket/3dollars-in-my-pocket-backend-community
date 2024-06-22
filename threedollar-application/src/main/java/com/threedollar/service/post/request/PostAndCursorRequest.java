package com.threedollar.service.post.request;

import com.threedollar.domain.post.PostGroup;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class PostAndCursorRequest {

    @Nullable
    private Long cursor;

    @Min(0)
    @Max(30)
    private int size;

    private String workspaceId;

    private String accountId;

    private PostGroup postGroup;

    public PostAndCursorRequest(Long cursor, int size, String workspaceId, String accountId, PostGroup postGroup) {
        this.cursor = cursor;
        this.size = size;
        this.workspaceId = workspaceId;
        this.accountId = accountId;
        this.postGroup = postGroup;
    }
}
