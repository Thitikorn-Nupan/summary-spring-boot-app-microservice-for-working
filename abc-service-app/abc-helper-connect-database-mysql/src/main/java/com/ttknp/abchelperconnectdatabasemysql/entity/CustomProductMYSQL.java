package com.ttknp.abchelperconnectdatabasemysql.entity;

import com.ttknp.abcmodelsservice.models.mysql.ProductMYSQL;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

// @Table(name = "products") // not working
public class CustomProductMYSQL extends ProductMYSQL {

    public CustomProductMYSQL( Long id, String name, Double price, Integer quantity, String sku, Boolean active) {
        super(id, name, price, quantity, sku, active);
    }

}
