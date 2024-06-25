package com.threedollar.domain.post;

import com.threedollar.domain.BaseEntity;
import com.threedollar.domain.post.postsection.PostSection;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Post extends BaseEntity {

    @Column(nullable = false, length = 200)
    @Enumerated(EnumType.STRING)
    private PostGroup postGroup;

    @Column(length = 200)
    private Long parentId;

    @Column(nullable = false, length = 200)
    private String workspaceId;

    @Column(nullable = false, length = 200)
    private String title;

    @Column(length = 4000)
    private String content;

    @Column(nullable = false, length = 200)
    private String targetId;

    @Column(nullable = false, length = 200)
    private String accountId;

    @Column(nullable = false, length = 100)
    @Enumerated(EnumType.STRING)
    private PostStatus status;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<PostSection> postSection = new ArrayList<>();

    @Builder
    public Post(PostGroup postGroup, Long parentId, String workspaceId, String title, String content, String targetId, String accountId, PostStatus status) {
        this.postGroup = postGroup;
        this.parentId = parentId;
        this.workspaceId = workspaceId;
        this.title = title;
        this.content = content;
        this.targetId = targetId;
        this.accountId = accountId;
        this.status = status;
    }

    public void add(PostSection postSection) {
        this.postSection.add(postSection);
    }

    public void delete() {
        this.status = PostStatus.DELETED;
    }

    public static Post of(PostGroup postGroup, Long parentId, String workspaceId,
                          String title, String content, String targetId, String accountId) {
        return Post.builder()
            .postGroup(postGroup)
            .parentId(parentId)
            .workspaceId(workspaceId)
            .title(title)
            .content(content)
            .targetId(targetId)
            .accountId(accountId)
            .status(PostStatus.ACTIVE)
            .build();
    }


}
