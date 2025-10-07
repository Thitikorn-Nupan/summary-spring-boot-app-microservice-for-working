package com.ttknp.bwebcontroller.web.controllers;

import com.ttknp.bservicewebcontroller.webannotations.CommonRestAPI;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CommonRestAPI(value = "/b1",origins = "*")
public class BController {

    @GetMapping(value = {"/","","/hello-world"})
    private @ResponseBody @ResponseStatus(HttpStatus.OK) String helloWorld() {
        return "Hello World Form B Web Controller!";
    }


}
