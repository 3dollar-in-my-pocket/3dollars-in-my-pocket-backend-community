package com.threedollar;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan(basePackages = {"com.threedollar"})
@SpringBootApplication(scanBasePackages = {"com.threedollar"})
public class DomainRootApplicationTest {
}
