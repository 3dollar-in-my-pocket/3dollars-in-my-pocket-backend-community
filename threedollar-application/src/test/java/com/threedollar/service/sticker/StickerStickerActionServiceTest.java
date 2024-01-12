package com.threedollar.service.sticker;

import com.threedollar.domain.reaction.StickerAction;
import com.threedollar.domain.reaction.repository.StickerActionRepository;
import com.threedollar.domain.sticker.Sticker;
import com.threedollar.domain.sticker.StickerGroup;
import com.threedollar.domain.sticker.repository.StickerRepository;
import com.threedollar.service.sticker.dto.response.request.AddReactionRequest;
import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class StickerStickerActionServiceTest {

    @Autowired
    private StickerActionRepository stickerActionRepository;

    @Autowired
    private StickerReactionService stickerReactionService;

    @Autowired
    private StickerRepository stickerRepository;

    @AfterEach
    void clean_up() {
        stickerActionRepository.deleteAll();
        stickerRepository.deleteAll();
    }

    private void assertReaction(StickerAction stickerAction, StickerGroup stickerGroup, String targetId, String accountId, List<Long> stickerIds) {
        assertThat(stickerAction.getStickerGroup()).isEqualTo(stickerGroup);
        assertThat(stickerAction.getTargetId()).isEqualTo(targetId);
        assertThat(stickerAction.getAccountId()).isEqualTo(accountId);
        assertThat(stickerAction.getStickerIds()).isEqualTo(stickerIds);
    }


    private StickerAction getReaction(AddReactionRequest request, StickerGroup stickerGroup) {
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
