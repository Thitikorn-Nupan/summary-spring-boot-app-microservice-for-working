package com.ttknp.abchelperconnectdatabaseh2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserH2Service {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public UserH2Service(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void testSelectAll () {
        jdbcTemplate.query("SELECT * FROM public.users;", (rs, rowNum) -> {
            System.out.println(rs.getString("username"));
            return null;
        });
    }

}
