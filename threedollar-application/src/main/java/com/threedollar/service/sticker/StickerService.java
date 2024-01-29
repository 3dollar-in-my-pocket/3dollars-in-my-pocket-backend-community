package com.threedollar.service.sticker;

import com.threedollar.domain.sticker.Sticker;
import com.threedollar.domain.sticker.StickerGroup;
import com.threedollar.domain.sticker.repository.StickerRepository;
import com.threedollar.service.sticker.dto.response.StickerInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
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

    @Transactional(readOnly = true)
    public List<Sticker> getStickersByStickerGroup(StickerGroup stickerGroup) {
        return stickerRepository.getStickerByStickerGroup(stickerGroup);
    }

    @Transactional(readOnly = true)
    public Set<Long> getStickerListByStickerIdAndGroup(Set<Long> stickerIds, StickerGroup stickerGroup) {

        Set<Long> stickerList = stickerRepository.getStickerByIdsAndStickerGroup(stickerIds, stickerGroup);
        if (stickerList.isEmpty()) {
            throw new IllegalArgumentException(String.format("요청하신 스티커(%s)를 사용할 수 없습니다.", stickerIds));
        }
        return stickerList;
    }
}
