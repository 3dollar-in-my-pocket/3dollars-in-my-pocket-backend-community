package com.threedollar.service.sticker;


import com.threedollar.domain.reaction.repository.ReactionRepository;
import com.threedollar.domain.sticker.StickerGroup;
import com.threedollar.domain.sticker.repository.StickerRepository;
import com.threedollar.service.sticker.request.AddReactionRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


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



    }

}
