package com.threedollar.service.sticker;

import com.threedollar.domain.reaction.Reaction;
import com.threedollar.domain.reaction.repository.ReactionRepository;
import com.threedollar.domain.sticker.Sticker;
import com.threedollar.domain.sticker.StickerGroup;
import com.threedollar.domain.sticker.repository.StickerRepository;
import com.threedollar.service.sticker.request.AddReactionRequest;
import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class StickerReactionServiceTest {

    @Autowired
    private ReactionRepository reactionRepository;

    @Autowired
    private StickerReactionService stickerReactionService;

    @Autowired
    private StickerRepository stickerRepository;

    @AfterEach
    void clean_up() {
        reactionRepository.deleteAll();
        stickerRepository.deleteAll();
    }

    private void assertReaction(Reaction reaction, StickerGroup stickerGroup, String targetId, String accountId, List<Long> stickerIds) {
        assertThat(reaction.getStickerGroup()).isEqualTo(stickerGroup);
        assertThat(reaction.getTargetId()).isEqualTo(targetId);
        assertThat(reaction.getAccountId()).isEqualTo(accountId);
        assertThat(reaction.getStickerIds()).isEqualTo(stickerIds);
    }


    private Reaction getReaction(AddReactionRequest request, StickerGroup stickerGroup) {
        return request.toEntity(stickerGroup);
    }

    private AddReactionRequest getRequest() {
        String targetId = "1L";
        String accountId = "USER_ACCOUNT999L";
        return AddReactionRequest.builder()
                .targetId(targetId)
                .accountId(accountId)
                .build();
    }

    private Sticker createSticker() {
        String imageUrl = "imageUrl";
        StickerGroup stickerGroup = StickerGroup.COMMUNITY_COMMENT;
        return stickerRepository.save(Sticker.newInstance(imageUrl, stickerGroup));
    }

}
