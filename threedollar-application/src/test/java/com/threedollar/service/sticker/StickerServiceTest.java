package com.threedollar.service.sticker;

import com.threedollar.domain.reaction.Reaction;
import com.threedollar.domain.reaction.repository.ReactionRepository;
import com.threedollar.domain.sticker.Sticker;
import com.threedollar.domain.sticker.StickerGroup;
import com.threedollar.domain.sticker.repository.StickerRepository;
import com.threedollar.service.reaction.dto.request.AddReactionRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class StickerServiceTest {

    @Autowired
    private ReactionRepository reactionRepository;

    @Autowired
    private StickerService stickerService;

    @Autowired
    private StickerRepository stickerRepository;

    @AfterEach
    void clean_up() {
        reactionRepository.deleteAll();
        stickerRepository.deleteAll();
    }

    @Test
    void 리액션을_추가한다() {
        // given
        Sticker sticker = createSticker();
        AddReactionRequest request = getRequest(sticker.getId());

        // when
        stickerService.addSticker(request, sticker.getStickerGroup());

        // then
        List<Reaction> reactionList = reactionRepository.findAll();
        assertThat(reactionList).hasSize(1);
        assertReaction(reactionList.get(0), sticker.getStickerGroup(), request.getTargetId(), request.getAccountId(), request.getStickerId());
    }

    @Test
    void 리액션을_이미_했을때_호출하면_제거된다() {
        // given
        Sticker sticker = createSticker();
        AddReactionRequest request = getRequest(sticker.getId());
        Reaction reaction = getReaction(request, sticker.getStickerGroup());
        reactionRepository.save(reaction);

        // when
        stickerService.addSticker(request, sticker.getStickerGroup());

        // then
        List<Reaction> reactionList = reactionRepository.findAll();
        assertThat(reactionList).isEmpty();

    }

    private void assertReaction(Reaction reaction, StickerGroup stickerGroup, String targetId, String accountId, Long stickerId) {
        assertThat(reaction.getStickerGroup()).isEqualTo(stickerGroup);
        assertThat(reaction.getTargetId()).isEqualTo(targetId);
        assertThat(reaction.getAccountId()).isEqualTo(accountId);
        assertThat(reaction.getStickerId()).isEqualTo(stickerId);
    }


    private Reaction getReaction(AddReactionRequest request, StickerGroup stickerGroup) {
        return request.toEntity(stickerGroup);
    }

    private AddReactionRequest getRequest(Long stickerId) {
        String targetId = "1L";
        String accountId = "USER_ACCOUNT999L";
        return AddReactionRequest.builder()
                .targetId(targetId)
                .accountId(accountId)
                .stickerId(stickerId)
                .build();
    }

    private Sticker createSticker() {
        String imageUrl = "imageUrl";
        StickerGroup stickerGroup = StickerGroup.COMMUNITY_COMMENT;
        return stickerRepository.save(Sticker.newInstance(imageUrl, stickerGroup));
    }

}
