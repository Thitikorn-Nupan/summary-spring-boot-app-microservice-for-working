package com.ttknp.brunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ttknp.abchelperconnectdatabaseh2.services.RobotH2Service;
import com.ttknp.abchelperconnectdatabaseh2.services.UserH2Service;
import com.ttknp.abcmodelsservice.models.h2.RobotH2;
import com.ttknp.abcmodelsservice.models.h2.UserH2;
import com.ttknp.bwebcontroller.web.controllers.BController;
import com.ttknp.bwebcontroller.web.privatescontrollers.RobotH2Controller;
import com.ttknp.bwebcontroller.web.privatescontrollers.UserH2Controller;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// *** use the @WebMvcTest annotation to load only UserController class. (can multiple rest controller)
@WebMvcTest(controllers = {BController.class, RobotH2Controller.class ,UserH2Controller.class})
class BRunnerApplicationTests {

    // *** using MockMvc class to make REST API calls.
    @Autowired
    private MockMvc mockMvc;
    // don't forget inject your services layer
    @MockitoBean
    private UserH2Service userH2Service;
    @MockitoBean
    private RobotH2Service robotH2Service;

    @Nested
    protected class UserH2Tests {

        // /b/robot-h2
        @Test
        public void testRetrieveAllUsers() throws Exception {

            /// ** provide response if service calls ** it's kinda same as when(...).return(...)
            given(userH2Service.retrieveAll()).willReturn(getUsers());


            /// ** call the provider **
            // when -  action or the behaviour(n.พฤติกรรม) that we are going test
            RequestBuilder request = MockMvcRequestBuilders.get("/b/user-h2");
            ResultActions response = mockMvc.perform(request);

            ///  ** result follow your api response
            response.andExpect(status().isAccepted())
                    .andExpect(content().contentType("application/json"))
                    .andExpect(jsonPath("$[0].username").value("alex ryder"))
                    .andDo(print());

        }

        @Test
        public void testExceptionHandlerOnRetrieveUser() throws Exception {

            /// ** call the provider **
            // when -  action or the behaviour(n.พฤติกรรม) that we are going test
            RequestBuilder request = MockMvcRequestBuilders.get("/b/user-h2/searchBy")
                    .param("username","T")
                    .param("id", String.valueOf(0));

            ResultActions response = mockMvc.perform(request);

            ///  ** result follow your api response
            response.andExpect(status().isNotAcceptable())
                    .andExpect(header().string("Message","Test Exception Handler"))
                    .andDo(print());

        }

        @Test
        public void testAddUser() throws Exception {

            UserH2 newUser = getUser(1001L);

            given(userH2Service.add(newUser)).willReturn(true);

            // convert java to json as string
            String requestBody = new ObjectMapper().writeValueAsString(newUser);
            /// ** call the provider **
            // when -  action or the behaviour(n.พฤติกรรม) that we are going test
            RequestBuilder request = MockMvcRequestBuilders
                    .post("/b/user-h2/add")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(requestBody);

            ResultActions response = mockMvc.perform(request);

            ///  ** result follow your api response
            response.andExpect(status().isAccepted())
                    .andDo(print());

        }

        private List<UserH2> getUsers() {
            return List.of(
                    new UserH2(1000L,"alex ryder","alexryder@gmail.com"),
                    new UserH2(1001L,"alex slider","alexslider@gmail.com"),
                    new UserH2(1002L,"alex owner","alexrowner@gmail.com")
            );
        }

        private UserH2 getUser(Long id) {
            return new UserH2(id,"alex ryder","alexryder@gmail.com");
        }

    }

    @Nested
    protected class RobotH2Tests {

        @Test
        public void testRetrieveAlRobots() throws Exception {

            /// ** provide response if service calls ** it's kinda same as when(...).return(...)
            given(robotH2Service.retrieveAll()).willReturn(retrieveAlRobots());

            /// ** call the provider **
            // when -  action or the behaviour(n.พฤติกรรม) that we are going test
            RequestBuilder request = MockMvcRequestBuilders.get("/b/robot-h2");
            ResultActions response = mockMvc.perform(request);

            ///  ** result follow your api response
            response.andExpect(status().isAccepted())
                    .andExpect(content().contentType("application/json"))
                    .andExpect(jsonPath("$[0].codename").value("RX-0"))
                    .andDo(print());

        }

        private List<RobotH2> retrieveAlRobots() {
            return List.of(
              new RobotH2(1000L,"RX-0","2025-09-31",700000.0,true),
              new RobotH2(1001L,"RX-1","2025-10-31",800000.0,true),
              new RobotH2(1002L,"RX-2","2025-11-31",900000.0,true)
            );
        }
    }

    @Test
    public void testHelloWorld() throws Exception {
        /// ** provide response if service calls ** it's kinda same as when(...).return(...)
        // given - precondition or setup
        // given().willReturn("Hello World Form A Web Controller!");

        /// ** call the provider **
        // when -  action or the behaviour(n.พฤติกรรม) that we are going test
        RequestBuilder request = MockMvcRequestBuilders.get("/b/hello-world");
        // ** ResultActions class to handle the response of the REST API.
        ResultActions response = mockMvc.perform(request);

        ///  ** result follow your api response
        // then - verify the output
        response.andExpect(status().isOk())
                .andExpect(content().contentType("text/plain;charset=UTF-8"))
                .andDo(print());
    }



}
