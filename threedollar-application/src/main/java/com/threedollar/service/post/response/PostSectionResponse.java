package com.threedollar.service.post.response;

import com.threedollar.domain.post.SectionType;
import com.threedollar.domain.post.postsection.PostSection;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class PostSectionResponse {

    private SectionType sectionType;

    private int priority;

    private String url;

    private int width;

    private int height;

    @Builder
    public PostSectionResponse(SectionType sectionType, int priority, String url, int width, int height) {
        this.sectionType = sectionType;
        this.priority = priority;
        this.url = url;
        this.width = width;
        this.height = height;
    }

    public static PostSectionResponse of(PostSection section) {

        return PostSectionResponse.builder()
            .sectionType(section.getSectionType())
            .priority(section.getPriority())
            .url(section.getUrl())
            .width(section.getWidth())
            .height(section.getHeight())
            .build();

    }
}
