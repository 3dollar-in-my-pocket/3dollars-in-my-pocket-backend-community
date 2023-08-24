package com.threedollar.threedollardomain.domain.options;

import com.threedollar.threedollardomain.domain.BaseEntity;
import com.threedollar.threedollardomain.domain.poll.Poll;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@NoArgsConstructor
public class Options extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "poll_id", nullable = false)
    private Poll poll;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(length = 100)
    private String content;

    @Column(length = 200)
    private String imageUrl;

    @Builder(access = AccessLevel.PACKAGE)
    public Options(Poll poll, String title, String content, String imageUrl) {
        this.poll = poll;
        this.title = title;
        this.content = content;
        this.imageUrl = imageUrl;
    }

    public static Options of(@NotNull Poll poll, String title, String content, String imageUrl) {
        return Options.builder()
                .poll(poll)
                .title(title)
                .content(content)
                .imageUrl(imageUrl)
                .build();
    }

}