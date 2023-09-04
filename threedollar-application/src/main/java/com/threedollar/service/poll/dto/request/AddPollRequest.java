package com.threedollar.service.poll.dto.request;

import com.threedollar.domain.options.Options;
import com.threedollar.domain.poll.Poll;
import com.threedollar.domain.poll.PollType;
import com.threedollar.service.options.dto.request.OptionsRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
public class AddPollRequest {

    @NotNull
    private PollType pollType;

    @NotBlank
    private String title;

    @Nullable
    private String content;

    @NotNull
    private String accountType;

    @NotBlank
    private String accountId;

    @NotNull
    private LocalDateTime startTime;

    @NotNull
    private LocalDateTime endTime;

    private List<OptionsRequest> optionsRequestList;

    @Builder
    public AddPollRequest(PollType pollType, String title, @Nullable String content, String accountType, String accountId, LocalDateTime startTime, LocalDateTime endTime, List<OptionsRequest> optionsRequestList) {
        this.pollType = pollType;
        this.title = title;
        this.content = content;
        this.accountType = accountType;
        this.accountId = accountId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.optionsRequestList = optionsRequestList;
    }

    public Poll toEntity() {
        Poll poll = Poll.newInstance(pollType, title, content, accountType, accountId, startTime, endTime);
        List<Options> options = this.optionsRequestList.stream()
                .map(option -> option.toEntity(poll))
                .collect(Collectors.toList());
        poll.addOptions(options);
        return poll;
    }


}
