package com.threedollar.domain.poll.repository;

import com.threedollar.domain.poll.Poll;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PollRepository extends JpaRepository<Poll, Long>, PollRepositoryCustom {
}
