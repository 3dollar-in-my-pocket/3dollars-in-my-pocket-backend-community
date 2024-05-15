package com.threedollar.service.sticker;

import com.threedollar.config.cache.CacheType;
import com.threedollar.domain.sticker.Sticker;
import com.threedollar.domain.sticker.StickerGroup;
import com.threedollar.domain.sticker.repository.StickerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StickerService {

    private final StickerRepository stickerRepository;

    @Transactional(readOnly = true)
    @Cacheable(cacheNames = CacheType.CacheConstants.STICKER, key = "{#stickerGroup, #workspaceId}")
    public List<Sticker> getStickersByStickerGroupAndWorkspaceId(StickerGroup stickerGroup, String workspaceId) {
        return stickerRepository.getStickerByStickerGroupAndWorkspaceIdAndTargetIds(stickerGroup, workspaceId);
    }

}
