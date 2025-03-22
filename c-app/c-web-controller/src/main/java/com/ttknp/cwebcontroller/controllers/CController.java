package com.ttknp.cwebcontroller.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/c")
//@CommonRestAPI(value = "/b")
public class CController {

    @GetMapping(value = {"/","","/hello-world"})
    private @ResponseBody @ResponseStatus(HttpStatus.OK) String helloWorld() {
        return "Hello World Form C Web Controller!";
    }


}
