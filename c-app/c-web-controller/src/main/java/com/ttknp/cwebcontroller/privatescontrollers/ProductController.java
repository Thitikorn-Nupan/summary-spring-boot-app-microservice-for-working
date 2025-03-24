package com.ttknp.cwebcontroller.privatescontrollers;


import com.ttknp.abchelperconnectdatabasemysql.service.ProductMySQLService;
import com.ttknp.abcmodelsservice.models.mysql.ProductMYSQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
                .body(productMySQLService.getProducts());
    }

    @PostMapping(value = "/truncate")
    private ResponseEntity<List<ProductMYSQL>> truncateProductsAndResetAutoincrement() {
        productMySQLService.resetProducts();
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .header("Message", "Product Reset Successful")
                .body(null);
    }


}
