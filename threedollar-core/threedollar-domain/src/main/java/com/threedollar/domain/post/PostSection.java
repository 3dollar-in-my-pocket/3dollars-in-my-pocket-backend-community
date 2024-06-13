package com.threedollar.domain.post;

import com.threedollar.domain.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class PostSection extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 200)
    private PostType postType;

    @Column(length = 200)
    private Long parentId;

    @Column(nullable = false)
    private int priority;

    @Column(length = 4000)
    private String content;


}
