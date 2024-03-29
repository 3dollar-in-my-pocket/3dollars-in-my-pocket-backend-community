package com.threedollar.service.poll.dto.request;

import com.threedollar.domain.options.PollOption;
import com.threedollar.domain.poll.Poll;
import com.threedollar.domain.poll.PollCategory;
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

    @NotBlank
    private String targetId;

    @NotNull
    private LocalDateTime startTime;

    @NotNull
    private LocalDateTime endTime;

    private List<PollOptionCreateRequest> options;

    @Builder
    public PollCreateRequest(PollCategory pollCategory, String title, String targetId, @Nullable String content, LocalDateTime startTime, LocalDateTime endTime, List<PollOptionCreateRequest> options) {
        this.pollCategory = pollCategory;
        this.title = title;
        this.targetId = targetId;
        this.content = content;
        this.startTime = startTime;
        this.endTime = endTime;
        this.options = options;
    }

    public Poll toEntity(String accountId, String workspaceId) {
        Poll poll = Poll.newInstance(pollCategory, workspaceId, targetId, title, content, accountId, startTime, endTime);
        List<PollOption> options = this.options.stream()
                .map(option -> option.toEntity(poll))
                .collect(Collectors.toList());
        poll.addOptions(options);
        return poll;
    }

}
