package com.kelompok5.kelompok5app.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class databaseConnection {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/kelompok5app?useSSL=false&serverTimezone=Asia/Jakarta";
    private static final String DB_USER = "root"; // ganti jika kamu pakai user lain
    private static final String DB_PASSWORD = ""; // ganti jika root kamu pakai password

    private static Connection connection;

    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                System.out.println("✅ Koneksi ke database berhasil.");
            }
        } catch (ClassNotFoundException e) {
            System.err.println("❌ Driver JDBC tidak ditemukan: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("❌ Gagal koneksi ke database: " + e.getMessage());
        }
        return connection;
    }

    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("🔌 Koneksi database ditutup.");
            }
        } catch (SQLException e) {
            System.err.println("❌ Gagal menutup koneksi: " + e.getMessage());
        }
    }
}
