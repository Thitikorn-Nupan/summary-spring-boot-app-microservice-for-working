package com.ttknp.abchelperconnectdatabasemysql.service;

import com.ttknp.abcmodelsservice.models.mysql.ProductMYSQL;
import com.ttknp.abcmodelsservice.models.mysql_cl.MySQL_CL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ProductMySQLService {

    private JdbcTemplate jdbcTemplate;
    private List<ProductMYSQL> products;
    private Logger log;
    private String sqlScriptDirOnAbs = "B:/practice-java-one-jetbrains/spring-boot-skills/lab_core_36/sumary-spring-boot-career/abc-parent/abc-properties-service/src/main/resources/sql/";

    /*
    // not working
    @Autowired
    private ProductRepository productRepository;
    */

    @Autowired
    public ProductMySQLService(@Qualifier("dataSourceMySQL") DataSource dataSource ) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.products = new ArrayList<>();
        this.log = LoggerFactory.getLogger(ProductMySQLService.class);

    }


    public void resetProducts() {
        // ** way to query with script sql ** if you want queries response don't do the way
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        // populator.addScripts(new ClassPathResource("fetching-all-products-mysql.sql")); // ClassPathResource class it looks to src of this module
        populator.addScripts(new FileSystemResource(sqlScriptDirOnAbs +"reset-products-mysql.sql")); // FileSystemResource it looks to abs path
        populator.execute(Objects.requireNonNull(this.jdbcTemplate.getDataSource())); // by default it'll log queries result on console
    }


    public List<ProductMYSQL> getProducts() {
        products = jdbcTemplate.query(MySQL_CL.MYSQL_PRODUCT_SELECT_ALL, (rs, rowNum) -> {
            ProductMYSQL productMYSQL = new ProductMYSQL(
                    rs.getLong("id"),
                    rs.getString("name"),
                    rs.getDouble("price"),
                    rs.getInt("quantity"),
                    rs.getString("sku"),
                    rs.getBoolean("active")
            );
            return productMYSQL;
        });
        return products;
    }



}
