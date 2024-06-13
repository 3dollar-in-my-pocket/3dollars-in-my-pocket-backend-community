package com.threedollar.domain.post.postcategory;

import com.threedollar.domain.BaseEntity;
import com.threedollar.domain.post.Post;
import com.threedollar.domain.post.PostGroup;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class PostCategory extends BaseEntity {

    @NotBlank
    private String workspaceId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @NotNull
    @Enumerated(EnumType.STRING)
    private PostGroup postGroup;

    @NotBlank
    private String title;


}
