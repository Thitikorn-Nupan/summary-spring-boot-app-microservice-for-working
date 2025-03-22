package com.ttknp.cwebcontroller.privatescontrollers;

import com.ttknp.abchelperconnectdatabasemysql.entity.CustomProductMYSQL;
import com.ttknp.abchelperconnectdatabasemysql.service.ProductMySQLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
    private ResponseEntity<List<CustomProductMYSQL>> retrieveAllProducts() {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(productMySQLService.getProducts());
    }
}
