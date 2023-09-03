package com.threedollar.service.options.dto.request;

import com.threedollar.domain.options.Options;
import com.threedollar.domain.poll.Poll;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class OptionsRequest {

    private String title;

    private String content;

    private String urlImage;

    @Builder
    public OptionsRequest(String title, String content, String urlImage) {
        this.title = title;
        this.content = content;
        this.urlImage = urlImage;
    }

    public Options toEntity(Poll poll) {
        return Options.of(poll, title, content, urlImage);
    }
}
