package com.ttknp.abclogservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

public class AbcLogServiceApplication {
    // protected static Logger log = LoggerFactory.getLogger(ALogServiceApplication.class);
    protected static Logger log;

    public AbcLogServiceApplication(Class clazz) {
        log = LoggerFactory.getLogger(clazz);
    }
}
