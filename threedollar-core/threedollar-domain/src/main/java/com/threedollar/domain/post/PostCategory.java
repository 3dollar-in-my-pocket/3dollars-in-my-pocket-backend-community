package com.threedollar.domain.post;

import com.threedollar.domain.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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

    @NotNull
    @Enumerated(EnumType.STRING)
    private PostGroup postGroup;

    @NotBlank
    private String title;


}
