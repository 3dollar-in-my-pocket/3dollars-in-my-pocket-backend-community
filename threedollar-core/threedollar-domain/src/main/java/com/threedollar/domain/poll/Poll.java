package com.threedollar.domain.poll;

import com.threedollar.domain.AccountType;
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
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(indexes = {
        @Index(name = "idx_poll_1", columnList = "category, pollStatus"),
        @Index(name = "idx_poll_2", columnList = "accountId,accountType,pollStatus")
}
)
public class Poll extends BaseEntity {

    @Column(nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    private PollType pollType;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(length = 300)
    private String content;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @Column(nullable = false)
    private String accountId;

    @Column(nullable = false)
    private LocalDateTime startDateTime;

    @Column(nullable = false)
    private LocalDateTime endDateTime;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PollStatus pollStatus;

    @OneToMany(mappedBy = "poll", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Options> options = new ArrayList<>();

    @Builder
    public Poll(PollType pollType, String title, String content, AccountType accountType, String accountId, LocalDateTime startDateTime, LocalDateTime endDateTime, PollStatus pollStatus) {
        this.pollType = pollType;
        this.title = title;
        this.content = content;
        this.accountType = accountType;
        this.accountId = accountId;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.pollStatus = pollStatus;
    }

    public void addOptions(List<Options> options) {
        for (Options option : options) {
            addOption(option);
        }
    }

    private void addOption(Options options) {
        this.options.add(options);
    }

    public static Poll newInstance(PollType pollType, String title, String content, AccountType accountType, String accountId, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        return Poll.builder()
                .pollType(pollType)
                .title(title)
                .content(content)
                .accountType(accountType)
                .accountId(accountId)
                .startDateTime(startDateTime)
                .endDateTime(endDateTime)
                .build();
    }

}
