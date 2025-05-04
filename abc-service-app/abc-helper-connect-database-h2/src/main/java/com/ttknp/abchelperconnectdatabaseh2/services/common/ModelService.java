package com.ttknp.abchelperconnectdatabaseh2.services.common;


import com.ttknp.abcmodelsservice.models.paths.AbsolutePathUtility;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;
import java.util.List;
import java.util.Objects;

// use abs class with extends
public abstract class ModelService <T> {
    private String sqlScriptDirOnRoot = AbsolutePathUtility.SQL_SCRIPT_DIR_ON_ROOT;
    public abstract List<T> retrieveAll() ;
    public abstract Boolean add(T t);
    public abstract Boolean edit(T t,Long pk);
    public abstract Boolean remove(Long pk);
    // ** <U> can be any types
    // ** generic with parameter can be any type
    public abstract <U> Boolean removeModelByAnything(U uniqueKey);
    // ** generic with void method
    public abstract <U> void loadScript(String fileName);
    public void loadScript(String fileName, DataSource dataSource) {
        String fullSqlScriptDirOnRoot = sqlScriptDirOnRoot + fileName;
        // ** way to query with script sql ** if you want queries response don't do the way
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScripts(new ClassPathResource(fullSqlScriptDirOnRoot)); // ClassPathResource class it looks to src of this module
        populator.execute(Objects.requireNonNull(dataSource)); // by default it'll log queries result on console
    }
    // **
    public abstract List<T> retrieveAllAndSort(String sortField, String sortDesc) ;


}
