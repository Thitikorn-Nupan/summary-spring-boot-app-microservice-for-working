package com.ttknp.bwebcontroller.web.privatescontrollers;

import com.ttknp.abchelperconnectdatabaseh2.services.RobotH2Service;
import com.ttknp.abchelperconnectdatabaseh2.services.UserH2Service;
import com.ttknp.abcmodelsservice.models.h2.RobotH2;
import com.ttknp.abcmodelsservice.models.h2.UserH2;
import com.ttknp.bservicewebcontroller.webannotations.CommonRestAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@CommonRestAPI(value = "/b/robot-h2")
public class RobotH2Controller {


    private RobotH2Service robotH2Service;

    @Autowired
    public RobotH2Controller(RobotH2Service robotH2Service) {
        this.robotH2Service = robotH2Service;
    }

    @GetMapping(value = {"/",""})
    private ResponseEntity<List<RobotH2>> retrieveAllRobots() {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(robotH2Service.retrieveAllRobots());
    }


}
