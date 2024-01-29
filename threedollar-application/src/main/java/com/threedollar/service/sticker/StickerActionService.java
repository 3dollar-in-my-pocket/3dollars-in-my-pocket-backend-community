package com.threedollar.service.sticker;


import com.threedollar.domain.sticker.Sticker;
import com.threedollar.domain.sticker.StickerGroup;
import com.threedollar.domain.stickeraction.StickerAction;
import com.threedollar.domain.stickeraction.StickerActionCountKey;
import com.threedollar.domain.stickeraction.repository.StickerActionCountRepository;
import com.threedollar.domain.stickeraction.repository.StickerActionRepository;
import com.threedollar.service.sticker.dto.response.StickerInfoDetail;
import com.threedollar.service.sticker.dto.response.TargetStickerAction;
import com.threedollar.service.sticker.dto.request.AddReactionRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class StickerActionService {

    private final StickerActionRepository stickerActionRepository;

    private final StickerActionCountRepository stickerCountRepository;

    @Transactional
    public void upsertSticker(AddReactionRequest request, StickerGroup stickerGroup, Set<Long> stickerList) {
        StickerAction stickerAction = stickerActionRepository.getReactionByStickerGroupAndTargetIdAndAccountId(stickerGroup, request.getTargetId(), request.getAccountId());

        if (request.getStickerIds().isEmpty()) {
            stickerCountRepository.decrBulkByCount(stickerGroup, request.getTargetId(), stickerAction.getStickerIds());
            stickerActionRepository.delete(stickerAction);
        }

        if (stickerAction != null) {
            stickerCountRepository.decrBulkByCount(stickerGroup, stickerAction.getTargetId(), stickerAction.getStickerIds());
            stickerCountRepository.incrBulkByCount(stickerGroup, request.getTargetId(), request.getStickerIds());
            stickerAction.update(request.getStickerIds());
            return;
        }

        stickerCountRepository.incrBulkByCount(stickerGroup, request.getTargetId(), request.getStickerIds());
        stickerActionRepository.save(StickerAction.newInstance(stickerGroup, stickerList, request.getAccountId(), request.getTargetId()));

    }

    @Transactional(readOnly = true)
    public List<TargetStickerAction> getStickerReactionResponse(StickerGroup stickerGroup,
                                                                String accountId,
                                                                Set<String> targetIds,
                                                                List<Sticker> stickers) {

        Map<StickerActionCountKey, Long> stickerCountKeyLongMap = getStickerCountKey(stickerGroup, targetIds, stickers);

        Map<String, StickerAction> targetIdActedByMe = getTargetIdActedByMe(targetIds, accountId, stickerGroup);

        return targetIds.stream()
            .map(targetId -> {
                List<StickerInfoDetail> stickerInfoDetails = stickers.stream().map(

                    sticker -> {
                        long count = stickerCountKeyLongMap.getOrDefault(StickerActionCountKey.of(stickerGroup, targetId, sticker.getId()), 0L);
                        StickerAction stickerAction = targetIdActedByMe.getOrDefault(targetId, null);
                        return StickerInfoDetail.of(sticker,
                            count,
                            stickerAction == null || stickerAction.getStickerIds().contains(sticker.getId()));

                    }).toList();
                return TargetStickerAction.builder()
                    .stickerInfoDetailList(stickerInfoDetails)
                    .targetId(targetId)
                    .build();
            }).collect(Collectors.toList());

    }

    private Map<StickerActionCountKey, Long> getStickerCountKey(StickerGroup stickerGroup,
                                                                Set<String> targetIds,
                                                                List<Sticker> stickers) {
        List<StickerActionCountKey> stickerCountKeys = targetIds.stream()
            .flatMap(targetId -> stickers.stream()
                .map(sticker -> StickerActionCountKey.of(stickerGroup, targetId, sticker.getId()))
            ).collect(Collectors.toList());
        return stickerCountRepository.getStickerCountMap(stickerCountKeys);

    }

    private Map<String, StickerAction> getTargetIdActedByMe(Set<String> targetIds,
                                                            String accountId,
                                                            StickerGroup stickerGroup) {
        List<StickerAction> stickerActions = stickerActionRepository.getStickerActionByMe(accountId, targetIds, stickerGroup);
        return stickerActions.stream()
            .collect(Collectors.toMap(StickerAction::getTargetId, stickerAction -> stickerAction));
    }

}
