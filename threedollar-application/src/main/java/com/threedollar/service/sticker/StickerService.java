package com.threedollar.service.sticker;

import com.threedollar.domain.reaction.Reaction;
import com.threedollar.domain.reaction.repository.ReactionRepository;
import com.threedollar.domain.sticker.Sticker;
import com.threedollar.domain.sticker.StickerGroup;
import com.threedollar.domain.sticker.repository.StickerRepository;
import com.threedollar.service.reaction.dto.request.AddReactionRequest;
import com.threedollar.service.sticker.dto.response.StickerInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class StickerService {

    private final ReactionRepository reactionRepository;
    private final StickerRepository stickerRepository;

    @Transactional(readOnly = true)
    public List<StickerInfoResponse> getStickerList(StickerGroup group) {
        List<Sticker> stickers = stickerRepository.getStickerByStickerGroup(group);
        return stickers.stream()
                .map(StickerInfoResponse::of)
                .collect(Collectors.toList());
    }


    @Transactional
    public Long addSticker(AddReactionRequest request) {

        Sticker sticker = StickerServiceHelper.getStickerById(stickerRepository, request.getStickerId());

        Reaction reaction = reactionRepository.getReactionByTargetAndAccountIdAndStickerId(request.getReactionTarget(),
                request.getTargetId(),
                request.getAccountId(),
                sticker.getId());

        if (reaction != null) {
            reactionRepository.delete(reaction);
            return reaction.getId();
        }

        Reaction savedReaction = reactionRepository.save(request.toEntity());
        return savedReaction.getId();

    }


}
