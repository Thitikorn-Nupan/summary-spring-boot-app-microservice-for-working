package com.ttknp.abchelperconnectdatabasemysql.service;

import com.ttknp.abchelperconnectdatabasemysql.service.common.ModelService;
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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Service
public class ProductMySQLService extends ModelService<ProductMYSQL> {

    private JdbcTemplate jdbcTemplate;
    private List<ProductMYSQL> products;
    private Logger log;

    /**
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

    /*
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
    }*/


    public void resetProducts() {
        loadScriptAbsPath("reset-products-mysql.sql",jdbcTemplate.getDataSource());
    }

    @Override
    public List<ProductMYSQL> retrieveAll() {
        products.clear();
        try {
            HashMap<String,String> params = new HashMap<>();
            params.put("{NAME}","'Product 0001'");
            params.put("{PRICE}","0.0");
            params.put("{QUANTITY}","0");
            params.put("{SKU}","'PD-0001'");
            params.put("{ACTIVE}","0");
            // jdbcTemplateMySQL.execute("truncate table <>;"); // only truncate table
            loadScriptAbsPath("truncate-products-mysql.sql", jdbcTemplate.getDataSource()); // query by file truncate table and clear auto increment
            loadScriptAbsPath("reset-products-mysql-params.sql",jdbcTemplate,params); // query by statement
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        products = jdbcTemplate.query(MySQL_CL.MYSQL_PRODUCT_SELECT_ALL,this);
        return products;
    }

    @Override
    public Boolean add(ProductMYSQL productMYSQL) {
        return null;
    }

    @Override
    public Boolean edit(ProductMYSQL productMYSQL, Long pk) {
        return null;
    }

    @Override
    public Boolean remove(Long pk) {
        return null;
    }

    @Override
    public <U> Boolean removeModelByAnything(U uniqueKey) {
        log.info("Removing model with id  {}", uniqueKey);
        // just for testing it's bad logic
        HashMap<String,String> params = new HashMap<>();
        params.put("[ACTIVE]","null");
        params.put("{ID}",uniqueKey.toString());
        try {
            loadScriptAbsPath("products-mysql-backup.sql",jdbcTemplate,params);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        int rowAffected = jdbcTemplate.update(MySQL_CL.MYSQL_PRODUCT_DELETE, uniqueKey); // async method
        if (rowAffected > 0) {
            log.debug("Successfully removed model with id {}", uniqueKey);
            return true;
        } else {
            log.debug("Failed to remove model with id {}", uniqueKey);
            return false;
        }
    }

    @Override
    public <U> void loadScriptAbsPath(String fileName) {}

    @Override
    public ProductMYSQL mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new ProductMYSQL(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getDouble("price"),
                rs.getInt("quantity"),
                rs.getString("sku"),
                rs.getBoolean("active")
        );
    }
}
