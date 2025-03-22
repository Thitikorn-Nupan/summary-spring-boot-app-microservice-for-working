package com.ttknp.abchelperconnectdatabasemysql.service;

import com.ttknp.abchelperconnectdatabasemysql.entity.CustomProductMYSQL;
import com.ttknp.abcmodelsservice.models.mysql_cl.MySQL_CL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductMySQLService {

    private JdbcTemplate jdbcTemplate;
    private List<CustomProductMYSQL> products;
    private Logger log;
    /* // not working
    @Autowired
    private ProductRepository productRepository;
    */


    @Autowired
    public ProductMySQLService(@Qualifier("dataSourceMySQL") DataSource dataSource ) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.products = new ArrayList<>();
        this.log = LoggerFactory.getLogger(ProductMySQLService.class);

    }

    /*
    // not working
    public List<CustomProductMYSQL> getProductsByRepositoryAnnotation() {
        return (List<CustomProductMYSQL>) productRepository.findAll();
    }
    */


    public List<CustomProductMYSQL> getProducts() {
        products = jdbcTemplate.query(MySQL_CL.MYSQL_PRODUCT_SELECT_ALL, (rs, rowNum) -> {
            CustomProductMYSQL customProductMYSQL = new CustomProductMYSQL(
                    rs.getLong("id"),
                    rs.getString("name"),
                    rs.getDouble("price"),
                    rs.getInt("quantity"),
                    rs.getString("sku"),
                    rs.getBoolean("active")
            );
            return customProductMYSQL;
        });
        return products;
    }



}
