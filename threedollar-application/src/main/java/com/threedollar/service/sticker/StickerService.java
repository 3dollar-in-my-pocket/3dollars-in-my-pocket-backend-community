package com.threedollar.service.sticker;

import com.threedollar.common.exception.NotFoundException;
import com.threedollar.domain.sticker.Sticker;
import com.threedollar.domain.sticker.StickerGroup;
import com.threedollar.domain.sticker.repository.StickerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class StickerService {

    private final StickerRepository stickerRepository;

    @Transactional(readOnly = true)
    @Cacheable(cacheNames = "sticker", key = "#stickerGroup", value = "sticker")
    public List<Sticker> getStickersByStickerGroup(StickerGroup stickerGroup) {
        return stickerRepository.getStickerByStickerGroup(stickerGroup);
    }

    @Transactional(readOnly = true)
    public Set<Long> getStickerListByStickerIdAndGroup(Set<Long> stickerIds, StickerGroup stickerGroup) {

        Set<Long> stickerList = stickerRepository.getStickerByIdsAndStickerGroup(stickerIds, stickerGroup);
        if (stickerList.isEmpty()) {
            throw new NotFoundException(String.format("요청하신 스티커(%s)를 사용할 수 없습니다.", stickerIds));
        }
        return stickerList;
    }
}
