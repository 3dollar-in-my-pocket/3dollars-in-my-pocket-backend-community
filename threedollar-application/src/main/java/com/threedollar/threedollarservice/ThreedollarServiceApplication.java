package com.threedollar.threedollarservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
public class ThreedollarServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ThreedollarServiceApplication.class, args);
    }

}
