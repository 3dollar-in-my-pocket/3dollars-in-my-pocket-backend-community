package com.threedollar.service.options.dto.response;

import com.threedollar.domain.options.Options;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class OptionsResponse {

    private String title;

    private String imageUrl;

    private String content;

    @Builder
    public OptionsResponse(String title, String imageUrl, String content) {
        this.title = title;
        this.imageUrl = imageUrl;
        this.content = content;
    }

    public static OptionsResponse of(Options options) {
        return OptionsResponse.builder()
                .title(options.getTitle())
                .imageUrl(options.getImageUrl())
                .content(options.getContent())
                .build();
    }
}
