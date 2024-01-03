package com.threedollar.domain.reaction.repository;

import com.threedollar.domain.reaction.Reaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReactionRepository extends JpaRepository<Reaction, Long>, ReactionRepositoryCustom {
}
