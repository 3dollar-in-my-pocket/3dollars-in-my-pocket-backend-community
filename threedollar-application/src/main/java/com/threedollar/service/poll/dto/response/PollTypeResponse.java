package com.threedollar.service.poll.dto.response;

import com.threedollar.domain.poll.PollType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PollTypeResponse {

    private String pollType;

    private String title;

    private String content;

    @Builder
    public PollTypeResponse(String pollType, String title, String content) {
        this.pollType = pollType;
        this.title = title;
        this.content = content;
    }

    public static PollTypeResponse of(PollType pollType) {
        return PollTypeResponse.builder()
                .pollType(pollType.name())
                .title(pollType.getTitle())
                .content(pollType.getContent())
                .build();
    }

}
