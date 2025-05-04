package com.ttknp.abchelperconnectdatabasemysql.service.common;


import com.ttknp.abcmodelsservice.models.paths.AbsolutePathUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;
import java.io.File;
//import java.io.FileInputStream;
import java.io.IOException;
//import java.io.InputStream;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
// use abs class with extends
public abstract class ModelService<T> implements RowMapper<T> {

    private final Logger log = LoggerFactory.getLogger(ModelService.class);

    private String sqlScriptDirOnAbs = AbsolutePathUtility.SQL_SCRIPT_DIR_ON_PARENT;
    public abstract List<T> retrieveAll() ;
    //  public abstract List<T> retrieveAllBySort(String key , String description) ; did it on abc-helper-connect-database-h2 module
    public abstract Boolean add(T t);
    public abstract Boolean edit(T t,Long pk);
    public abstract Boolean remove(Long pk);
    // ** <U> can be any types
    // ** generic with parameter can be any type
    public abstract <U> Boolean removeModelByAnything(U uniqueKey);
    // ** generic with void method
    public abstract <U> void loadScriptAbsPath(String fileName);

    // ** query script (as file) *** query by ResourceDatabasePopulator So , you can have many sql statements in script as reset-products-mysql.sql
    public void loadScriptAbsPath(String fileName, DataSource dataSource) {
        String fullSqlScriptDirOnRoot = sqlScriptDirOnAbs + fileName;
        // ** way to query with script sql ** if you want queries response don't do the way
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScripts(new FileSystemResource(fullSqlScriptDirOnRoot)); // FileSystemResource class it looks to abs path
        populator.execute(Objects.requireNonNull(dataSource)); // by default it'll log queries result on console
    }
    // ** query script (as statement) and pass params *** query by JdbcTemplate
    // ** (MYSQL ONLY) you can have only one sql statement in script as demo-products-mysql-params.sql
    public void loadScriptAbsPath( String fileName, JdbcTemplate jdbcTemplateSQL, HashMap<String,String> params) throws  IOException { // Class aClass,
        String fullSqlScriptDirOnRoot = sqlScriptDirOnAbs + fileName;
        StringBuilder stringBuilder = readSQLFileAsStatement(fullSqlScriptDirOnRoot);
        String sql = stringBuilder.toString();
        log.info("Loaded sql script as statement  {}" , sql);

        int paramCount = params.size();
        String[] keys = new String[paramCount];
        String[] values = new String[paramCount];
        int i = 0;
        for (String key : params.keySet()) {
            keys[i] = key;
            i++;
        }
        i=0;
        for (String value : params.values()) {
            values[i] = value;
            i++;
        }
        for (i = 0; i < paramCount ; i++) {
            sql = sql.replace(keys[i], values[i]); // replace and update
           //  log.info("Pass params to sql statement {}",sql);
        }

        // if do this way script must not contain the comment in sql script or contain it on the last lines
        jdbcTemplateSQL.execute(sql); // in mysql you can't execute many statements by execute(<queries>)
        log.info("Queried sql script as statement {}" , sql);
    }


    // ** InputStream works for root dir but this case i work with absolute dir so i use File instead
    private StringBuilder readSQLFileAsStatement(String filePath) throws IOException { // Class aClass,
        // InputStream inputStream = aClass.getClassLoader().getResourceAsStream(filePath);
        File file = new File(filePath);
        if (file == null) {
            throw new IllegalArgumentException("File not found: " + filePath);
        }
        // convert to useful form
        Scanner in = new Scanner(file);
        // read contents
        StringBuilder sqlScriptBuilder = new StringBuilder();
        while (in.hasNext()) {
            sqlScriptBuilder.append(in.nextLine());
        }
        // close file
        in.close();
        return sqlScriptBuilder;
    }

}
