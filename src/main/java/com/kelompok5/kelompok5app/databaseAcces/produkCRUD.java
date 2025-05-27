package com.kelompok5.kelompok5app.databaseAcces;

import com.kelompok5.kelompok5app.config.databaseConnection;
import com.kelompok5.kelompok5app.model.produk;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class produkCRUD {

    private Connection conn = databaseConnection.getConnection();

    public boolean insertProduk(produk p) {
        String query = "INSERT INTO produk (id, name, stock, kategori) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, p.id);
            stmt.setString(2, p.name);
            stmt.setInt(3, p.stock);
            stmt.setString(4, p.kategori);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Insert produk failed: " + e.getMessage());
            return false;
        }
    }

    public List<produk> getAllProduk() {
        List<produk> produkList = new ArrayList<>();
        String query = "SELECT * FROM produk";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                produk p = new produk(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("stock"),
                        rs.getString("kategori"),
                        new ArrayList<>() // materialList bisa ditambahkan lewat join jika diperlukan
                );
                produkList.add(p);
            }
        } catch (SQLException e) {
            System.err.println("Read produk failed: " + e.getMessage());
        }
        return produkList;
    }

    public boolean updateProduk(produk p) {
        String query = "UPDATE produk SET name=?, stock=?, kategori=? WHERE id=?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, p.name);
            stmt.setInt(2, p.stock);
            stmt.setString(3, p.kategori);
            stmt.setInt(4, p.id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Update produk failed: " + e.getMessage());
            return false;
        }
    }

    public boolean deleteProduk(int id) {
        String query = "DELETE FROM produk WHERE id=?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Delete produk failed: " + e.getMessage());
            return false;
        }
    }
}
