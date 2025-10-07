package com.ttknp.cextrawebcontroller.controllers;

import com.ttknp.abcmodelsservice.models.mysql.DetailMYSQL;
import com.ttknp.cextraservicewebcontroller.services.DetailMYSQLService;
import com.ttknp.cextraservicewebcontroller.webannotations.CommonRestAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@CommonRestAPI(value = "/c-extra/detail-mysql")
public class DetailController {

    private final DetailMYSQLService detailMYSQLService;

    @Autowired
    public DetailController(DetailMYSQLService detailMYSQLService) {
        this.detailMYSQLService = detailMYSQLService;
    }

    @GetMapping(value = {"/",""})
    private ResponseEntity<List<DetailMYSQL>> retrieveAllDetails() {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(detailMYSQLService.retrieveAll());
    }

    @GetMapping(value = "/searchBy")
    private ResponseEntity<DetailMYSQL> retrieveDetail(@RequestParam String code) {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(detailMYSQLService.retrieveBy(code));
    }


    @GetMapping(value = "/searchCodeBy")
    private ResponseEntity<String> retrieveCodeDetail(@RequestParam Long did) {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(detailMYSQLService.retrieveColumnBy(did));
    }


}
