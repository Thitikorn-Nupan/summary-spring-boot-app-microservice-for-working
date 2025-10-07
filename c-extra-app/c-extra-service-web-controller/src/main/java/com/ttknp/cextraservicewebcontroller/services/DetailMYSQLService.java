package com.ttknp.cextraservicewebcontroller.services;

import com.ttknp.abchelperjdbc.helper.JdbcHelper;
import com.ttknp.abchelperjdbc.utility.JdbcAndUtility;
import com.ttknp.abcmodelsservice.models.mysql.DetailMYSQL;
import com.ttknp.abcmodelsservice.models.mysql_cl.MySQL_CL;
import com.ttknp.cextraservicewebcontroller.services.common.ModelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service // use this bean by inject
public class DetailMYSQLService extends ModelService<DetailMYSQL> {

    private final Logger log;
    private final JdbcHelper jdbcHelper;
    private final JdbcAndUtility jdbcAndUtility;
    private final DataSource dataSource;

    // Note, one bean can use many times!! at the same process
    public DetailMYSQLService(@Qualifier("dataSourceMySQLExtra") DataSource dataSource) {
        this.jdbcHelper = new JdbcHelper(dataSource);
        this.jdbcAndUtility = new JdbcAndUtility();
        this.dataSource = dataSource; // for load script as file
        this.log = LoggerFactory.getLogger(DetailMYSQLService.class);
    }

    @Override
    public List<DetailMYSQL> retrieveAll() {

        try {
            this.jdbcAndUtility.loadScriptAbsPath("truncate-details-mysql.sql", dataSource); // truncate table and clear auto increment
            this.jdbcAndUtility.loadScriptAbsPath("reset-details-mysql.sql", dataSource);
            log.info("resetting all details successfully");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        List<Map<String, String>> listMap = jdbcHelper.executeSQLStatementForObject(MySQL_CL.MYSQL_DETAIL_SELECT_ALL);
        return convertListMapToListDetailMYSQL(listMap);
    }

    @Override
    public <U> DetailMYSQL retrieveBy(U key) {
        List<String> params = new ArrayList<>();
        params.add(key.toString());
        Map<String, String> map = jdbcHelper.executeSQLStatementForObject(MySQL_CL.MYSQL_DETAIL_SELECT_BY_CODE,params);
        return convertMapToDetailMYSQL(map);
    }

    @Override
    public <U, E > E retrieveColumnBy(U key) {
        String code = jdbcHelper.executeSQLStatementForObject(String.class,MySQL_CL.MYSQL_DETAIL_SELECT_CODE_BY_DID,key); //
        return (E) code;
    }


    private List<DetailMYSQL> convertListMapToListDetailMYSQL(List<Map<String, String>> listMap) {
        List<DetailMYSQL> detailMYSQLList = new ArrayList<>();
        for (Map<String, String> map : listMap) {
            // Note get("<the column name is case sensitive>")
            DetailMYSQL detailMYSQL = new DetailMYSQL(
                    Long.parseLong(map.get("did")),
                    map.get("code")
            );
            detailMYSQLList.add(detailMYSQL);
        }
        return detailMYSQLList;
    }

    private DetailMYSQL convertMapToDetailMYSQL(Map<String, String> map) {
        return new DetailMYSQL(
                Long.parseLong(map.get("did")),
                map.get("code")
        );
    }

}
