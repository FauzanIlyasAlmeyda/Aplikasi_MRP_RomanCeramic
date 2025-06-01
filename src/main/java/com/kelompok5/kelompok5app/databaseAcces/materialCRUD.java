package com.kelompok5.kelompok5app.databaseAcces;

import com.kelompok5.kelompok5app.config.databaseConnection;
import com.kelompok5.kelompok5app.model.material;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class materialCRUD {
    private Connection conn = databaseConnection.getConnection();

    // CREATE
    public void tambahMaterial(material m) {
        String sql = "INSERT INTO material (id, name, kategori, min_stock, max_stock, stock, vendor, harga) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, m.id);
            stmt.setString(2, m.name);
            stmt.setString(3, m.kategori);
            stmt.setInt(4, m.min_stock);
            stmt.setInt(5, m.max_stock);
            stmt.setInt(6, m.stock);
            stmt.setString(7, m.vendor);
            stmt.setDouble(8, m.harga);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // READ
    public List<material> getAllMaterial() {
        List<material> list = new ArrayList<>();
        String sql = "SELECT * FROM material";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                material m = new material(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("kategori"),
                        rs.getInt("min_stock"),
                        rs.getInt("max_stock"),
                        rs.getInt("stock"),
                        rs.getString("vendor"),
                        rs.getDouble("harga")
                );
                list.add(m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // UPDATE
    public void updateMaterial(material m) {
        String sql = "UPDATE material SET name=?, kategori=?, min_stock=?, max_stock=?, stock=?, vendor=?, harga=? WHERE id=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, m.name);
            stmt.setString(2, m.kategori);
            stmt.setInt(3, m.min_stock);
            stmt.setInt(4, m.max_stock);
            stmt.setInt(5, m.stock);
            stmt.setString(6, m.vendor);
            stmt.setDouble(7, m.harga);
            stmt.setString(8, m.id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // DELETE
    public void deleteMaterial(String id) {
        String sql = "DELETE FROM material WHERE id=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
