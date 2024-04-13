package com.threedollar.domain.stickeraction.repository;

import com.threedollar.domain.stickeraction.StickerAction;
import com.threedollar.domain.sticker.StickerGroup;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.Set;


public interface StickerActionRepositoryCustom {

    StickerAction getStickerActionByStickerGroupAndTargetIdAndAccountIdAndWorkspaceId(@NotNull StickerGroup stickerGroup,
                                                                                      @NotBlank String targetId,
                                                                                      @NotBlank String accountId,
                                                                                      @NotBlank String workspaceId);


    List<StickerAction> getStickerActionByMe(String accountId,
                                             Set<String> targetIds,
                                             StickerGroup stickerGroup,
                                             String workspaceId);

    Set<String> validatedTargetIds(StickerGroup stickerGroup,
                                     String workspaceId,
                                     Set<String> targetIds);

}
