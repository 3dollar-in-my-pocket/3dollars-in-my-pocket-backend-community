package com.threedollar.threedollardomain.domain.poll;

import com.threedollar.threedollardomain.domain.BaseEntity;
import com.threedollar.threedollardomain.domain.options.Options;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Poll extends BaseEntity {

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PollType pollType;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private String accountType;

    @Column(nullable = false)
    private String accountId;

    @Column(nullable = false)
    private LocalDateTime startTime;

    @Column(nullable = false)
    private LocalDateTime endTime;


    @Column(nullable = false)
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Options> options = new ArrayList<>();

    @Builder
    public Poll(PollType pollType, String title, String contents, String accountType, String accountId, LocalDateTime startTime, LocalDateTime endTime) {
        this.pollType = pollType;
        this.title = title;
        this.contents = contents;
        this.accountType = accountType;
        this.accountId = accountId;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @NotNull
    public static Poll newInstance(PollType pollType, String title, String contents, String accountType, String accountId, LocalDateTime startTime, LocalDateTime endTime) {
        return Poll.builder()
                .pollType(pollType)
                .title(title)
                .contents(contents)
                .accountType(accountType)
                .accountId(accountId)
                .startTime(startTime)
                .endTime(endTime)
                .build();
    }

}
