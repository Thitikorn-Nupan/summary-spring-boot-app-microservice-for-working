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
    public Boolean add(RobotH2 robotH2) {
        return false;
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
