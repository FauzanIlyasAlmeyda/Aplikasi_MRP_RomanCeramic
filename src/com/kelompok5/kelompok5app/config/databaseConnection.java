package com.kelompok5.kelompok5app.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class databaseConnection {

    private static final String DB_URL = "jdbc:mysql://srv1154.hstgr.io:3306/u251077349_romanCeramick5?autoReconnect=true&useSSL=false&zeroDateTimeBehavior=CONVERT_TO_NULL";
    private static final String DB_USER = "u251077349_romanCeramick5";
    private static final String DB_PASSWORD = "Mrpkelompok5";

    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            } catch (ClassNotFoundException e) {
                System.err.println("Driver JDBC tidak ditemukan: " + e.getMessage());
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
