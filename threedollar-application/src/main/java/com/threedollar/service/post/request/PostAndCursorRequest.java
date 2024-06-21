package com.threedollar.service.post.request;

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

    public PostAndCursorRequest(Long cursor, int size, String workspaceId, String accountId) {
        this.cursor = cursor;
        this.size = size;
        this.workspaceId = workspaceId;
        this.accountId = accountId;
    }
}
