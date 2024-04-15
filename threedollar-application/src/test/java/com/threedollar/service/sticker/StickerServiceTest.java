package com.threedollar.service.sticker;

import com.threedollar.IntegrationTest;
import com.threedollar.common.exception.NotFoundException;
import com.threedollar.domain.sticker.Sticker;
import com.threedollar.domain.sticker.StickerGroup;
import com.threedollar.domain.sticker.repository.StickerRepository;
import com.threedollar.service.sticker.dto.request.AddStickerActionRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class StickerServiceTest extends IntegrationTest {

    @Autowired
    private StickerRepository stickerRepository;

    @Autowired
    private StickerFacadeService stickerFacadeService;

    @Test
    void 스티커를_추가할때_해당하는_스티커가_존재하지_않을_때() {
        // given
        Sticker sticker = createSticker();
        AddStickerActionRequest request = getRequest(sticker);
        String workspaceId = "three-dollar-local";

        // when & then
        assertThatThrownBy(() -> stickerFacadeService.upsertSticker(request, sticker.getStickerGroup(), workspaceId))
            .isInstanceOf(NotFoundException.class);

    }

    private AddStickerActionRequest getRequest(Sticker sticker) {
        String targetId = "1L";
        String accountId = "USER_ACCOUNT999L";
        return AddStickerActionRequest.builder()
            .targetId(targetId)
            .stickerNames(Set.of(sticker.getName()))
            .accountId(accountId)
            .build();
    }

    private Sticker createSticker() {
        String imageUrl = "imageUrl";
        String workspaceId = "2";
        StickerGroup stickerGroup = StickerGroup.POLL_COMMENT;
        String name = "POLL";
        return stickerRepository.save(Sticker.newInstance(imageUrl, workspaceId, name, stickerGroup));
    }


}
