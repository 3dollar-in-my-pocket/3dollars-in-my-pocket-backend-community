package com.threedollar.domain.poll;

import com.threedollar.domain.options.PollOption;
import com.threedollar.domain.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(uniqueConstraints = {
    @UniqueConstraint(
        name = "uni_poll_1",
        columnNames = {"accountId", "targetId", "workspaceId"}
    )
})
public class Poll extends BaseEntity {

    @Column(nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    private PollCategory pollCategory;

    @Column(nullable = false, length = 10)
    private String workspaceId;

    @Column(nullable = false, length = 10)
    private String targetId;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(length = 300)
    private String content;

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
    public Poll(PollCategory pollCategory, String workspaceId, String targetId, String title, String content, String accountId, LocalDateTime startDateTime, LocalDateTime endDateTime, PollStatus pollStatus) {
        this.pollCategory = pollCategory;
        this.workspaceId = workspaceId;
        this.targetId = targetId;
        this.title = title;
        this.content = content;
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

    public static Poll newInstance(PollCategory pollCategory, String workspaceId, String targetId, String title, String content, String accountId, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        return Poll.builder()
            .pollCategory(pollCategory)
            .workspaceId(workspaceId)
            .targetId(targetId)
            .title(title)
            .content(content)
            .accountId(accountId)
            .startDateTime(startDateTime)
            .endDateTime(endDateTime)
            .pollStatus(PollStatus.ACTIVE)
            .build();
    }

}
