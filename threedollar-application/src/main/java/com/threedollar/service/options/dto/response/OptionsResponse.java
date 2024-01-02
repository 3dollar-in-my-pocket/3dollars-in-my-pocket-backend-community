package com.threedollar.service.options.dto.response;

import com.threedollar.domain.options.PollOption;
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

    public static OptionsResponse of(PollOption pollOption) {
        return OptionsResponse.builder()
                .title(pollOption.getTitle())
                .imageUrl(pollOption.getImageUrl())
                .content(pollOption.getContent())
                .build();
    }
}
