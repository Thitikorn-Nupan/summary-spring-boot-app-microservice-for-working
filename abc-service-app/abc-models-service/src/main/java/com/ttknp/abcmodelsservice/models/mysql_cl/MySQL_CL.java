package com.ttknp.abcmodelsservice.models.mysql_cl;


public class MySQL_CL {

    public static final String H2_EXTRA_ROBOT_SELECT_ALL = "SELECT * FROM PUBLIC.ROBOTS;";
    public static final String H2_EXTRA_ROBOT_CREATE_NEW = "INSERT INTO PUBLIC.ROBOTS (RID, CODENAME, RELEASEDATE, PRICE, STATUS) VALUES (?, ?, ?, ?,?);";
    public static final String H2_EXTRA_ROBOT_DELETE = "DELETE FROM PUBLIC.ROBOTS WHERE RID = ?;";
    public static final String H2_EXTRA_ROBOT_UPDATE = "UPDATE PUBLIC.ROBOTS SET CODENAME = ?, RELEASEDATE = ?, PRICE = ?, STATUS = ? WHERE RID = ?;";

    public static final String H2_USERS_SELECT_ALL = "SELECT * FROM PUBLIC.USERS;";
    public static final String H2_USERS_CREATE_NEW = "INSERT INTO PUBLIC.USERS (USERNAME, MAIL) VALUES (?,?);";
    public static final String H2_USERS_UPDATE = "UPDATE PUBLIC.USERS SET USERNAME = ? , MAIL = ? where ID = ?;";
    public static final String H2_USERS_DELETE = "DELETE FROM PUBLIC.USERS WHERE ID = ?;";


    public static final String MYSQL_PRODUCT_SELECT_ALL = "SELECT * FROM PRODUCTS;";
    public static final String MYSQL_TOYS_SELECT_ALL = "SELECT * FROM TOYS;";
}
