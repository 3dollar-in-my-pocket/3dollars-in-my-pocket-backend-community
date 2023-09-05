package com.threedollar.domain.poll;

import com.threedollar.domain.options.Options;
import com.threedollar.domain.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
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
    private String content;

    @Column(nullable = false)
    private String accountType;

    @Column(nullable = false)
    private String accountId;

    @Column(nullable = false)
    private LocalDateTime startTime;

    @Column(nullable = false)
    private LocalDateTime endTime;

    @OneToMany(mappedBy = "poll", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Options> options = new ArrayList<>();


    @Builder
    public Poll(PollType pollType, String title, String content, String accountType, String accountId, LocalDateTime startTime, LocalDateTime endTime) {
        this.pollType = pollType;
        this.title = title;
        this.content = content;
        this.accountType = accountType;
        this.accountId = accountId;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public void addOptions(List<Options> options) {
        for (Options option : options) {
            addOption(option);
        }
    }

    private void addOption(Options options) {
        this.options.add(options);
    }

    public static Poll newInstance(PollType pollType, String title, String content, String accountType, String accountId, LocalDateTime startTime, LocalDateTime endTime) {
        return Poll.builder()
                .pollType(pollType)
                .title(title)
                .content(content)
                .accountType(accountType)
                .accountId(accountId)
                .startTime(startTime)
                .endTime(endTime)
                .build();
    }

}
