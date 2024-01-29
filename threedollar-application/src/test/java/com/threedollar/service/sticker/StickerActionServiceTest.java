package com.threedollar.service.sticker;

import com.threedollar.IntegrationTest;
import com.threedollar.domain.sticker.Sticker;
import com.threedollar.domain.sticker.StickerGroup;
import com.threedollar.domain.sticker.repository.StickerRepository;
import com.threedollar.domain.stickeraction.StickerAction;
import com.threedollar.domain.stickeraction.repository.StickerActionRepository;
import com.threedollar.service.sticker.dto.response.request.AddReactionRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class StickerActionServiceTest extends IntegrationTest {

    @Autowired
    private StickerActionRepository stickerActionRepository;

    @Autowired
    private StickerActionService stickerActionService;

    @Autowired
    private StickerRepository stickerRepository;

    @AfterEach
    void cleanUp() {
        stickerActionRepository.deleteAll();
        stickerRepository.deleteAll();
    }

    @Test
    void 스티커를_추가한다() {
        // given
        Sticker sticker = createSticker();
        AddReactionRequest request = getRequest(sticker);

        // when
        stickerActionService.upsertSticker(request, sticker.getStickerGroup(), request.getStickerIds());

        // then
        StickerAction stickerAction = getStickerAction(request, sticker.getStickerGroup());
        assertReaction(stickerAction, sticker.getStickerGroup(), stickerAction.getTargetId(), stickerAction.getAccountId(), stickerAction.getStickerIds());
    }

    private void assertReaction(StickerAction stickerAction, StickerGroup stickerGroup, String targetId, String accountId, Set<Long> stickerIds) {
        assertThat(stickerAction.getStickerGroup()).isEqualTo(stickerGroup);
        assertThat(stickerAction.getTargetId()).isEqualTo(targetId);
        assertThat(stickerAction.getAccountId()).isEqualTo(accountId);
        assertThat(stickerAction.getStickerIds()).isEqualTo(stickerIds);
    }


    private StickerAction getStickerAction(AddReactionRequest request, StickerGroup stickerGroup) {
        return request.toEntity(stickerGroup);
    }

    private AddReactionRequest getRequest(Sticker sticker) {
        String targetId = "1L";
        String accountId = "USER_ACCOUNT999L";
        Set<Long> stickerIds = Set.of(sticker.getId());
        return AddReactionRequest.builder()
            .targetId(targetId)
            .stickerIds(stickerIds)
            .accountId(accountId)
            .build();
    }

    private Sticker createSticker() {
        String imageUrl = "imageUrl";
        StickerGroup stickerGroup = StickerGroup.COMMUNITY_COMMENT;
        return stickerRepository.save(Sticker.newInstance(imageUrl, stickerGroup));
    }

}
