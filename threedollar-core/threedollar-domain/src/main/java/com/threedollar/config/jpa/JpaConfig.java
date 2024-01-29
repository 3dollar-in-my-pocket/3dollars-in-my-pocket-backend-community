package com.threedollar.config.jpa;

import com.threedollar.DomainRoot;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaAuditing
@EnableJpaRepositories(basePackageClasses = {DomainRoot.class})
@Configuration
public class JpaConfig {


}
