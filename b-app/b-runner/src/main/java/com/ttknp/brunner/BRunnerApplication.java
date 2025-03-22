package com.ttknp.brunner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;

import java.lang.management.ManagementFactory;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


// *** specify this spring boot app to scan app on "com.ttknp*" this path
// *** meaning all multiple modules that start with "com.ttknp*" can work well on this app
@ComponentScan(basePackages = {"com.ttknp"})
@SpringBootApplication
@Configuration
public class BRunnerApplication {

    private static final Logger log = LoggerFactory.getLogger(BRunnerApplication.class);

    // ** Note parent of this bean must have a database driver
    // set the bean paths
    private static final Set<String> pathBeanOnXml = new HashSet<>(Arrays.asList(
            "file:B:/practice-java-one-jetbrains/spring-boot-skills/lab_core_36/sumary-spring-boot-career/abc-service-app/abc-helper-connect-database-h2/src/main/resources/xml/spring-context-h2-db.xml",
            "file:B:/practice-java-one-jetbrains/spring-boot-skills/lab_core_36/sumary-spring-boot-career/abc-service-app/abc-helper-connect-database-h2/src/main/resources/xml/spring-context-h2-db-extra.xml"
    ));

    protected static void runSpringBootWithoutImportResourceAnnotation(Class<?> theClass, String... theArgs) {
        log.info("begin : method main");
        ConfigurableEnvironment configurableEnvironment = null;
        // start app
        SpringApplication application = new SpringApplication(BRunnerApplication.class);
        application.addListeners(new ApplicationPidFileWriter());
        // set resource paths to app
        application.setSources(pathBeanOnXml);
        // get env from app
        configurableEnvironment = application.run(theArgs).getEnvironment();
        log.debug("env exists visualName : {}", ManagementFactory.getRuntimeMXBean().getName()); // 21432@TTKNP
        log.debug("env exists : {}", configurableEnvironment.getPropertySources()); // get all application.prop (include any hiding prop) [MapPropertySource@145494758 {name='server.ports', properties={local.server.port=8082}},
        log.debug("env exists spring.application.name : {}", configurableEnvironment.getProperty("spring.application.name")); // b-runner
        log.debug("env exists server.port : {}", configurableEnvironment.getProperty("server.port")); // 8082
        // Return the value of System.getenv().
        // Note that most Environment implementations will include this system environment map as a default PropertySource to be searched. Therefore,
        // it is recommended that this method not be used directly unless bypassing other property sources is expressly intended.
        log.debug("env exists System.getenv() : {}", configurableEnvironment.getSystemEnvironment()); // {USERDOMAIN_ROAMINGPROFILE=TTKNP, NVM_SYMLINK=C:\nvm4w\nodejs, PROCESSOR_LEVEL=6, SESSIONNAME=Console
        log.debug("env exists System.getProperties() : {}", configurableEnvironment.getSystemProperties()); // {java.specification.version=22, sun.cpu.isalist=amd64, sun.jnu.encoding=MS874, java.class
    }

    public static void main(String[] args) {
        // SpringApplication.run(BRunnerApplication.class, args);
        runSpringBootWithoutImportResourceAnnotation(BRunnerApplication.class,args);
    }
}

