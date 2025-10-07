package com.ttknp.awebcontroller.web.controllers;

import com.ttknp.abclogservice.AbcLogServiceApplication;
import com.ttknp.aservicewebcontroller.webannotations.CommonRestAPI;
import com.ttknp.awebcontroller.entity.Student;
import com.ttknp.awebcontroller.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.Set;

//@RestController
//@RequestMapping(value = "/a1")
// Note! you can test origin on postman like, Add an Origin header: to your request with the value of the origin you want to test (e.g., http://localhost:3000). This simulates a cross-origin request.
@CommonRestAPI(value = {"/a1","/A1"},origins = "*") // *** ide bug! it can't run app on ide
public class A1Controller extends AbcLogServiceApplication {

	private final StudentService studentService;

	public A1Controller() {
		super(A1Controller.class);
		studentService = new StudentService();
	}

	@GetMapping(value = {"/","","/hello-world"})
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	private String helloWorld() {
		return "Hello World Form A Web Controller!";
	}

	@GetMapping(value = "/students")
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	private Set<Student> retrieveDemoStudentsFromDataSet() {
		log.debug("Get request students");
		return studentService.getStudents();
	}

	@GetMapping(value = "/student")
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	private Student retrieveDemoStudentFromDataSet(@RequestParam(name = "id", required = true) int id) {
		log.debug("Get request student with id {}", id);
		return studentService.getStudentById(id);
	}




}
