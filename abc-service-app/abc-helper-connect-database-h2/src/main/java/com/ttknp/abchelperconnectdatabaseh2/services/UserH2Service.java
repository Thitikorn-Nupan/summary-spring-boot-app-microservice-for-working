package com.ttknp.abchelperconnectdatabaseh2.services;

import com.ttknp.abchelperconnectdatabaseh2.mysql_cl.MySQL_CL;
import com.ttknp.abcmodelsservice.models.h2.UserH2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserH2Service {

    private JdbcTemplate jdbcTemplate;
    private List<UserH2> users;
    private Logger log;

    @Autowired
    public UserH2Service(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        users = new ArrayList<>();
        log = LoggerFactory.getLogger(UserH2Service.class);
    }

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

}
