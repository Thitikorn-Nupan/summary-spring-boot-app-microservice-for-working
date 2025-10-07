package com.ttknp.abchelperjdbc.utility;

import com.ttknp.abcmodelsservice.models.paths.AbsolutePathUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;

public class JdbcAndUtility {

    private final Logger log = LoggerFactory.getLogger(JdbcAndUtility.class);

    private StringBuilder readSQLFileAsStatement(String absPath) throws IOException {
        File file = new File(absPath);
        if (file == null) {
            throw new IllegalArgumentException("File not found: " + absPath);
        }
        Scanner in = new Scanner(file);
        StringBuilder sqlScriptBuilder = new StringBuilder();
        while (in.hasNext()) {
            sqlScriptBuilder.append(in.nextLine());
        }
        in.close();
        return sqlScriptBuilder;
    }

    // ** way to query with script sql ** if you want queries response don't do the way
    // ** query script (it's file!) *** query by ResourceDatabasePopulator **  you can have many sql command in script
    // by default it'll log queries result on console
    public void loadScriptAbsPath(String fileName, DataSource dataSource) {
        String fullSqlScriptDirOnRoot = AbsolutePathUtility.SQL_SCRIPT_DIR_ON_PARENT + fileName;
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScripts(new FileSystemResource(fullSqlScriptDirOnRoot)); // FileSystemResource class it looks to abs path
        populator.execute(Objects.requireNonNull(dataSource));
    }

    // ** query script (as statement) and pass params *** query by JdbcTemplate ** (MYSQL ONLY) you can have only one sql statement in script as demo-products-mysql-params.sql
    public void loadScriptAbsPath(String fileName, JdbcTemplate jdbcTemplateSQL, HashMap<String,String> params) throws  IOException { // Class aClass,
        String fullSqlScriptDirOnRoot = AbsolutePathUtility.SQL_SCRIPT_DIR_ON_PARENT + fileName;
        StringBuilder stringBuilder = readSQLFileAsStatement(fullSqlScriptDirOnRoot);
        String sql = stringBuilder.toString();
        log.info("Loaded sql script as statement  {}" , sql);
        int paramCount = params.size();
        String[] keys = new String[paramCount];
        String[] values = new String[paramCount];

        for (int i = 0; i < paramCount; i++) {
            String key = params.keySet().toArray()[i].toString();
            String value = params.values().toArray()[i].toString();
            keys[i] = key;
            values[i] = value;
        }

        for (int i = 0; i < paramCount ; i++) {
            sql = sql.replace(keys[i], values[i]); // replace and update
        }
        jdbcTemplateSQL.execute(sql); // in mysql you can't execute many statements by execute(<queries>)
        log.info("Queried sql script as statement {}" , sql);
    }

}
