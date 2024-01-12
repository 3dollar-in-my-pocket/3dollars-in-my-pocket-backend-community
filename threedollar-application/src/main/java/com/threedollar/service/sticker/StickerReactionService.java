package com.threedollar.service.sticker;


import com.threedollar.domain.reaction.StickerAction;
import com.threedollar.domain.reaction.repository.StickerActionRepository;
import com.threedollar.domain.redis.sticker.repository.StickerCountRepository;
import com.threedollar.domain.sticker.StickerGroup;
import com.threedollar.domain.sticker.repository.StickerRepository;
import com.threedollar.service.sticker.dto.response.request.AddReactionRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class StickerReactionService {

    private final StickerActionRepository stickerActionRepository;

    private final StickerRepository stickerRepository;

    private final StickerCountRepository stickerCountRepository;

    @Transactional
    public void upsertSticker(AddReactionRequest request, StickerGroup stickerGroup) {

        List<Long> stickerList = stickerRepository.getStickerByIdsAndStickerGroup(request.getStickerIds(), stickerGroup);
        if (request.getStickerIds().size() != stickerList.size()) {
            throw new IllegalArgumentException(String.format("요청하신 스티커(%s)를 사용할 수 없습니다.", request.getStickerIds()));
        }

        StickerAction stickerAction = stickerActionRepository.getReactionByStickerGroupAndTargetIdAndAccountId(stickerGroup, request.getTargetId(), request.getAccountId());
        if (stickerAction != null) {
            stickerCountRepository.decrBulkByCount(stickerGroup, stickerAction.getTargetId(), stickerAction.getStickerIds());
            stickerCountRepository.incrBulkByCount(stickerGroup, request.getTargetId(), request.getStickerIds());
            stickerAction.update(request.getStickerIds());
            return;
        }
        stickerCountRepository.incrBulkByCount(stickerGroup, request.getTargetId(), request.getStickerIds());
        stickerActionRepository.save(StickerAction.newInstance(stickerGroup, stickerList, request.getAccountId(), request.getTargetId()));

    }

}
