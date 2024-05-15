package com.threedollar.domain.options.repository;


import com.threedollar.domain.options.PollOption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OptionsRepository extends JpaRepository<PollOption, Long> {
}
