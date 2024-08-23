package com.company;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database_Connection {
    private static final Logger logger = LoggerFactory.getLogger(Database_Connection.class);
    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                String url = "jdbc:mysql://localhost:3306/ducat2_30pm";
                String username = "root";
                String password = "Harshit@1936";
                Class.forName("com.mysql.cj.jdbc.Driver"); // Explicitly load the driver
                connection = DriverManager.getConnection(url, username, password);
                logger.info("Database connection established successfully.");
            } catch (ClassNotFoundException e) {
                logger.error("MySQL JDBC Driver not found: ", e);
            } catch (SQLException e) {
                logger.error("Database connection failed: ", e);
            }
        }
        return connection;
    }
}