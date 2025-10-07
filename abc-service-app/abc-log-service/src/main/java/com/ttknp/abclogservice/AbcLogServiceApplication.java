package com.ttknp.abclogservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AbcLogServiceApplication {
    protected static Logger log;
    public AbcLogServiceApplication(Class<?> clazz) {
        log = LoggerFactory.getLogger(clazz);
    }
}
