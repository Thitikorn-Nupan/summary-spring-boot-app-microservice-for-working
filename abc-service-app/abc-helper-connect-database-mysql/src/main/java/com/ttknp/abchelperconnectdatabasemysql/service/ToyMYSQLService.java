package com.ttknp.abchelperconnectdatabasemysql.service;

import com.ttknp.abcmodelsservice.models.mysql.ProductMYSQL;
import com.ttknp.abcmodelsservice.models.mysql.ToyMYSQL;
import com.ttknp.abcmodelsservice.models.mysql_cl.MySQL_CL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Service
public class ToyMYSQLService {
    private JdbcTemplate jdbcTemplate;
    private List<ToyMYSQL> products;
    private Logger log;
    @Autowired
    public ToyMYSQLService(@Qualifier("dataSourceMySQLExtra") DataSource dataSource ) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.products = new ArrayList<>();
        this.log = LoggerFactory.getLogger(ToyMYSQLService.class);
    }
    public List<ToyMYSQL> getToys() {
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
    }
}
