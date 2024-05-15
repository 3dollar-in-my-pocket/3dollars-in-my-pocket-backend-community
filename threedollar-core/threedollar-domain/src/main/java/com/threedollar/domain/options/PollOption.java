package com.threedollar.domain.options;

import com.threedollar.domain.BaseEntity;
import com.threedollar.domain.poll.Poll;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@NoArgsConstructor
public class PollOption extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "poll_id", nullable = false)
    private Poll poll;

    @Column(length = 100, nullable = false)
    @NotBlank
    private String title;

    @Column(length = 100)
    private String content;

    @Column(length = 200)
    private String imageUrl;

    @Builder
    public PollOption(Poll poll, String title, String content, String imageUrl) {
        this.poll = poll;
        this.title = title;
        this.content = content;
        this.imageUrl = imageUrl;
    }

    public static PollOption of(@NotNull Poll poll, @NotBlank String title, String content, String imageUrl) {
        return PollOption.builder()
                .poll(poll)
                .title(title)
                .content(content)
                .imageUrl(imageUrl)
                .build();
    }

}
