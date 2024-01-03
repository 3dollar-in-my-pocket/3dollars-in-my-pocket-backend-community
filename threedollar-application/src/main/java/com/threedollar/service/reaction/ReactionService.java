package com.threedollar.service.reaction;

import com.threedollar.domain.reaction.Reaction;
import com.threedollar.domain.reaction.repository.ReactionRepository;
import com.threedollar.service.reaction.dto.request.AddReactionRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReactionService {

    private final ReactionRepository reactionRepository;

    @Transactional
    public Long addSticker(AddReactionRequest request) {
        Reaction reaction = reactionRepository.getReactionByTargetAndAccountIdAndStickerId(request.getReactionTarget(),
                request.getTargetId(),
                request.getAccountId(),
                request.getStickerId());
        if (reaction != null) {
            reactionRepository.delete(reaction);
            return reaction.getId();
        }
        Reaction savedReaction = reactionRepository.save(request.toEntity());
        return savedReaction.getId();

    }

}
