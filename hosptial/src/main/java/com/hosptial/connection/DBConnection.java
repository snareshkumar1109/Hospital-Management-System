package com.hosptial.connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:8082/hosptial"; 
    private static final String USER = "root";
    private static final String PASSWORD = "12345"; 

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver"); // Load MySQL JDBC Driver
        return DriverManager.getConnection(URL, USER, PASSWORD); // Establish connection
    }
}