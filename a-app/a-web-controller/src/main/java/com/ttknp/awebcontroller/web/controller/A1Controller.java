package com.ttknp.awebcontroller.web.controller;

import com.ttknp.aservicewebcontroller.webannotations.CommonRestAPI;
import com.ttknp.awebcontroller.entity.Student;
import com.ttknp.awebcontroller.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@CommonRestAPI(value = "/a1") // ***
public class A1Controller {

	private final StudentService studentService;

	public A1Controller() {
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
		return studentService.getStudents();
	}


	@GetMapping(value = "/student")
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	private Student retrieveDemoStudentFromDataSet(@RequestParam(name = "id", required = true) int id) {
		return studentService.getStudentById(id);
	}




}
