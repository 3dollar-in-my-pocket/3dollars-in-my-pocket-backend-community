package com.threedollar.domain.sticker.repository;

import com.threedollar.domain.sticker.Sticker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StickerRepository extends JpaRepository<Sticker, Long>, StickerRepositoryCustom {

}
