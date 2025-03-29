package com.ttknp.abchelperconnectdatabaseh2.services.common;


import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;
import java.util.List;
import java.util.Objects;

// use abs class with extends
public abstract class ModelService <T> {
    private String sqlScriptDirOnRoot = "sql/";
    public abstract List<T> retrieveAll() ;
    public abstract Boolean add(T t);
    public void loadScript(String fileName, DataSource dataSource) {
        String fullSqlScriptDirOnRoot = sqlScriptDirOnRoot + fileName;
        // ** way to query with script sql ** if you want queries response don't do the way
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScripts(new ClassPathResource(fullSqlScriptDirOnRoot)); // ClassPathResource class it looks to src of this module
        populator.execute(Objects.requireNonNull(dataSource)); // by default it'll log queries result on console
    }
}
