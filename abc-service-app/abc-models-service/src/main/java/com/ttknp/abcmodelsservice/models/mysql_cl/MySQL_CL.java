package com.ttknp.abcmodelsservice.models.mysql_cl;


public class MySQL_CL {
    public static final String H2_USERS_SELECT_ALL = "SELECT * FROM public.users;";
    public static final String H2_EXTRA_ROBOT_SELECT_ALL = "SELECT * FROM public.robots;";
    public static final String MYSQL_PRODUCT_SELECT_ALL = "SELECT * FROM products;";
    public static final String MYSQL_TOYS_SELECT_ALL = "SELECT * FROM toys;";
    public static final String H2_USERS_CREATE_NEW = "insert into PUBLIC.USERS (USERNAME, MAIL) values (?,?);";
}
