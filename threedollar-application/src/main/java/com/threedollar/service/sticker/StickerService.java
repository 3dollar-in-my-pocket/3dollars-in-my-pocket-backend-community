package com.threedollar.service.sticker;

import com.threedollar.domain.sticker.Sticker;
import com.threedollar.domain.sticker.StickerGroup;
import com.threedollar.domain.sticker.repository.StickerRepository;
import com.threedollar.service.sticker.dto.response.StickerInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StickerService {

    private final StickerRepository stickerRepository;

    @Transactional(readOnly = true)
    public List<StickerInfoResponse> getStickerList(StickerGroup group) {
        List<Sticker> stickers = stickerRepository.getStickerByStickerGroup(group);
        return stickers.stream()
            .map(StickerInfoResponse::of)
            .collect(Collectors.toList());
    }

}
