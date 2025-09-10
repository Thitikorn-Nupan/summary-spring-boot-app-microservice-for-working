package com.ttknp.abchelperdatasource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.lang.management.ManagementFactory;
import java.util.Collections;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;


public class AbcHelperDatasourceApplication {

    protected static final Logger log = LoggerFactory.getLogger(AbcHelperDatasourceApplication.class);

    // get resource database by specify path
    protected static void runSpringBootWithoutImportResourceAnnotation(Class<?> theClass, Set<String> absolutePathsBeanOnXml , String... theArgs) {
        log.info("Starting Spring Boot Application");
        // ConfigurableEnvironment configurableEnvironment = null;
        // start app
        SpringApplication application = new SpringApplication(theClass);
        application.addListeners(new ApplicationPidFileWriter());
        // set resource paths to app
        application.setSources(absolutePathsBeanOnXml);
        // get env from app
        ConfigurableEnvironment configurableEnvironment = application.run(theArgs).getEnvironment();
        logProperties(configurableEnvironment);
    }

    // get resource database from application.properties as default property based on extend class
    protected static void runSpringBootWithoutImportResourceAnnotation(Class<?> theClass, String... theArgs) {
        log.info("Starting Spring Boot Application");
        // ConfigurableEnvironment configurableEnvironment = null;
        // start app
        SpringApplication application = new SpringApplication(theClass);
        application.addListeners(new ApplicationPidFileWriter());
        // set resource paths to app
        final Properties properties = new Properties();
        try (InputStream input = new ClassPathResource("application.properties").getInputStream()) {
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        application.setSources(new HashSet<>(Collections.singleton(properties.getProperty("context.path.mysql"))));
        // get env from app
        ConfigurableEnvironment configurableEnvironment = application.run(theArgs).getEnvironment();
        logProperties(configurableEnvironment);
    }

    private static void logProperties(ConfigurableEnvironment configurableEnvironment) {
        log.info(
                "visual name {} \nenvironment resource {} \n applicaiton name & port {} & {} \n",
                ManagementFactory.getRuntimeMXBean().getName(),
                configurableEnvironment.getPropertySources(),
                configurableEnvironment.getProperty("spring.application.name"),
                configurableEnvironment.getProperty("server.port")
        );
        // Return the value of System.getenv().
        // Note that most Environment implementations will include this system environment map as a default PropertySource to be searched. Therefore,
        // it is recommended that this method not be used directly unless bypassing other property sources is expressly intended.
        // System.out.println("env exists System.getenv() : {}"+ configurableEnvironment.getSystemEnvironment()); // {USERDOMAIN_ROAMINGPROFILE=TTKNP+ NVM_SYMLINK=C:\nvm4w\nodejs+ PROCESSOR_LEVEL=6, SESSIONNAME=Console
        // System.out.println("env exists System.getProperties() : {}"+ configurableEnvironment.getSystemProperties()); // {java.specification.version=22, sun.cpu.isalist=amd64, sun.jnu.encoding=MS874, java.class
        log.info("Spring Boot Application completed");
    }


}
