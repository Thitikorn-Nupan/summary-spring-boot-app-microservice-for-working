package com.ttknp.cwebcontroller.privatescontrollers;


import com.ttknp.abchelperconnectdatabasemysql.service.ProductMySQLService;
import com.ttknp.abcmodelsservice.models.mysql.ProductMYSQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/c/product-mysql")
public class ProductController {

    private ProductMySQLService productMySQLService;

    @Autowired
    public ProductController(ProductMySQLService productMySQLService) {
        this.productMySQLService = productMySQLService;
    }

    @GetMapping(value = {"/",""})
    private ResponseEntity<List<ProductMYSQL>> retrieveAllProducts() {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(productMySQLService.retrieveAll());
    }

    @PostMapping(value = "/truncate")
    private ResponseEntity<List<ProductMYSQL>> truncateProductsAndResetAutoincrement() {
        productMySQLService.resetProducts();
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .header("Message", "Product Reset Successful")
                .body(null);
    }

    @PutMapping(value = "/remove")
    private ResponseEntity<Boolean> removeProductByUniqKey(@RequestParam Long id) {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(productMySQLService.removeModelByAnything(id));
    }


}
