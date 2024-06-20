package com.threedollar.domain.post.postsection;

import com.threedollar.domain.BaseEntity;
import com.threedollar.domain.post.Post;
import com.threedollar.domain.post.SectionType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class PostSection extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 200)
    private SectionType sectionType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @Column(nullable = false)
    private int priority;

    @Column(nullable = false, length = 500)
    private String url;

    @Column(length = 100)
    private int width;

    @Column(length = 100)
    private int height;

    @Builder
    public PostSection(SectionType sectionType, Post post, int priority, String url, int width, int height) {
        this.sectionType = sectionType;
        this.post = post;
        this.priority = priority;
        this.url = url;
        this.width = width;
        this.height = height;
    }

    public static PostSection of(SectionType sectionType, Post post, int priority, String url, int width, int height) {
        return PostSection.builder()
            .post(post)
            .sectionType(sectionType)
            .priority(priority)
            .url(url)
            .width(width)
            .height(height)
            .build();
    }

}
