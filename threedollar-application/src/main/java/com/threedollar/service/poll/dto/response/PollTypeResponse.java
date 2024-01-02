package com.threedollar.service.poll.dto.response;

import com.threedollar.domain.poll.PollCategory;
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

    public static PollTypeResponse of(PollCategory pollCategory) {
        return PollTypeResponse.builder()
                .pollType(pollCategory.name())
                .title(pollCategory.getTitle())
                .content(pollCategory.getContent())
                .build();
    }

}
