package com.kelompok5.kelompok5app.databaseAcces;

import com.kelompok5.kelompok5app.config.databaseConnection;
import com.kelompok5.kelompok5app.model.User;

import java.sql.*;

public class UserCRUD {

    private Connection conn;

    public UserCRUD() {
        conn = databaseConnection.getConnection();
    }

    public User login(String username, String password) {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new User(rs.getString("username"), rs.getString("divisi"));
            }
        } catch (SQLException e) {
            System.err.println("❌ Gagal login: " + e.getMessage());
        }

        return null;
    }
}
