package com.event.eagle.dao;



import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.Connection;
import java.sql.DriverManager;

public class DB {

    final static String USER = "root";
    final static String PASS = "";
    final static String url = "jdbc:mysql://localhost:3306/eagleevents";

    public static Connection getConnection() {
        Connection connection = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, USER, PASS);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return connection;
    }
}
