package com.ttknp.abchelperconnectdatabasemysql.configuration;// ** i'll config xml by java code instead @ImportResource("classpath:xml/spring-context.xml") annotation
// ** look at c-runner module
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
// Config bean on xml
@Configuration
// We can import this XML configuration file into a configuration class using the @ImportResource annotation **  It looks at on the resource folder
@ImportResource("classpath:xml/spring-context-mysql-db.xml") // can import multiple files ** try
public class DriverManagerDataSourceConfigByXML { }

