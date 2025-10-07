package com.ttknp.cwebcontroller.privatescontrollers;

import com.ttknp.abchelperconnectdatabasemysql.service.ProductMySQLService;
import com.ttknp.abcmodelsservice.models.mysql.ProductMYSQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

// Use An Bean as dataSourceMySQL
@RestController
@RequestMapping(value = "/c1/product-mysql")
@CrossOrigin(origins = "*")
public class ProductController {

    private final ProductMySQLService productMySQLService;

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
    private ResponseEntity<List<ProductMYSQL>> truncateProductsAndResetAutoincrement() throws IOException {
        // productMySQLService.resetProducts();
        // productMySQLService.resetProductsAndNoParams();
        productMySQLService.resetProductsAndPassParams();
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
