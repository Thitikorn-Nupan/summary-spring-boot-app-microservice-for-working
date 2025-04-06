package com.ttknp.abchelperconnectdatabasemysql.service.common;


import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

// use abs class with extends
public abstract class ModelService<T> implements RowMapper<T> {
    private String sqlScriptDirOnAbs = "B:/practice-java-one-jetbrains/spring-boot-skills/lab_core_36/sumary-spring-boot-career/abc-parent/abc-properties-service/src/main/resources/sql/";
    public abstract List<T> retrieveAll() ;
    public abstract Boolean add(T t);
    public void loadScriptAbsPath(String fileName, DataSource dataSource) {
        String fullSqlScriptDirOnRoot = sqlScriptDirOnAbs + fileName;
        // ** way to query with script sql ** if you want queries response don't do the way
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScripts(new FileSystemResource(fullSqlScriptDirOnRoot)); // FileSystemResource class it looks to abs path
        populator.execute(Objects.requireNonNull(dataSource)); // by default it'll log queries result on console
    }


}
