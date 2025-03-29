package com.ttknp.bwebcontroller.web.privatescontrollers;

import com.ttknp.abchelperconnectdatabaseh2.services.UserH2Service;
import com.ttknp.abcmodelsservice.models.h2.UserH2;
import com.ttknp.bservicewebcontroller.exception.ContentNotAllowed;
import com.ttknp.bservicewebcontroller.webannotations.CommonRestAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// sometime ide fucking เอ๋อ
// @RestController
// @RequestMapping(value = "/b/user-h2")
@CommonRestAPI(value = "/b/user-h2")
public class UserH2Controller {


    private UserH2Service userH2Service;

    @Autowired
    public UserH2Controller(UserH2Service userH2Service) {
        this.userH2Service = userH2Service;
    }

    /**
    // just don't like this way
    @GetMapping(value = {"/", ""})
    private @ResponseBody @ResponseStatus(HttpStatus.ACCEPTED) List<UserH2> retrieveAllUsers() {
        return userH2Service.retrieveAllUsers();
    }
    */

    @GetMapping(value = {"/",""})
    private ResponseEntity<List<UserH2>> retrieveAllUsers() {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(userH2Service.retrieveAll());
    }

    // uri look like /searchBy?id=1&username=alex%20ryder
    @GetMapping(value ="/searchBy",params = {"id","username"}) // params = {"id","username"}  optional
    private ResponseEntity<String> retrieveUser(@RequestParam(required = false) Long id,@RequestParam(required = false) String username) {
        if (id == 0 && username.equals("T")) {
            throw new ContentNotAllowed("Test Exception Handler");
        } else {
            return ResponseEntity
                    .status(HttpStatus.ACCEPTED)
                    .body("Found");
        }
    }

    @PostMapping(value = "/add")
    private ResponseEntity<Boolean> addUser(@RequestBody UserH2 user) {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(userH2Service.add(user));
    }







}
