package com.ttknp.abchelperconnectdatabaseh2.services;

import com.ttknp.abchelperconnectdatabaseh2.services.common.ModelService;
import com.ttknp.abcmodelsservice.models.mysql_cl.MySQL_CL;
import com.ttknp.abcmodelsservice.models.h2.UserH2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

// ** multiple connect db
@Service
public class UserH2Service extends ModelService<UserH2> {

    private final JdbcTemplate jdbcTemplate;
    private List<UserH2> users;
    private final Logger log;

    @Autowired // ** try manual on mysql
    public UserH2Service(@Qualifier("dataSourceH2") DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        users = new ArrayList<>();
        log = LoggerFactory.getLogger(UserH2Service.class);
    }

    @Override
    public List<UserH2> retrieveAll() {
        // queries with sql file
        users.clear();
        loadScript("reset-users-h2.sql",jdbcTemplate.getDataSource());
        users = jdbcTemplate.query(MySQL_CL.H2_USERS_SELECT_ALL, (rs, rowNum) -> {
            UserH2 userH2 = new UserH2(
                    rs.getLong("id"),
                    rs.getString("username"),
                    rs.getString("mail")
            );
            return userH2;
        });
        return users;
    }

    @Override
    public Boolean add(UserH2 user) {
        Integer row = jdbcTemplate.update(MySQL_CL.H2_USERS_CREATE_NEW,
                user.getUsername(),
                user.getMail());
        logRowAffected(row);
        if (row > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean edit(UserH2 userH2, Long pk) {
        Integer row = jdbcTemplate.update(MySQL_CL.H2_USERS_UPDATE,
                userH2.getUsername(),
                userH2.getMail(),
                pk
                );
        logRowAffected(row);
        if (row > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean remove(Long pk) {
        Integer row = jdbcTemplate.update(MySQL_CL.H2_USERS_DELETE, pk);
        logRowAffected(row);
        if (row > 0) {
            return true;
        }
        return false;
    }

    // *** U can be any types
    @Override
    public <U> Boolean removeModelByAnything(U uniqueKey) {
        Integer row = jdbcTemplate.update(MySQL_CL.H2_USERS_DELETE, uniqueKey);
        logRowAffected(row);
        if (row > 0) {
            return true;
        }
        return false;
    }

    @Override
    public <U> void loadScript(String fileName) {

    }

    @Override
    public List<UserH2> retrieveAllAndSort(String sortField, String sortDesc) {
        users.clear();
        loadScript("reset-users-h2.sql",jdbcTemplate.getDataSource());
        // ** Only values can be bound as parameters. Not parts of the query itself.
        String sql = String.format(MySQL_CL.H2_USERS_SELECT_ALL_AND_SORT, sortField,sortDesc);
        users = jdbcTemplate.query(sql, (rs, rowNum) -> {
            UserH2 userH2 = new UserH2(
                    rs.getLong("id"),
                    rs.getString("username"),
                    rs.getString("mail")
            );
            return userH2;
        });
        return users;
    }

    private void logRowAffected(Integer row) {
        log.debug("Users row affected : {}" , row);
    }

    /**
    public List<UserH2> retrieveAllUsers() {
        users = jdbcTemplate.query(MySQL_CL.H2_USERS_SELECT_ALL, (rs, rowNum) -> {
            UserH2 userH2 = new UserH2(
                    rs.getLong("id"),
                    rs.getString("username"),
                    rs.getString("mail")
            );
            return userH2;
        });
        return users;
    }

    public Boolean addUser(UserH2 user) {
        Integer row = jdbcTemplate.update(MySQL_CL.H2_USERS_CREATE_NEW,
                user.getUsername(),
                user.getMail());
        log.debug("User row affected : {}" , row);
        if (row > 0) {
            return true;
        }
        return false;
    }
    */

}
