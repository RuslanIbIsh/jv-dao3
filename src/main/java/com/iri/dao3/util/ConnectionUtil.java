package com.iri.dao3.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {
    private static final String URL = "jdbc:mysql://localhost:3306/dao3_db?serverTimezone=UTC";

    public static Connection getConnection() {
        Properties dbProperties = new Properties();
        dbProperties.put("user", "root");
        dbProperties.put("password", "root");

        try {
            Connection connection = DriverManager.getConnection(URL, dbProperties);
            return connection;
        } catch (SQLException throwables) {
            throw new RuntimeException("Can't create connection", throwables);
        }
    }
}
