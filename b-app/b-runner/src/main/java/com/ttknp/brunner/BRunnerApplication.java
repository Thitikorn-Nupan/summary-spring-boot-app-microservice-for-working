package com.ttknp.brunner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

// *** specify this spring boot app to scan app on "com.ttknp*" this path
// *** meaning all multiple modules that start with "com.ttknp*" can work well on this app
@ComponentScan(basePackages = {"com.ttknp"})
@SpringBootApplication
public class BRunnerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BRunnerApplication.class, args);
    }

}

/*create table USERS
(
    USERNAME CHARACTER VARYING(60)  not null
        primary key,
    MAIL     CHARACTER VARYING(100) not null,
    ID       INTEGER auto_increment
        constraint USERS_PK
            unique
);*/