package com.threedollar.domain.post;

import com.threedollar.domain.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
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

    @Column(nullable = false, length = 200)
    private String accountId;

    @Column(nullable = false, length = 100)
    @Enumerated(EnumType.STRING)
    private PostStatus status;

    @OneToMany(mappedBy = "post_id", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<PostCategory> postCategory = new ArrayList<>();

    @OneToMany(mappedBy = "post_id", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<PostSection> postSection = new ArrayList<>();


}
