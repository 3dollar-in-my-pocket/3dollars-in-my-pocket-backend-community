package com.threedollar.service.poll.dto.request;

import com.threedollar.domain.poll.Poll;
import com.threedollar.domain.poll.PollType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

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

    @Builder
    public AddPollRequest(PollType pollType, String title, @Nullable String content, String accountType, String accountId, LocalDateTime startTime, LocalDateTime endTime) {
        this.pollType = pollType;
        this.title = title;
        this.content = content;
        this.accountType = accountType;
        this.accountId = accountId;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Poll toEntity() {
        return Poll.newInstance(pollType, title, content, accountType, accountId, startTime, endTime);
    }


}
