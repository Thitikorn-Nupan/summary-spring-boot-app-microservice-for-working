package com.ttknp.abcmodelsservice.models.mysql_cl;


public class MySQL_CL {

    public static final String H2_EXTRA_ROBOT_SELECT_ALL = "SELECT * FROM PUBLIC.ROBOTS;";
    public static final String H2_EXTRA_ROBOT_SELECT_ALL_AND_SORT = "SELECT * FROM PUBLIC.ROBOTS ORDER BY %s %s;";
    public static final String H2_EXTRA_ROBOT_CREATE_NEW = "INSERT INTO PUBLIC.ROBOTS (RID, CODENAME, RELEASEDATE, PRICE, STATUS) VALUES (?, ?, ?, ?,?);";
    public static final String H2_EXTRA_ROBOT_DELETE = "DELETE FROM PUBLIC.ROBOTS WHERE RID = ?;";
    public static final String H2_EXTRA_ROBOT_UPDATE = "UPDATE PUBLIC.ROBOTS SET CODENAME = ?, RELEASEDATE = ?, PRICE = ?, STATUS = ? WHERE RID = ?;";

    public static final String H2_USERS_SELECT_ALL = "SELECT * FROM PUBLIC.USERS;";
    // the ? operator, it only values can be bound as parameters. Not parts of the query itself. so i got way by using String class pass value
    public static final String H2_USERS_SELECT_ALL_AND_SORT = "SELECT * FROM PUBLIC.USERS ORDER BY %s %s;";
    public static final String H2_USERS_CREATE_NEW = "INSERT INTO PUBLIC.USERS (USERNAME, MAIL) VALUES (?,?);";
    public static final String H2_USERS_UPDATE = "UPDATE PUBLIC.USERS SET USERNAME = ? , MAIL = ? where ID = ?;";
    public static final String H2_USERS_DELETE = "DELETE FROM PUBLIC.USERS WHERE ID = ?;";


    public static final String MYSQL_PRODUCT_SELECT_ALL = "SELECT * FROM PRODUCTS;";
    public static final String MYSQL_PRODUCT_DELETE = "DELETE FROM PRODUCTS WHERE ID = ?;";
    public static final String MYSQL_TOYS_SELECT_ALL = "SELECT * FROM TOYS;";
    public static final String MYSQL_TOYS_SELECT_BY_TID = "SELECT * FROM TOYS WHERE TID = ?;";
    public static final String MYSQL_TOYS_SELECT_TID_BY_NAME = "SELECT TID FROM TOYS WHERE NAME = ?;";
    public static final String MYSQL_TOYS_SELECT_BY_NAME = "SELECT * FROM TOYS WHERE NAME = ?;";
    public static final String MYSQL_DETAIL_SELECT_ALL = "SELECT * FROM DETAILS;";
    public static final String MYSQL_DETAIL_SELECT_BY_CODE = "SELECT * FROM DETAILS WHERE CODE = ?;";
    public static final String MYSQL_DETAIL_SELECT_CODE_BY_DID = "SELECT CODE FROM DETAILS WHERE DID = ?;";

}
