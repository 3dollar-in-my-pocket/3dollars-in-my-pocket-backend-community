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

    private String title;

    private String content;

    private String accountId;

    private String targetId;

    private List<PostSectionResponse> postSections;

    @Builder
    public PostResponse(Long postId, PostGroup postGroup, Long parentId, String title, String content, String accountId, String targetId, List<PostSectionResponse> postSections) {
        this.postId = postId;
        this.postGroup = postGroup;
        this.parentId = parentId;
        this.title = title;
        this.content = content;
        this.accountId = accountId;
        this.targetId = targetId;
        this.postSections = postSections;
    }

    public static PostResponse of(Post post) {
        return PostResponse.builder()
            .postId(post.getId())
            .postGroup(post.getPostGroup())
            .parentId(post.getParentId())
            .title(post.getTitle())
            .content(post.getContent())
            .accountId(post.getAccountId())
            .targetId(post.getTargetId())
            .postSections(getPostSectionResponses(post))
            .build();

    }

    public static List<PostSectionResponse> getPostSectionResponses(Post post) {
        return post.getPostSection().stream()
            .map(PostSectionResponse::of)
            .collect(Collectors.toList());
    }

}
