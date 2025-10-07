package com.ttknp.arunner;

import com.ttknp.abclogservice.AbcLogServiceApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

// *** specify this spring boot app to scan app on "com.ttknp*" this path *** meaning all multiple modules that start with "com.ttknp*" can work well on this app
@ComponentScan(basePackages = {"com.ttknp"})
@SpringBootApplication
public class ARunnerApplication extends AbcLogServiceApplication {

    public ARunnerApplication() {
        super(ARunnerApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(ARunnerApplication.class, args);
    }

}
