package com.threedollar.service.post.request;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
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

    @NotBlank
    private String workspaceId;

    @NotBlank
    private String targetId;

    public PostAndCursorRequest(Long cursor, int size, String workspaceId, String targetId) {
        this.cursor = cursor;
        this.size = size;
        this.workspaceId = workspaceId;
        this.targetId = targetId;
    }
}
