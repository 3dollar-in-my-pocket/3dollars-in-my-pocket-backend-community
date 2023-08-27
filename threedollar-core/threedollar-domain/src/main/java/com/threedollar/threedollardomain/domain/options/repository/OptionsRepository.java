package com.threedollar.threedollardomain.domain.options.repository;


import com.threedollar.threedollardomain.domain.options.Options;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OptionsRepository extends JpaRepository<Options, Long> {
}
