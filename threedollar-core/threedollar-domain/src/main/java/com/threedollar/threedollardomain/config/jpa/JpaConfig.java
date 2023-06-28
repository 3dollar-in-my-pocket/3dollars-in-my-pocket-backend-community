package com.threedollar.threedollardomain.config.jpa;

import com.threedollar.threedollardomain.DomainRoot;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackageClasses = {DomainRoot.class})
@Configuration
public class JpaConfig {


}
