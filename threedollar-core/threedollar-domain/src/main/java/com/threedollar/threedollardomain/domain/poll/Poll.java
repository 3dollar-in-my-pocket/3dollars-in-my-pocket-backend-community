package com.threedollar.threedollardomain.domain.poll;

import com.threedollar.threedollardomain.domain.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Poll extends BaseEntity {

    @NotNull
    @Column(nullable = false, length = 50)
    private PollType pollType;

    @NotBlank
    @Column(nullable = false, length = 100)
    private String title;

    @NotNull
    @Column(nullable = false, length = 100)
    private LocalDateTime startTime;

    @NotNull
    @Column(nullable = false, length = 100)
    private LocalDateTime endTime;

    @OneToMany(mappedBy = "poll", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<PollOption> pollOptions = new ArrayList<>();

    @Builder(access = AccessLevel.PACKAGE)
    public Poll(@NotNull PollType pollType, @NotBlank String title, @NotNull LocalDateTime startTime, @NotNull LocalDateTime endTime) {
        this.pollType = pollType;
        this.title = title;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public static Poll newPollInstance(@NotNull PollType pollType, @NotBlank String title, @NotNull LocalDateTime startTime, @NotNull LocalDateTime endTime) {
        return Poll.builder()
                .pollType(pollType)
                .title(title)
                .startTime(startTime)
                .endTime(endTime)
                .build();
    }


}
