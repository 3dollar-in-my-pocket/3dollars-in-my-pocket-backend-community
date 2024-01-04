package com.threedollar.domain.reaction;

import com.threedollar.StringArrayConverter;
import com.threedollar.domain.BaseEntity;
import com.threedollar.domain.sticker.StickerGroup;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Reaction extends BaseEntity {

    @Column(nullable = false)
    private StickerGroup stickerGroup;

    @Convert(converter = StringArrayConverter.class)
    private List<Long> stickerIds;

    @Column(nullable = false)
    private String accountId;

    @Column(nullable = false)
    private String targetId; // poll, review 등 ..


    @Builder
    public Reaction(@NotNull StickerGroup stickerGroup,
                    @NotBlank List<Long> stickerIds,
                    @NotBlank String accountId,
                    @NotBlank String targetId) {
        this.stickerGroup = stickerGroup;
        this.stickerIds = stickerIds;
        this.accountId = accountId;
        this.targetId = targetId;
    }

    public static Reaction newInstance(StickerGroup stickerGroup, List<Long> stickerIds, String accountId, String targetId) {
        return Reaction.builder()
                .stickerGroup(stickerGroup)
                .stickerIds(stickerIds)
                .accountId(accountId)
                .targetId(targetId)
                .build();
    }
}
