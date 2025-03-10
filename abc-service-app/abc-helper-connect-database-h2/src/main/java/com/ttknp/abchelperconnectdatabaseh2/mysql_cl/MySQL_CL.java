package com.ttknp.abchelperconnectdatabaseh2.mysql_cl;

public class MySQL_CL {
    public static final String H2_USERS_SELECT_ALL = "SELECT * FROM public.users;";
    public static final String H2_USERS_CREATE_NEW = "insert into PUBLIC.USERS (USERNAME, MAIL) values (?,?);";

}
