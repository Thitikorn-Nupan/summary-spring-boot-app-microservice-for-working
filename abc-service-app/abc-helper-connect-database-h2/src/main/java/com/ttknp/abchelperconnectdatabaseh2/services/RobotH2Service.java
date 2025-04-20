package com.ttknp.abchelperconnectdatabaseh2.services;

import com.ttknp.abchelperconnectdatabaseh2.services.common.ModelService;
import com.ttknp.abcmodelsservice.models.mysql_cl.MySQL_CL;
import com.ttknp.abcmodelsservice.models.h2.RobotH2;
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
public class RobotH2Service extends ModelService<RobotH2> {

    private JdbcTemplate jdbcTemplate;
    private List<RobotH2> robots;
    private Logger log;

    @Autowired
    public RobotH2Service(@Qualifier("dataSourceH2Extra") DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.robots = new ArrayList<>();
        this.log = LoggerFactory.getLogger(RobotH2Service.class);
    }

    @Override
    public List<RobotH2> retrieveAll() {
        robots.clear();
        loadScript("reset-robots-h2.sql",jdbcTemplate.getDataSource());
        robots = jdbcTemplate.query(MySQL_CL.H2_EXTRA_ROBOT_SELECT_ALL, (rs, rowNum) -> {
            RobotH2 robotH2 = new RobotH2(
                    rs.getLong("rid"),
                    rs.getString("codename"),
                    rs.getString("releasedate"),
                    rs.getDouble("price"),
                    rs.getBoolean("status")
            );
            return robotH2;
        });
        return robots;
    }

    @Override
    public List<RobotH2> retrieveAllAndSort(String sortField, String sortDesc) {
        robots.clear();
        loadScript("reset-robots-h2.sql",jdbcTemplate.getDataSource());
        String sql = String.format(MySQL_CL.H2_EXTRA_ROBOT_SELECT_ALL_AND_SORT, sortField,sortDesc);
        robots = jdbcTemplate.query(sql, (rs, rowNum) -> {
            RobotH2 robotH2 = new RobotH2(
                    rs.getLong("rid"),
                    rs.getString("codename"),
                    rs.getString("releasedate"),
                    rs.getDouble("price"),
                    rs.getBoolean("status")
            );
            return robotH2;
        });
        return robots;
    }

    @Override
    public Boolean add(RobotH2 robotH2) {
        Integer row = jdbcTemplate.update(MySQL_CL.H2_EXTRA_ROBOT_CREATE_NEW,
                robotH2.getRid(),
                robotH2.getCodename(),
                robotH2.getReleaseDate(),
                robotH2.getPrice(),
                robotH2.getStatus());
        logRowAffected(row);
        if (row > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean edit(RobotH2 robotH2, Long pk) {
        Integer row = jdbcTemplate.update(MySQL_CL.H2_EXTRA_ROBOT_UPDATE,
                robotH2.getCodename(),
                robotH2.getReleaseDate(),
                robotH2.getPrice(),
                robotH2.getStatus(),
                pk);
        logRowAffected(row);
        if (row > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean remove(Long pk) {
        Integer row = jdbcTemplate.update(MySQL_CL.H2_EXTRA_ROBOT_DELETE, pk);
        logRowAffected(row);
        if (row > 0) {
            return true;
        }
        return false;
    }

    // *** U can be any types
    @Override
    public <U> Boolean removeModelByAnything(U uniqueKey) {
        Integer row = jdbcTemplate.update(MySQL_CL.H2_EXTRA_ROBOT_DELETE, uniqueKey);
        logRowAffected(row);
        if (row > 0) {
            return true;
        }
        return false;
    }

    @Override
    public <U> void loadScript(String fileName) {

    }


    private void logRowAffected(Integer row) {
        log.debug("Robots row affected : {}" , row);
    }

    /**
    public List<RobotH2> retrieveAllRobots() {
        robots = jdbcTemplate.query(MySQL_CL.H2_EXTRA_ROBOT_SELECT_ALL, (rs, rowNum) -> {
            RobotH2 robotH2 = new RobotH2(
                    rs.getLong("rid"),
                    rs.getString("codename"),
                    rs.getString("releasedate"),
                    rs.getDouble("price"),
                    rs.getBoolean("status")
            );
            return robotH2;
        });
        return robots;
    }
    */
}
