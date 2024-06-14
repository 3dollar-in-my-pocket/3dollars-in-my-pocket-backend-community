package com.threedollar.service.post.request;

import com.threedollar.domain.post.Post;
import com.threedollar.domain.post.PostType;
import com.threedollar.domain.post.postsection.PostSection;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class PostSectionRequest {

    @NotNull
    private PostType postType;

    private int priority;

    private String url;

    private int width;

    private int height;

    @Builder
    public PostSectionRequest(PostType postType, int priority, String url, int width, int height) {
        this.postType = postType;
        this.priority = priority;
        this.url = url;
        this.width = width;
        this.height = height;
    }

    public PostSection toEntity(Post post) {

        return PostSection.of(postType, post, priority, url, width, height);

    }


}
