package com.ttknp.cextraservicewebcontroller.services;

import com.ttknp.abchelperjdbc.helper.JdbcHelper;
import com.ttknp.abchelperjdbc.utility.JdbcAndUtility;
import com.ttknp.abcmodelsservice.models.mysql.ToyMYSQL;
import com.ttknp.abcmodelsservice.models.mysql_cl.MySQL_CL;
import com.ttknp.cextraservicewebcontroller.services.common.ModelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service // use this bean by inject
public class ToyMYSQLService extends ModelService<ToyMYSQL> implements RowMapper<ToyMYSQL> {

    private final Logger log ;
    private final JdbcHelper jdbcHelper;
    private final JdbcAndUtility jdbcAndUtility;
    private final DataSource dataSource;

    // Note, one bean can use many times!! at the same process
    public ToyMYSQLService(@Qualifier("dataSourceMySQLExtra") DataSource dataSource) {
        this.jdbcHelper = new JdbcHelper(dataSource);
        this.dataSource = dataSource;
        this.jdbcAndUtility = new JdbcAndUtility();
        this.log = LoggerFactory.getLogger(ToyMYSQLService.class);
    }

    @Override
    public List<ToyMYSQL> retrieveAll() {
        List<Map<String, String>> listMap = jdbcHelper.executeSQLStatementForObject(MySQL_CL.MYSQL_TOYS_SELECT_ALL);
        return convertListMapToListToyMYSQL(listMap);
    }

    public <U> List<ToyMYSQL> retrieveAll(U whateverParamIJustWantSameName) {
        try {
            this.jdbcAndUtility.loadScriptAbsPath("truncate-toys-mysql.sql", dataSource); // truncate table and clear auto increment
            this.jdbcAndUtility.loadScriptAbsPath("reset-toys-mysql.sql", dataSource);
            log.info("resetting all details successfully");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return jdbcHelper.executeSQLStatementForObject(MySQL_CL.MYSQL_TOYS_SELECT_ALL,this);
    }

    public <U> ToyMYSQL retrieveBy(U whateverParamIJustWantSameName,String name) {
        return jdbcHelper.executeSQLStatementForObject(MySQL_CL.MYSQL_TOYS_SELECT_BY_NAME,this, name);
    }

    @Override
    public <U> ToyMYSQL retrieveBy(U key) {
        List<String> params = new ArrayList<>();
        params.add(key.toString());
        Map<String, String> map = jdbcHelper.executeSQLStatementForObject(MySQL_CL.MYSQL_TOYS_SELECT_BY_TID, params);
        return convertMapToToyMYSQL(map);
    }

    @Override
    public <U, E> E retrieveColumnBy(U key) {
        List<String> params = new ArrayList<>();
        params.add(key.toString());
        return (E) jdbcHelper.executeSQLStatementForObject(Long.class,MySQL_CL.MYSQL_TOYS_SELECT_TID_BY_NAME,params);
    }

    private List<ToyMYSQL> convertListMapToListToyMYSQL(List<Map<String, String>> listMap) {
        List<ToyMYSQL> toyMYSQLList = new ArrayList<>();
        for (Map<String, String> map : listMap) {
            // Note get("<the column name is case sensitive>")
            ToyMYSQL toyMYSQL = new ToyMYSQL(
                    Long.parseLong(map.get("tid")),
                    map.get("name"),
                    map.get("status"),
                    Double.parseDouble(map.get("price")),
                    Timestamp.valueOf(map.get("release_date"))
            );
            toyMYSQLList.add(toyMYSQL);
        }
        return toyMYSQLList;
    }

    private ToyMYSQL convertMapToToyMYSQL(Map<String, String> map) {
        return new ToyMYSQL(
                Long.parseLong(map.get("tid")),
                map.get("name"),
                map.get("status"),
                Double.parseDouble(map.get("price")),
                Timestamp.valueOf(map.get("release_date"))
        );
    }

    @Override
    public ToyMYSQL mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new ToyMYSQL(
                rs.getLong("tid"),
                rs.getString("name"),
                rs.getString("status"),
                rs.getDouble("price"),
                rs.getTimestamp("release_date")
        );
    }
}
