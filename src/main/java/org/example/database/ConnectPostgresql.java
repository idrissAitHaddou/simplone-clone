package org.example.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectPostgresql {
    protected  static Connection connection;
    protected static String jdbcURL = "jdbc:postgresql://localhost:5433/simploneClone";
    protected static String username = "postgres";
    protected static String password = "admin";

    private static void connect() {
        try {
            ConnectPostgresql.connection = DriverManager.getConnection(jdbcURL, username, password);
        } catch (SQLException e) {
            System.out.println("error connection !!!!");
            e.printStackTrace();
        }
    }
    public static Connection getConnection() {
        if(connection == null) {
            connect();
        }
        return connection;
    }
}