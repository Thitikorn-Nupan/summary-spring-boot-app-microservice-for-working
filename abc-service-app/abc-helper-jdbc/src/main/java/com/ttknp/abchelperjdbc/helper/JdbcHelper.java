package com.ttknp.abchelperjdbc.helper;

import com.ttknp.abchelperjdbc.service.JdbcMapRowService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import javax.sql.DataSource;
import java.util.List;
import java.util.Map;


@Component // ** if i mark @Component i can pass parameter as @Qualifier("dataSource") DataSource dataSource to constructor when inject this bean
public class JdbcHelper {

    private final JdbcTemplate jdbcTemplate;
    private final Logger log = LoggerFactory.getLogger(JdbcHelper.class);

    public JdbcHelper(DataSource dataSourceSQL) {
        this.jdbcTemplate = new JdbcTemplate(dataSourceSQL);
    }


    // (1) update(...) methods work for update,delete,insert statement only!
    public int executeSQLStatement(String statement, Object... params) { // Object... theValues can be (sql,1,"1",'1',true) or null (sql)
        return this.jdbcTemplate.update(statement, params);
    }

    // (2) update(...) by another jdbc template instance same (1)
    public int executeSQLStatement(DataSource dataSource, String statement, Object... params) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate.update(statement, params);
    }

    // (3) update(...) it's same (1),(2) , but difference type of params
    public <E> int executeSQLStatement(DataSource dataSource, String statement, List<E> params) {
        Object[] paramObjectArray = params.toArray();
        // list => [Jacky, Aldo, 29, Jacky@aldo.com, 0913231933, 3] & object => Jacky, Aldo, 29, Jacky@aldo.com, 0913231933, 3
        log.info("params as list {}\nparams as object {}",params,paramObjectArray);
        return this.executeSQLStatement(dataSource, statement, paramObjectArray);
    }

    // (4) queryForObject(...) without RowMapper class will return only one thing as int , boolean , string , char
    /**
     example ,
     public Integer getCountOfGenderInAGrade(String gender, Integer grade) {
        String sql = "select count(1) as total from student where gender = ? and grade = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, gender, grade);
     }
    */
    public <T> T executeSQLStatementForObject(Class<T> aClass, String statement, Object... params) {
        // queryForObject(...) meant for handling a DB query result with a single row !
        // With Class<T> it returns follow generic type
        return this.jdbcTemplate.queryForObject(statement, aClass, params);
    }

    // (5) queryForObject(...) same (4) but difference type params
    public <T, E> T executeSQLStatementForObject(Class<T> theClass, String theSQL, List<E> params) {
        Object[] paramObjectArray = params.toArray();
        // list => [Jacky, Aldo, 29, Jacky@aldo.com, 0913231933, 3] & object => Jacky, Aldo, 29, Jacky@aldo.com, 0913231933, 3
        log.info("params as list {}\nparams as object {}",params,paramObjectArray);
        return this.executeSQLStatementForObject(theClass, theSQL, paramObjectArray);
    }

    // Real shit! ********************************************* (6) single row , (7) many rows
    // (6) queryForObject(...) with RowMapper class
    // So you inherit only 1 RowMapper (method) and you'll work with methods that return T or List<T> !
    public <T> T executeSQLStatementForObject(String statement, RowMapper<T> rowMapper, Object... params) {
        // User user = queryForObject("select * from A_APP.USERS_DETAIL where ID = ?;", (rs,rowNum) -> {...} , 1)
        return this.jdbcTemplate.queryForObject(statement, rowMapper , params); // should get only 1 ** follow generic type
    }

    // (7) query(...) with RowMapper class it'll loop and map to list
    public <T> List<T> executeSQLStatementForObject(String statement, RowMapper<T> rowMapper) {
        // can call like this. jdbcHelper.executeSQLStatementForObject(sql, this , id);
        return this.jdbcTemplate.query(statement, rowMapper); // should get as array list ** follow generic type
    }


    // Real shit! ********************************************* (8) single row , (9) many rows ,Both (8),(9) use the same RowMapper class
    // (8) returns as Map<String, String> and it only one index!
    public <E> Map<String, String> executeSQLStatementForObject(String statement, List<E> params) {
        // sql statement , map , object
        return this.jdbcTemplate.queryForObject(statement, (rs, rowNum) -> JdbcMapRowService.mapRow(rs,rowNum) , params.toArray());
    }

    // (9) returns as List< Map<String, String> > and it many index!
    public List< Map<String, String> > executeSQLStatementForObject(String statement, Object ...params) {
        return this.jdbcTemplate.query(statement, (rs, rowNum) -> JdbcMapRowService.mapRow(rs,rowNum) , params );
    }


}
