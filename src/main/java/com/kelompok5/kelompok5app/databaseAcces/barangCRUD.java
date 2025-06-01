package com.kelompok5.kelompok5app.databaseAcces;

import com.kelompok5.kelompok5app.config.databaseConnection;
import com.kelompok5.kelompok5app.model.barang;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class barangCRUD {
    private Connection conn = databaseConnection.getConnection();

    public void tambahBarang(barang b) {
        String sql = "INSERT INTO barang (id, nama, kategori, min_stock, max_stock, stock, vendor, harga) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, b.id);
            stmt.setString(2, b.nama);
            stmt.setString(3, b.kategori);
            stmt.setInt(4, b.min_stock);
            stmt.setInt(5, b.max_stock);
            stmt.setInt(6, b.stock);
            stmt.setString(7, b.vendor);
            stmt.setDouble(8, b.harga);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<barang> getAllBarang() {
        List<barang> list = new ArrayList<>();
        String sql = "SELECT * FROM barang";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                barang b = new barang(
                        rs.getInt("id"),
                        rs.getString("nama"),
                        rs.getString("kategori"),
                        rs.getInt("min_stock"),
                        rs.getInt("max_stock"),
                        rs.getInt("stock"),
                        rs.getString("vendor"),
                        rs.getDouble("harga")
                );
                list.add(b);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}

package com.kelompok5.kelompok5app.databaseAcces;

import com.kelompok5.kelompok5app.config.databaseConnection;
import com.kelompok5.kelompok5app.model.barang;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class barangCRUD {
    private Connection conn = databaseConnection.getConnection();

    // CREATE
    public void tambahBarang(barang b) {
        String sql = "INSERT INTO barang (id, nama, kategori, min_stock, max_stock, stock, vendor, harga) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, b.id);
            stmt.setString(2, b.nama);
            stmt.setString(3, b.kategori);
            stmt.setInt(4, b.min_stock);
            stmt.setInt(5, b.max_stock);
            stmt.setInt(6, b.stock);
            stmt.setString(7, b.vendor);
            stmt.setDouble(8, b.harga);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // READ
    public List<barang> getAllBarang() {
        List<barang> list = new ArrayList<>();
        String sql = "SELECT * FROM barang";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                barang b = new barang(
                        rs.getInt("id"),
                        rs.getString("nama"),
                        rs.getString("kategori"),
                        rs.getInt("min_stock"),
                        rs.getInt("max_stock"),
                        rs.getInt("stock"),
                        rs.getString("vendor"),
                        rs.getDouble("harga")
                );
                list.add(b);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // UPDATE
    public void updateBarang(barang b) {
        String sql = "UPDATE barang SET nama = ?, kategori = ?, min_stock = ?, max_stock = ?, stock = ?, vendor = ?, harga = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, b.nama);
            stmt.setString(2, b.kategori);
            stmt.setInt(3, b.min_stock);
            stmt.setInt(4, b.max_stock);
            stmt.setInt(5, b.stock);
            stmt.setString(6, b.vendor);
            stmt.setDouble(7, b.harga);
            stmt.setInt(8, b.id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // DELETE
    public void deleteBarang(int id) {
        String sql = "DELETE FROM barang WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
