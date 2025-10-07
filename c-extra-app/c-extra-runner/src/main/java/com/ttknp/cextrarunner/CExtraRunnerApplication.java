package com.ttknp.cextrarunner;

import com.ttknp.abchelperdatasource.AbcHelperDatasourceApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan(basePackages = {"com.ttknp"})
@SpringBootApplication
@Configuration
public class CExtraRunnerApplication extends AbcHelperDatasourceApplication {


    /**
    it's amazing <br/>
    focus on parent it have no driver sql but still get driver such as script ! <br/>
    but in sub module as c-extra-web-controller should be driver ! <br/>
    private static final Set<String> absPathsBeanOnXml = new HashSet<>(Arrays.asList(
            "file:B:/practice-java-one-jetbrains/spring-boot-skills/lab_core_36/sumary-spring-boot-career/abc-parent/abc-properties-service/src/main/resources/xml/spring-context-mysql-db-extra.xml"
    ));
    */
    public static void main(String[] args) {
        runSpringBootWithoutImportResourceAnnotation(CExtraRunnerApplication.class,args);
    }

}
