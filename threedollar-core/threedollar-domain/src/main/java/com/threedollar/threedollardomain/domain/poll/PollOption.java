package com.threedollar.threedollardomain.domain.poll;

import com.threedollar.threedollardomain.domain.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PollOption extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "poll_id", nullable = false)
    private Poll poll;

    @Column(nullable = false, length = 100)
    private String title;

    @Builder(access = AccessLevel.PACKAGE)
    public PollOption(Poll poll, String title) {
        this.poll = poll;
        this.title = title;
    }

    public static PollOption newPollOptionInstance(@NotNull Poll poll, @NotBlank String title) {
        return PollOption.builder()
                .poll(poll)
                .title(title)
                .build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PollOption pollOption = (PollOption) o;

        return Objects.equals(poll.getId(), pollOption.poll.getId())
                && Objects.equals(poll.getTitle(), pollOption.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(poll.getId(), title);
    }
}
