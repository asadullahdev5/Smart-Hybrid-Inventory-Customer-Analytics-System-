package com.smartstore.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class SQLConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/smart_store";
    private static final String USER = "root";
    private static final String PASSWORD = "asadiu09-0325"; // Apna password dalein

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            System.out.println("SQL Connection Failed: " + e.getMessage());
            return null;
        }
    }
}