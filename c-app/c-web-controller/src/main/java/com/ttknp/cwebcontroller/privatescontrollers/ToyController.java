package com.ttknp.cwebcontroller.privatescontrollers;


import com.ttknp.abchelperconnectdatabasemysql.service.ToyMYSQLService;
import com.ttknp.abcmodelsservice.models.mysql.ToyMYSQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

// Use An Bean as dataSourceMySQLExtra
@RestController
@RequestMapping(value = "/c1/toy-mysql")
@CrossOrigin(origins = "*")
public class ToyController {

    private final ToyMYSQLService toyMYSQLService;

    @Autowired
    public ToyController(ToyMYSQLService toyMYSQLService) {
        this.toyMYSQLService = toyMYSQLService;
    }

    @GetMapping(value = {"/",""})
    private ResponseEntity<List<ToyMYSQL>> retrieveAllToys() {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(toyMYSQLService.retrieveAll());
    }

    /*@PostMapping(value = "/truncate")
    private ResponseEntity<List<ProductMYSQL>> truncateProductsAndResetAutoincrement() {
        productMySQLService.resetProducts();
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .header("Message", "Product Reset Successful")
                .body(null);
    }*/


}
