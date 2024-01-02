package com.threedollar.service.options.dto.request;

import com.threedollar.domain.options.PollOption;
import com.threedollar.domain.poll.Poll;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class PollOptionCreateRequest {

    private String title;

    private String content;

    private String urlImage;

    @Builder
    public PollOptionCreateRequest(String title, String content, String urlImage) {
        this.title = title;
        this.content = content;
        this.urlImage = urlImage;
    }

    public PollOption toEntity(Poll poll) {
        return PollOption.of(poll, title, content, urlImage);
    }


}
