package com.threedollar.service.post.response;

import com.threedollar.domain.post.Post;
import com.threedollar.domain.post.PostGroup;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
public class PostResponse {

    private Long postId;

    private PostGroup postGroup;

    private Long parentId;

    private String workspaceId;

    private String title;

    private String content;

    private String accountId;

    private List<PostSectionResponse> postSections;

    @Builder
    public PostResponse(Long postId, PostGroup postGroup, Long parentId, String workspaceId, String title, String content, String accountId, List<PostSectionResponse> postSections) {
        this.postId = postId;
        this.postGroup = postGroup;
        this.parentId = parentId;
        this.workspaceId = workspaceId;
        this.title = title;
        this.content = content;
        this.accountId = accountId;
        this.postSections = postSections;
    }

    public static PostResponse of(Post post) {
        return PostResponse.builder()
            .postId(post.getId())
            .postGroup(post.getPostGroup())
            .parentId(post.getParentId())
            .workspaceId(post.getWorkspaceId())
            .title(post.getTitle())
            .content(post.getContent())
            .accountId(post.getAccountId())
            .postSections(getPostSectionResponses(post))
            .build();

    }

    public static List<PostSectionResponse> getPostSectionResponses(Post post) {
        return post.getPostSection().stream()
            .map(PostSectionResponse::of)
            .collect(Collectors.toList());
    }

}
