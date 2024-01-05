package com.threedollar.service.poll.dto.request;

import com.threedollar.domain.AccountType;
import com.threedollar.domain.options.PollOption;
import com.threedollar.domain.poll.Poll;
import com.threedollar.domain.poll.PollCategory;
import com.threedollar.service.options.dto.request.PollOptionCreateRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
public class PollCreateRequest {

    @NotNull
    private PollCategory pollCategory;

    @NotBlank
    private String title;

    @Nullable
    private String content;

    @NotNull
    private LocalDateTime startTime;

    @NotNull
    private LocalDateTime endTime;

    private List<PollOptionCreateRequest> options;

    @Builder
    public PollCreateRequest(PollCategory pollCategory, String title, @Nullable String content, LocalDateTime startTime, LocalDateTime endTime, List<PollOptionCreateRequest> options) {
        this.pollCategory = pollCategory;
        this.title = title;
        this.content = content;
        this.startTime = startTime;
        this.endTime = endTime;
        this.options = options;
    }

    public Poll toEntity(AccountType accountType, String accountId) {
        Poll poll = Poll.newInstance(pollCategory, title, content, accountType, accountId, startTime, endTime);
        List<PollOption> options = this.options.stream()
                .map(option -> option.toEntity(poll))
                .collect(Collectors.toList());
        poll.addOptions(options);
        return poll;
    }

}
