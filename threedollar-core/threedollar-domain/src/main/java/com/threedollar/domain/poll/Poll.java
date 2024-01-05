package com.threedollar.domain.poll;

import com.threedollar.domain.AccountType;
import com.threedollar.domain.options.PollOption;
import com.threedollar.domain.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Poll extends BaseEntity {

    @Column(nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    private PollCategory pollCategory;

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
    private final List<PollOption> options = new ArrayList<>();

    @Builder
    public Poll(PollCategory pollCategory, String title, String content, AccountType accountType, String accountId, LocalDateTime startDateTime, LocalDateTime endDateTime, PollStatus pollStatus) {
        this.pollCategory = pollCategory;
        this.title = title;
        this.content = content;
        this.accountType = accountType;
        this.accountId = accountId;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.pollStatus = pollStatus;
    }

    public void addOptions(List<PollOption> options) {
        for (PollOption option : options) {
            addOption(option);
        }
    }

    public void delete() {
        this.pollStatus = PollStatus.DELETED;
    }

    private void addOption(PollOption pollOption) {
        this.options.add(pollOption);
    }

    public static Poll newInstance(PollCategory pollCategory, String title, String content, AccountType accountType, String accountId, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        return Poll.builder()
                .pollCategory(pollCategory)
                .title(title)
                .content(content)
                .accountType(accountType)
                .accountId(accountId)
                .startDateTime(startDateTime)
                .endDateTime(endDateTime)
                .pollStatus(PollStatus.ACTIVE)
                .build();
    }

}
