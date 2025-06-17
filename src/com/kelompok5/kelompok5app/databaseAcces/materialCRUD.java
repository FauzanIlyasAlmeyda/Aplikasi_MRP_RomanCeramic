package com.kelompok5.kelompok5app.databaseAcces;

import com.kelompok5.kelompok5app.config.databaseConnection;
import com.kelompok5.kelompok5app.model.Material;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MaterialCRUD {
    private Connection conn = databaseConnection.getConnection();

    public void tambahMaterial(Material m) {
        String sql = "INSERT INTO barang (id, nama, kategori, min_stock, max_stock, stock, `order`, vendor, harga, updated_at) " +
                     "VALUES (?, ?, 'material', ?, ?, ?, ?, ?, ?, NOW())";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, m.getId());
            stmt.setString(2, m.getName());
            stmt.setInt(3, m.getMin_stock());
            stmt.setInt(4, m.getMax_stock());
            stmt.setInt(5, m.getStock());
            stmt.setInt(6, m.getOrder());
            stmt.setString(7, m.getVendor());
            stmt.setDouble(8, m.getHarga());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Material> getAllMaterial() {
        List<Material> list = new ArrayList<>();
        String sql = "SELECT * FROM barang WHERE kategori = 'material'";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Material m = new Material(
                        rs.getString("id"),
                        rs.getString("nama"),
                        rs.getString("kategori"),
                        rs.getInt("min_stock"),
                        rs.getInt("max_stock"),
                        rs.getInt("stock"),
                        rs.getInt("order"),
                        rs.getString("vendor"),
                        rs.getDouble("harga"),
                        rs.getString("updated_at")
                );
                list.add(m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void updateMaterial(Material m) {
        String sql = "UPDATE barang SET nama=?, min_stock=?, max_stock=?, stock=?, `order`=?, vendor=?, harga=?, updated_at=NOW() " +
                     "WHERE id=? AND kategori='material'";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, m.getName());
            stmt.setInt(2, m.getMin_stock());
            stmt.setInt(3, m.getMax_stock());
            stmt.setInt(4, m.getStock());
            stmt.setInt(5, m.getOrder());
            stmt.setString(6, m.getVendor());
            stmt.setDouble(7, m.getHarga());
            stmt.setString(8, m.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteMaterial(String id) {
        String sql = "DELETE FROM barang WHERE id=? AND kategori='material'";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean deleteByProdukId(String idProduk) {
    String sql = "DELETE FROM produk_material WHERE id_produk = ?";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, idProduk);
        return stmt.executeUpdate() > 0;
    } catch (SQLException e) {
        System.err.println("Gagal hapus BOM produk: " + e.getMessage());
        return false;
    }
}
}
