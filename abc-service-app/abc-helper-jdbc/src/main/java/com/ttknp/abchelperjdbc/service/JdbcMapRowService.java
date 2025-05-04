package com.ttknp.abchelperjdbc.service;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

public class JdbcMapRowService {
    public static Map<String, String> mapRow(ResultSet rs, int r) throws SQLException {
        Map<String, String> rowMap = new LinkedHashMap<>();
        /*
             ResultSet rs = stmt.executeQuery("SELECT a, b, c FROM TABLE2");
             ResultSetMetaData rsmd = rs.getMetaData(); // ** usefully !
             int numberOfColumns = rsmd.getColumnCount(); // ** get total columns of row
             boolean b = rsmd.isSearchable(1);
         */
        ResultSetMetaData metaData = rs.getMetaData(); // ** usefully !
        int columns = metaData.getColumnCount();  // ** get total columns of row

        for (int i = 1; i <= columns; i++) {
            String columnName = metaData.getColumnLabel(i); // ** get column name
            String columnValue = rs.getString(i); // ** get column value
            rowMap.put(columnName, columnValue);
        }
        // ex, result {ID=3, FIRSTNAME=Jacky, LASTNAME=Aldo, AGE=29, EMAIL=Jacky@aldo.com, PHONE=0913231933, DATE_CREATED=000000000001DC96} and it's very easy to loop and convert to java pojo
        return rowMap;
    }
}
