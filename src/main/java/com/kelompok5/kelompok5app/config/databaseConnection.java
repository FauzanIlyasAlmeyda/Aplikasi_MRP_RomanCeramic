package com.kelompok5.kelompok5app.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class databaseConnection {

    private static final String DB_URL = "jdbc:mysql://srv1154.hstgr.io:3306/u251077349_romanCeramick5";
    private static final String DB_USER = "u251077349_romanCeramick5";
    private static final String DB_PASSWORD = "Romanceramickelompok5";

    private static Connection connection;

    
    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            } catch (SQLException e) {
                System.err.println("Gagal koneksi ke database: " + e.getMessage());
            }
        }
        return connection;
    }

    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            System.err.println("Gagal menutup koneksi: " + e.getMessage());
        }
    }
}
