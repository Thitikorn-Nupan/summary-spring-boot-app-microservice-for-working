package com.ttknp.bwebcontroller.web.controllers;

import com.ttknp.abchelperconnectdatabaseh2.services.UserH2Service;
import com.ttknp.bservicewebcontroller.webannotations.CommonRestAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

//@RestController
//@RequestMapping(value = "/b")
@CommonRestAPI(value = "/b")
public class BController {

    @GetMapping(value = {"/","","/hello-world"})
    private @ResponseBody @ResponseStatus(HttpStatus.OK) String helloWorld() {
        return "Hello World Form B Web Controller!";
    }


}
