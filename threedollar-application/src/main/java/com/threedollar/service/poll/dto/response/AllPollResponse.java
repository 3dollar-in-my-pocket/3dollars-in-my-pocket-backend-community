package com.threedollar.service.poll.dto.response;

import com.threedollar.domain.options.Options;
import com.threedollar.domain.poll.Poll;
import com.threedollar.service.options.dto.response.OptionsResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
public class AllPollResponse {

    private String title;

    private String content;

    private String accountType;

    private String accountId;

    private List<OptionsResponse> optionsResponses;

    @Builder
    public AllPollResponse(String title, String content, String accountType, String accountId, List<OptionsResponse> optionsResponses) {
        this.title = title;
        this.content = content;
        this.accountType = accountType;
        this.accountId = accountId;
        this.optionsResponses = optionsResponses;
    }

    public static AllPollResponse of(Poll poll) {
        return AllPollResponse.builder()
                .title(poll.getTitle())
                .content(poll.getContent())
                .accountType(poll.getAccountType())
                .accountId(poll.getAccountId())
                .optionsResponses(getOptionResponse(poll.getOptions()))
                .build();
    }

    public static List<OptionsResponse> getOptionResponse(List<Options> options) {
        return options.stream()
                .map(OptionsResponse::of)
                .collect(Collectors.toList());
    }
}
