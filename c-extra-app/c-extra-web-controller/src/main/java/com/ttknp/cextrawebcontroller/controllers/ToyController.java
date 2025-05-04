package com.ttknp.cextrawebcontroller.controllers;



import com.ttknp.abcmodelsservice.models.mysql.ToyMYSQL;
import com.ttknp.cextraservicewebcontroller.services.ToyMYSQLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/c-extra/toy-mysql")
public class ToyController {

    private ToyMYSQLService toyMYSQLService;

    @Autowired
    public ToyController(ToyMYSQLService toyMYSQLService) {
        this.toyMYSQLService = toyMYSQLService;
    }

    @GetMapping(value = {"/",""})
    private ResponseEntity<List<ToyMYSQL>> retrieveAllToys() {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(toyMYSQLService.retrieveAll("REAL SHIT"));
    }


    @GetMapping(value = "/searchBy")
    private ResponseEntity<ToyMYSQL> retrieveToy(@RequestParam(required = false) String name,@RequestParam(required = false) Integer tid) {
        if (tid != null) {
            return ResponseEntity
                    .status(HttpStatus.ACCEPTED)
                    .body(toyMYSQLService.retrieveBy(tid));
        }
        else if (name != null) {
            return ResponseEntity
                    .status(HttpStatus.ACCEPTED)
                    .body(toyMYSQLService.retrieveBy("REAL SHIT", name));
        }
        else {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(null);
        }
    }

    @GetMapping(value = "/searchTidBy")
    private ResponseEntity<?> retrieveTidToy(@RequestParam String name) {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(toyMYSQLService.retrieveColumnBy(name));
    }

}
