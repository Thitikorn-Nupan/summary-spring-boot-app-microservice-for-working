package com.ttknp.arunner;

import com.ttknp.awebcontroller.entity.Student;
import com.ttknp.awebcontroller.service.StudentService;
import com.ttknp.awebcontroller.web.controllers.A1Controller;
import com.ttknp.awebcontroller.web.controllers.A2Controller;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
// *** have to MockMvcResultMatchers clss
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.mockito.BDDMockito.*;

// *** Test all my module controller on module runner
// *** JUnit5 test cases for CRUD REST APIs.
// *** use the @WebMvcTest annotation to load only UserController class. (can multiple rest controller)
@WebMvcTest(controllers = {A1Controller.class, A2Controller.class})
class ARunnerApplicationTests {

    // *** using MockMvc class to make REST API calls.
    @Autowired
    private MockMvc mockMvc;

    // @MockBean // is deprecated
    @MockitoBean //
    private StudentService studentService;


    // JUnit test for reads
    @Test
    public void testHelloWorld() throws Exception {
        /// ** provide response if service calls ** it's kinda same as when(...).return(...)
        // given - precondition or setup
        // given().willReturn("Hello World Form A Web Controller!");

        /// ** call the provider **
        // when -  action or the behaviour(n.พฤติกรรม) that we are going test
        RequestBuilder request = MockMvcRequestBuilders.get("/a1/hello-world");
        // ** ResultActions class to handle the response of the REST API.
        ResultActions response = mockMvc.perform(request);

        ///  ** result follow your api response
        // then - verify the output
        response.andExpect(status().isOk())
                .andExpect(content().contentType("text/plain;charset=UTF-8"))
                .andDo(print());

    }

    @Test
    public void testretRieveDemoStudentsFromDataSet() throws Exception {
        /// ** provide response if service calls ** it's kinda same as when(...).return(...)
        // given - precondition or setup
        given(studentService.getStudents()).willReturn(getStudents());

        /// ** call the provider **
        // when -  action or the behaviour(n.พฤติกรรม) that we are going test
        RequestBuilder request = MockMvcRequestBuilders.get("/a1/students");
        // ** ResultActions class to handle the response of the REST API.
        ResultActions response = mockMvc.perform(request);

        ///  ** result follow your api response
        // then - verify the output
        response.andExpect(status().isOk())
                .andExpect( jsonPath("$[0].id", is(503230)))
                .andExpect( jsonPath("$.size()", is(3)))
                .andDo(print());

    }

    @Test
    public void testretRetrieveDemoStudentFromDataSet() throws Exception {
        /// ** provide response if service calls ** it's kinda same as when(...).return(...)
        // given - precondition or setup
        given(studentService.getStudentById(503230)).willReturn(getStudent(503230));

        /// ** call the provider **
        // when -  action or the behaviour(n.พฤติกรรม) that we are going test
        RequestBuilder request = MockMvcRequestBuilders
                .get("/a1/student")
                .param("id", String.valueOf(503230));
        // ** ResultActions class to handle the response of the REST API.
        ResultActions response = mockMvc.perform(request);

        ///  ** result follow your api response
        // then - verify the output
        response.andExpect(status().isOk())
                .andExpect( jsonPath("$.id", is(503230)))
                .andDo(print());

    }

    public Set<Student> getStudents() {
        HashSet students = new HashSet<>();
        students.add(new Student(503230, "Alex Slider", 25));
        students.add(new Student(903234, "Kevin Owner", 27));
        students.add(new Student(333204, "Adam Sandler", 21));
        return students;
    }

    public Student getStudent(Integer id) {
        return new Student(id, "Alex Slider", 25);
    }
}
