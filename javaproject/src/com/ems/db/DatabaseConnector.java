package com.ems.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnector {
    public static Connection getConnection() throws Exception {
        String url = "jdbc:mysql://localhost:3306/event_management_system";
        String user = "root"; // your MySQL username
        String password = "Durga@31326"; // your MySQL password
        return DriverManager.getConnection(url, user, password);
    }
}
