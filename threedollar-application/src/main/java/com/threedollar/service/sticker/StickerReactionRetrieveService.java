package com.threedollar.service.sticker;

import com.threedollar.domain.reaction.Reaction;
import com.threedollar.domain.reaction.repository.ReactionRepository;
import com.threedollar.domain.redis.sticker.repository.StickerCountRepository;
import com.threedollar.domain.sticker.Sticker;
import com.threedollar.domain.sticker.StickerGroup;
import com.threedollar.domain.sticker.repository.StickerRepository;
import com.threedollar.service.sticker.dto.response.TargetStickerReactionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StickerReactionRetrieveService {

    private final ReactionRepository reactionRepository;

    private final StickerRepository stickerRepository;

    private final StickerCountRepository stickerCountRepository;

    @Transactional(readOnly = true)
    public List<TargetStickerReactionResponse> getStickerReactionResponse(StickerGroup stickerGroup,
                                                                          String accountId,
                                                                          List<String> targetIds) {

        List<Sticker> stickers = stickerRepository.getStickerByStickerGroup(stickerGroup);

        Map<String, List<Reaction>> reactionMap = reactionRepository.getReactionByStickerGroupAndTargetIds(stickerGroup, targetIds);

        List<TargetStickerReactionResponse> targetStickerReactionResponses = new ArrayList<>();

        for (String targetId : targetIds) {
            targetStickerReactionResponses.addAll(reactionMap.get(targetId).stream()
                    .map(
                            reaction -> TargetStickerReactionResponse.of(isSelected(reaction, accountId), targetId, stickerGroup, stickerCountRepository,
                                    getStickers(stickers, reaction, targetId))).toList());
        }
        return targetStickerReactionResponses;

    }

    public static boolean isSelected(Reaction reaction, String accountId) {
        return reaction.getAccountId().equals(accountId);
    }

    public static List<Sticker> getStickers(List<Sticker> stickers, Reaction reaction, String targetId) {
        return stickers.stream()
                .filter(sticker -> sticker.getStickerGroup().equals(reaction.getStickerGroup()) &&
                        targetId.equals(reaction.getTargetId()))
                .collect(Collectors.toList());
    }


}
