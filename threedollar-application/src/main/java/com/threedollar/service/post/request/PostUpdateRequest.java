package com.threedollar.service.post.request;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
public class PostUpdateRequest {

    @Nullable
    private String title;

    @Nullable
    private String content;

    @NotNull
    private List<PostSectionRequest> postSections;

    @Builder
    public PostUpdateRequest(String title, String content, List<PostSectionRequest> postSections) {
        this.title = title;
        this.content = content;
        this.postSections = postSections;
    }


}
