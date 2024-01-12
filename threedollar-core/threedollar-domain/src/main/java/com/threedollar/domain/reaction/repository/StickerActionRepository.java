package com.threedollar.domain.reaction.repository;

import com.threedollar.domain.reaction.StickerAction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StickerActionRepository extends JpaRepository<StickerAction, Long>, ReactionRepositoryCustom {

}
