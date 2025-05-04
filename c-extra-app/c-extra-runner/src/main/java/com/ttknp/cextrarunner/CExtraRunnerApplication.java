package com.ttknp.cextrarunner;

import com.ttknp.abchelperdatasource.AbcHelperDatasourceApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@ComponentScan(basePackages = {"com.ttknp"})
@SpringBootApplication
@Configuration
public class CExtraRunnerApplication extends AbcHelperDatasourceApplication {

    private static final Set<String> absPathsBeanOnXml = new HashSet<>(Arrays.asList(
            "file:B:/practice-java-one-jetbrains/spring-boot-skills/lab_core_36/sumary-spring-boot-career/c-extra-app/c-extra-service-web-controller/src/main/resources/xml/spring-context-mysql-db-extra.xml"
    ));

    public static void main(String[] args) {
        runSpringBootWithoutImportResourceAnnotation(CExtraRunnerApplication.class,absPathsBeanOnXml,args);
    }

}
