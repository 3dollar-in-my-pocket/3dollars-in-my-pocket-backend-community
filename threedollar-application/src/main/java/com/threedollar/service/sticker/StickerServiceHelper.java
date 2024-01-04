package com.threedollar.service.sticker;

import com.threedollar.common.exception.exception.NotFoundException;
import com.threedollar.domain.sticker.Sticker;
import com.threedollar.domain.sticker.StickerGroup;
import com.threedollar.domain.sticker.repository.StickerRepository;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StickerServiceHelper {

    public static Sticker getStickerByIdAndStickerGroup(StickerRepository stickerRepository,
                                                         StickerGroup stickerGroup,
                                                         Long stickerId) {
        Sticker sticker = stickerRepository.getStickerByIdAndStickerGroup(stickerId, stickerGroup);
        if (sticker == null) {
            throw new NotFoundException(String.format("(%s) 에 해당하는 스티커는 존재하지 않습니다.", stickerId));
        }
        return sticker;
    }

}
