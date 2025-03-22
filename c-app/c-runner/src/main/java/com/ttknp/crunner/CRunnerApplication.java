package com.ttknp.crunner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan(basePackages = {"com.ttknp"})
@SpringBootApplication
@Configuration
public class CRunnerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CRunnerApplication.class, args);
    }

}
