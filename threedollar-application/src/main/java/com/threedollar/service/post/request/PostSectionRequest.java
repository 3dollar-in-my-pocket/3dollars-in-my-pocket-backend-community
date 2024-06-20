package com.threedollar.service.post.request;

import com.threedollar.domain.post.Post;
import com.threedollar.domain.post.SectionType;
import com.threedollar.domain.post.postsection.PostSection;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class PostSectionRequest {

    @NotNull
    private SectionType sectionType;

    private int priority;

    private String url;

    private int width;

    private int height;

    @Builder
    public PostSectionRequest(SectionType sectionType, int priority, String url, int width, int height) {
        this.sectionType = sectionType;
        this.priority = priority;
        this.url = url;
        this.width = width;
        this.height = height;
    }

    public PostSection toEntity(Post post) {

        return PostSection.of(sectionType, post, priority, url, width, height);

    }


}
