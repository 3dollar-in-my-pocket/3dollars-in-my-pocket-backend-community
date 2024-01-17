package com.threedollar.service.sticker;

import com.threedollar.domain.redis.sticker.StickerCountKey;
import com.threedollar.domain.stickeraction.StickerAction;
import com.threedollar.domain.stickeraction.repository.StickerActionRepository;
import com.threedollar.domain.redis.sticker.repository.StickerCountRepository;
import com.threedollar.domain.sticker.Sticker;
import com.threedollar.domain.sticker.StickerGroup;
import com.threedollar.domain.sticker.repository.StickerRepository;
import com.threedollar.service.sticker.dto.response.StickerInfoDetail;
import com.threedollar.service.sticker.dto.response.TargetStickerReactionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StickerReactionRetrieveService {

    private final StickerActionRepository stickerActionRepository;

    private final StickerRepository stickerRepository;

    private final StickerCountRepository stickerCountRepository;

    @Transactional(readOnly = true)
    public List<TargetStickerReactionResponse> getStickerReactionResponse(StickerGroup stickerGroup,
                                                                          String accountId,
                                                                          Set<String> targetIds) {

        List<Sticker> stickers = stickerRepository.getStickerByStickerGroup(stickerGroup);

        Map<StickerCountKey, Long> stickerCountKeyLongMap = getStickerCountKey(stickerGroup, targetIds, stickers);

        Map<String, StickerAction> targetIdActedByMe = getTargetIdActedByMe(targetIds, accountId, stickerGroup);

        return targetIds.stream()
                .map(targetId -> {
                    List<StickerInfoDetail> stickerInfoDetails = stickers.stream().map(

                            sticker -> {
                                long count = stickerCountKeyLongMap.getOrDefault(StickerCountKey.of(stickerGroup, targetId, sticker.getId()), 0L);
                                StickerAction stickerAction = targetIdActedByMe.getOrDefault(targetId, null);
                                return StickerInfoDetail.of(sticker,
                                        count,
                                        stickerAction == null || stickerAction.getStickerIds().contains(sticker.getId()));

                            }).toList();
                    return TargetStickerReactionResponse.builder()
                            .stickerInfoDetailList(stickerInfoDetails)
                            .targetId(targetId)
                            .build();
                }).collect(Collectors.toList());

    }

    private Map<StickerCountKey, Long> getStickerCountKey(StickerGroup stickerGroup,
                                                          Set<String> targetIds,
                                                          List<Sticker> stickers) {
        List<StickerCountKey> stickerCountKeys = targetIds.stream()
                .flatMap(targetId -> stickers.stream()
                        .map(sticker -> StickerCountKey.of(stickerGroup, targetId, sticker.getId()))
                ).toList();
        return stickerCountRepository.stickerCount(stickerCountKeys);

    }

    private Map<String, StickerAction> getTargetIdActedByMe(Set<String> targetIds,
                                                            String accountId,
                                                            StickerGroup stickerGroup) {
        List<StickerAction> stickerActions = stickerActionRepository.getStickerActionByMe(accountId, targetIds, stickerGroup);
        return stickerActions.stream()
                .collect(Collectors.toMap(StickerAction::getTargetId, stickerAction -> stickerAction));

    }


}
