package com.threedollar.domain.options.repository;


import com.threedollar.domain.options.Options;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OptionsRepository extends JpaRepository<Options, Long> {
}
