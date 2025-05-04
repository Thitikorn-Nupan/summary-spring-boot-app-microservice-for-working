package com.ttknp.abchelperconnectdatabasemysql.service;

import com.ttknp.abchelperconnectdatabasemysql.service.common.ModelService;
import com.ttknp.abcmodelsservice.models.mysql.ToyMYSQL;
import com.ttknp.abcmodelsservice.models.mysql_cl.MySQL_CL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ToyMYSQLService extends ModelService<ToyMYSQL> {
    private JdbcTemplate jdbcTemplate;
    private List<ToyMYSQL> toys;
    private Logger log;
    @Autowired
    public ToyMYSQLService(@Qualifier("dataSourceMySQLExtra") DataSource dataSource ) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.toys = new ArrayList<>();
        this.log = LoggerFactory.getLogger(ToyMYSQLService.class);
    }

    /* public List<ToyMYSQL> getToys() {
        products = jdbcTemplate.query(MySQL_CL.MYSQL_TOYS_SELECT_ALL, (rs, rowNum) -> {
            ToyMYSQL toyMYSQL = new ToyMYSQL(
                    rs.getLong("tid"),
                    rs.getString("name"),
                    rs.getString("status"),
                    rs.getDouble("price"),
                    rs.getTimestamp("release_date")
            );
            return toyMYSQL;
        });
        return products;
    }*/

    @Override
    public List<ToyMYSQL> retrieveAll() {
        toys.clear();
        toys = jdbcTemplate.query(MySQL_CL.MYSQL_TOYS_SELECT_ALL, this);
        return toys;
    }


    @Override
    public Boolean add(ToyMYSQL toyMYSQL) {
        return null;
    }

    @Override
    public Boolean edit(ToyMYSQL toyMYSQL, Long pk) {
        return null;
    }

    @Override
    public Boolean remove(Long pk) {
        return null;
    }

    @Override
    public <U> Boolean removeModelByAnything(U uniqueKey) {
        return null;
    }

    @Override
    public <U> void loadScriptAbsPath(String fileName) {}


    @Override
    public ToyMYSQL mapRow(ResultSet rs, int rowNum) throws SQLException {
        log.info("row number is {}",rowNum );
        return new ToyMYSQL(
                rs.getLong("tid"),
                rs.getString("name"),
                rs.getString("status"),
                rs.getDouble("price"),
                rs.getTimestamp("release_date")
        );
    }
}
