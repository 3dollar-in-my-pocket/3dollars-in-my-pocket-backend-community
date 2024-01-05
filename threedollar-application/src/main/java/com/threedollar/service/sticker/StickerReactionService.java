package com.threedollar.service.sticker;


import com.threedollar.domain.reaction.Reaction;
import com.threedollar.domain.reaction.repository.ReactionRepository;
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

    @Transactional
    public void upsertSticker(AddReactionRequest request, StickerGroup stickerGroup) {

        /**
         * TODO: unique key 로 targetId, accountId, stickerGroup 으로 잡는다
         */
        List<Long> stickerList = stickerRepository.getStickerByIdsAndStickerGroup(request.getStickerIds(), stickerGroup);
        if (request.getStickerIds().size() != stickerList.size()) {
            throw new IllegalArgumentException("요청하신 스티커를 사용할 수 없습니다.");
        }

        Reaction reaction = reactionRepository.getByReactionStickerGroupAndTargetIdAndAccountId(stickerGroup, request.getTargetId(), request.getAccountId());
        if (reaction != null) {
            reactionRepository.delete(reaction);
        }

        reactionRepository.save(Reaction.newInstance(stickerGroup, stickerList, request.getAccountId(), request.getTargetId()));


    }

}
