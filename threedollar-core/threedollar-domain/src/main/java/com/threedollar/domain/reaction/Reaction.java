package com.threedollar.domain.reaction;

import com.threedollar.StringArrayConverter;
import com.threedollar.domain.BaseEntity;
import com.threedollar.domain.sticker.StickerGroup;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name="Reaction", uniqueConstraints = {
        @UniqueConstraint(
                name="uni_reaction_1",
                columnNames = {"targetId","accountId","stickerGroup"}
        )
})
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
