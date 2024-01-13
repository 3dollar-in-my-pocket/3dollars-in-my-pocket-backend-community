package com.threedollar.domain.stickeraction.repository;

import com.threedollar.domain.stickeraction.StickerAction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StickerActionRepository extends JpaRepository<StickerAction, Long>, StickerActionRepositoryCustom {

}
