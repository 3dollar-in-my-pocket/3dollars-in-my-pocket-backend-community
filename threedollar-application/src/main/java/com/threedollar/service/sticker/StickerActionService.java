package com.threedollar.service.sticker;


import com.threedollar.common.exception.NotFoundException;
import com.threedollar.domain.sticker.Sticker;
import com.threedollar.domain.sticker.StickerGroup;
import com.threedollar.domain.stickeraction.StickerAction;
import com.threedollar.domain.stickeraction.StickerActionCountKey;
import com.threedollar.domain.stickeraction.repository.StickerActionCountRepository;
import com.threedollar.domain.stickeraction.repository.StickerActionRepository;
import com.threedollar.service.sticker.dto.response.StickerInfoDetail;
import com.threedollar.service.sticker.dto.response.TargetStickerAction;
import com.threedollar.service.sticker.dto.request.AddStickerActionRequest;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
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
    public void upsertSticker(AddStickerActionRequest request, StickerGroup stickerGroup, Set<Long> stickerList, String workspaceId) {
        StickerAction stickerAction = stickerActionRepository.getStickerActionByStickerGroupAndTargetIdAndAccountId(stickerGroup, request.getTargetId(), request.getAccountId());

        if (stickerAction != null) {
            stickerCountRepository.decrBulkByCount(stickerGroup, workspaceId, stickerAction.getTargetId(), stickerAction.getStickerIds());
            stickerCountRepository.incrBulkByCount(stickerGroup, workspaceId, request.getTargetId(), request.getStickerIds());
            stickerAction.update(request.getStickerIds());
            return;
        }

        stickerCountRepository.incrBulkByCount(stickerGroup, request.getTargetId(), workspaceId, request.getStickerIds());
        stickerActionRepository.save(StickerAction.newInstance(stickerGroup, workspaceId, stickerList, request.getAccountId(), request.getTargetId()));

    }

    @Transactional
    public void deleteStickers(StickerGroup stickerGroup, String workspaceId, String targetId, String accountId) {
        StickerAction stickerAction = stickerActionRepository.getStickerActionByStickerGroupAndTargetIdAndAccountId(stickerGroup, targetId, accountId);

        if (stickerAction == null) {
            throw new NotFoundException(String.format("스티커 그룹 (%s)인 targetId (%s)에 해당하는 stickerAction 이 존재하지 않습니다.", stickerGroup, targetId));
        }
        stickerActionRepository.delete(stickerAction);
        stickerCountRepository.decrBulkByCount(stickerGroup, workspaceId, targetId, stickerAction.getStickerIds());

    }

    @Transactional(readOnly = true)
    public List<TargetStickerAction> getStickerActionResponse(StickerGroup stickerGroup,
                                                              String workspaceId,
                                                              @Nullable String accountId,
                                                              Set<String> targetIds,
                                                              List<Sticker> stickers) {

        Map<StickerActionCountKey, Long> stickerCountKeyLongMap = getStickerCountKey(stickerGroup, workspaceId, targetIds, stickers);

        Map<String, StickerAction> targetIdActedByMe = getTargetIdActedByMe(targetIds, accountId, stickerGroup);

        return targetIds.stream()
            .map(targetId -> {
                List<StickerInfoDetail> stickerInfoDetails = stickers.stream().map(

                    sticker -> {
                        long count = stickerCountKeyLongMap.getOrDefault(StickerActionCountKey.of(stickerGroup, targetId, workspaceId, sticker.getId()), 0L);
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
                                                                String workspaceId,
                                                                Set<String> targetIds,
                                                                List<Sticker> stickers) {
        List<StickerActionCountKey> stickerCountKeys = targetIds.stream()
            .flatMap(targetId -> stickers.stream()
                .map(sticker -> StickerActionCountKey.of(stickerGroup, targetId, workspaceId, sticker.getId()))
            ).collect(Collectors.toList());
        return stickerCountRepository.getStickerCountMap(stickerCountKeys);

    }

    private Map<String, StickerAction> getTargetIdActedByMe(Set<String> targetIds,
                                                            @Nullable String accountId,
                                                            StickerGroup stickerGroup) {
        if (StringUtils.isBlank(accountId)) {
            return Collections.emptyMap();
        }
        List<StickerAction> stickerActions = stickerActionRepository.getStickerActionByMe(accountId, targetIds, stickerGroup);
        return stickerActions.stream()
            .collect(Collectors.toMap(StickerAction::getTargetId, stickerAction -> stickerAction));
    }

}
