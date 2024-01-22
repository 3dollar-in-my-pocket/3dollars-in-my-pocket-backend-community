package com.threedollar.service.sticker;

import com.threedollar.domain.stickeraction.StickerAction;
import com.threedollar.domain.stickeraction.repository.StickerActionRepository;
import com.threedollar.domain.sticker.Sticker;
import com.threedollar.domain.sticker.StickerGroup;
import com.threedollar.domain.sticker.repository.StickerRepository;
import com.threedollar.service.sticker.dto.response.request.AddReactionRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class StickerActionServiceTest {

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

    @Test
    void 스티커를_추가한다() {
        // given
        Sticker sticker = createSticker();
        AddReactionRequest request = getRequest();

        // when
        stickerReactionService.upsertSticker(request, sticker.getStickerGroup());

        // then
        StickerAction stickerAction = getStickerAction(request, sticker.getStickerGroup());
        assertReaction(stickerAction, sticker.getStickerGroup(), stickerAction.getTargetId(), stickerAction.getAccountId(), stickerAction.getStickerIds());

    }

    private void assertReaction(StickerAction stickerAction, StickerGroup stickerGroup, String targetId, String accountId, List<Long> stickerIds) {
        assertThat(stickerAction.getStickerGroup()).isEqualTo(stickerGroup);
        assertThat(stickerAction.getTargetId()).isEqualTo(targetId);
        assertThat(stickerAction.getAccountId()).isEqualTo(accountId);
        assertThat(stickerAction.getStickerIds()).isEqualTo(stickerIds);
    }


    private StickerAction getStickerAction(AddReactionRequest request, StickerGroup stickerGroup) {
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
