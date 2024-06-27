package com.threedollar.service.post.response;

import com.threedollar.domain.post.Post;
import com.threedollar.domain.post.PostGroup;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
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

    private String targetId;

    private Boolean isOwner;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private List<PostSectionResponse> postSections;

    @Builder
    public PostResponse(Long postId, PostGroup postGroup, Long parentId, String title, String content, String targetId, boolean isOwner, LocalDateTime createTime, LocalDateTime updateTime, List<PostSectionResponse> postSections) {
        this.postId = postId;
        this.postGroup = postGroup;
        this.parentId = parentId;
        this.title = title;
        this.content = content;
        this.targetId = targetId;
        this.isOwner = isOwner;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.postSections = postSections;
    }

    public static PostResponse of(Post post, Boolean isOwner) {
        return PostResponse.builder()
            .postId(post.getId())
            .postGroup(post.getPostGroup())
            .parentId(post.getParentId())
            .title(post.getTitle())
            .content(post.getContent())
            .targetId(post.getTargetId())
            .isOwner(isOwner)
            .createTime(post.getCreatedAt())
            .updateTime(post.getUpdatedAt())
            .postSections(getPostSectionResponses(post))
            .build();

    }

    private static List<PostSectionResponse> getPostSectionResponses(Post post) {
        return post.getPostSection().stream()
            .map(PostSectionResponse::of)
            .collect(Collectors.toList());
    }

}
