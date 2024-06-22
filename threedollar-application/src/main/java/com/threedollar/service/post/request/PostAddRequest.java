package com.threedollar.service.post.request;


import com.threedollar.domain.post.Post;
import com.threedollar.domain.post.PostGroup;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class PostAddRequest {

    @NotNull
    private PostGroup postGroup;

    private Long parentId;

    private String title;

    private String workspaceId;

    @NotBlank
    private String content;

    @NotNull
    private List<PostSectionRequest> sections;

    @Builder
    public PostAddRequest(PostGroup postGroup, Long parentId, String title, String content, String workspaceId, List<PostSectionRequest> sections) {
        this.postGroup = postGroup;
        this.parentId = parentId;
        this.title = title;
        this.workspaceId = workspaceId;
        this.content = content;
        this.sections = sections;
    }

    public Post toEntity(String workspaceId, String accountId) {

        Post post = Post.of(postGroup, parentId, workspaceId, title, content, accountId);

        for (PostSectionRequest section : sections) {
            post.add(section.toEntity(post));
        }
        return post;

    }


}
