package com.quiz.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
   private static final String URL =
    "jdbc:mysql://localhost:3306/quiz_management?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";

private static final String USER = "root";
private static final String PASSWORD = "amrutham@2006";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            throw new RuntimeException("MySQL JDBC Driver not found.", e);
        }
    }

    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
