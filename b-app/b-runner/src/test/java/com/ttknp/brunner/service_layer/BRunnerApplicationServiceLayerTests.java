package com.ttknp.brunner.service_layer;

import com.ttknp.abchelperconnectdatabaseh2.services.RobotH2Service;
import com.ttknp.abchelperconnectdatabaseh2.services.UserH2Service;
import com.ttknp.abcmodelsservice.models.h2.RobotH2;
import com.ttknp.abcmodelsservice.models.h2.UserH2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

// concept is mocking
// **
// ** JUnit 5 + Mockito ** For testing Service Logic Not API
// **
// @SpringBootTest(classes = {UserH2Service.class, RobotH2Service.class})
// จากการที่ทดลองใช้ JUnit 5 กับ Mockito บน Spring Boot project นั้นส่วนใหญ่จะไม่แตกต่างจากการใช้งาน JUnit 4 กับ Mockito มากนัก
// ** อาจจะมีเปลี่ยน annotation จาก @RunWith เป็น @ExtendWith และ dependency ที่ต้องเพิ่มเองเท่านั้น
@ExtendWith(MockitoExtension.class)
public class BRunnerApplicationServiceLayerTests  {

    // Importance! it's not using in this class test but ,it's relation in service classes that use with @InjectMocks
    @Mock
    @Qualifier("dataSourceH2")
    public DataSource dataSource;
    @Mock
    @Qualifier("dataSourceH2Extra")
    public DataSource dataSourceExtra;

    @Mock
    public JdbcTemplate jdbcTemplate;
    @Mock
    public JdbcTemplate jdbcTemplateExtra;

    @Spy
    @InjectMocks
    public UserH2Service userH2Service;

    @InjectMocks
    public RobotH2Service robotH2Service;

    @BeforeEach
    void setUp() throws Exception {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl("jdbc:h2:B:/h2-database/db/database_b_web_controller");
        dataSource.setUsername("sa");
        dataSource.setPassword("");
        dataSource.setDriverClassName("org.h2.Driver");

        DriverManagerDataSource dataSourceExtra = new DriverManagerDataSource();
        dataSourceExtra.setUrl("jdbc:h2:B:/h2-database/db/database_b_extra_web_controller");
        dataSourceExtra.setUsername("sa");
        dataSourceExtra.setPassword("");
        dataSourceExtra.setDriverClassName("org.h2.Driver");

        jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplateExtra = new JdbcTemplate(dataSourceExtra);

        // the once way to mock services layer in jdbc ** this way is not working : userH2Service = new UserH2Service(jdbcTemplate.getDataSource());
        // ** you need to create a MOCK of service first, and then use that mock.
        userH2Service = mock(UserH2Service.class);
        robotH2Service = mock(RobotH2Service.class);
    }

    @Test
    public void testJDBCConnections() throws SQLException {
        Connection con = jdbcTemplate.getDataSource().getConnection();
        Connection conExtra = jdbcTemplateExtra.getDataSource().getConnection();
        assertNotNull(con);
        assertNotNull(conExtra);
        assertTrue(con.isValid(0));
        assertTrue(conExtra.isValid(0));
        con.close();
        conExtra.close();
    }


    @Nested
    protected class UserH2ServiceTest {

        @Test
        public void testRetrieveAllOnUserH2Service() {
            // keep concept mocking
            /**
             So, we have to tell Mockito to return something
             when userRepository.findAll() is called.
             We do this with the static when method.
             */
            when(userH2Service.retrieveAll()).thenReturn(getUsers());
            /*
            Now users object called userRepository.findAll() passed userService.reads()
            So it should store list of getUsers() method
            */
            List<UserH2> users = userH2Service.retrieveAll();

            assertEquals("alex ryder", users.get(0).getUsername());
            assertThat(users).hasSize(3);

            verify(userH2Service, times(1)).retrieveAll(); // verify (v. ตรวจสอบ) , invocations (n. การร้องขอ)
        }

        @Test
        public void testAddOnUserH2Service() {
            // keep concept mocking
            /**
             So, we have to tell Mockito to return something
             when userRepository.findAll() is called.
             We do this with the static when method.
             */
            UserH2 userH2 = getUser(1000L);
            when(userH2Service.add(userH2)).thenReturn(true);
        /*
        Now users object called userRepository.findAll() passed userService.reads()
        So it should store list of getUsers() method
        */
            Boolean result = userH2Service.add(userH2);

            assertEquals(true,result);

            verify(userH2Service, times(1)).add(userH2); // verify (v. ตรวจสอบ) , invocations (n. การร้องขอ)
        }


        private List<UserH2> getUsers() {
            return List.of(
                    new UserH2(1000L, "alex ryder", "alexryder@gmail.com"),
                    new UserH2(1001L, "alex slider", "alexslider@gmail.com"),
                    new UserH2(1002L, "alex owner", "alexrowner@gmail.com")
            );
        }

        private UserH2 getUser(Long id) {
            return new UserH2(id, "alex ryder", "alexryder@gmail.com");
        }
    }


    @Nested
    protected class RobotH2ServiceTest {

        @Test
        public void testRetrieveAllOnRobotH2Service() {

            when(robotH2Service.retrieveAll()).thenReturn(getRobots());

            List<RobotH2> robots = robotH2Service.retrieveAll();


            assertEquals("RX-0", robots.get(0).getCodename());
            assertThat(robots).hasSize(3);

            verify(robotH2Service, times(1)).retrieveAll(); // verify (v. ตรวจสอบ) , invocations (n. การร้องขอ)
        }

        private List<RobotH2> getRobots() {
            return List.of(
                    new RobotH2(1000L,"RX-0","2025-09-31",700000.0,true),
                    new RobotH2(1001L,"RX-1","2025-10-31",800000.0,true),
                    new RobotH2(1002L,"RX-2","2025-11-31",900000.0,true)
            );
        }
    }

}
