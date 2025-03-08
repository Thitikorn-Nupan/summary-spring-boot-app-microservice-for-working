package com.ttknp.awebcontroller.web.controller;

import com.ttknp.aservicewebcontroller.exception.ContentNotAllowed;
import com.ttknp.aservicewebcontroller.webannotations.CommonRestAPI;
import com.ttknp.awebcontroller.entity.Student;
import com.ttknp.awebcontroller.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Set;

@CommonRestAPI(value = "/a2")
public class A2Controller {

	private final StudentService studentService;

	public A2Controller() {
		studentService = new StudentService();
	}


	@GetMapping(value = "/student")
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	private Student retrieveDemoStudentFromDataSet(@RequestParam(name = "id", required = true) int id) throws Exception {
		if (id <= 0) {
			// my handle exception is on "a-service-web-controller"
			throw new ContentNotAllowed("Bad requested param");
		}
		return studentService.getStudentById(id);
	}




}
