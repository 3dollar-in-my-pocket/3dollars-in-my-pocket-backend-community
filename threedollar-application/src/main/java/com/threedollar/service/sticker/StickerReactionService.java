package com.threedollar.service.sticker;


import com.threedollar.domain.reaction.Reaction;
import com.threedollar.domain.reaction.repository.ReactionRepository;
import com.threedollar.domain.redis.sticker.repository.StickerCountRepository;
import com.threedollar.domain.sticker.StickerGroup;
import com.threedollar.domain.sticker.repository.StickerRepository;
import com.threedollar.service.sticker.request.AddReactionRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class StickerReactionService {

    private final ReactionRepository reactionRepository;

    private final StickerRepository stickerRepository;

    private final StickerCountRepository stickerCountRepository;

    @Transactional
    public void upsertSticker(AddReactionRequest request, StickerGroup stickerGroup) {

        List<Long> stickerList = stickerRepository.getStickerByIdsAndStickerGroup(request.getStickerIds(), stickerGroup);
        if (request.getStickerIds().size() != stickerList.size()) {
            throw new IllegalArgumentException(String.format("요청하신 스티커(%s)를 사용할 수 없습니다.", request.getStickerIds()));
        }

        Reaction reaction = reactionRepository.getByReactionStickerGroupAndTargetIdAndAccountId(stickerGroup, request.getTargetId(), request.getAccountId());
        if (reaction != null) {
            reaction.getStickerIds()
                    .forEach(id -> stickerCountRepository.decrByCount(stickerGroup, reaction.getTargetId(), id));
            request.getStickerIds()
                            .forEach(id -> stickerCountRepository.incrByCount(stickerGroup, request.getTargetId(), id));
            reaction.update(request.getStickerIds());
            return;
        }
        request.getStickerIds()
                .forEach(id -> stickerCountRepository.incrByCount(stickerGroup, request.getTargetId(), id));
        reactionRepository.save(Reaction.newInstance(stickerGroup, stickerList, request.getAccountId(), request.getTargetId()));

    }

}
