package com.kelompok5.kelompok5app.databaseAcces;

import com.kelompok5.kelompok5app.config.databaseConnection;
import com.kelompok5.kelompok5app.model.material;
import com.kelompok5.kelompok5app.model.materialProduk;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class materialProdukCRUD {
    private Connection conn = databaseConnection.getConnection();

    // CREATE
    public void tambahMaterialProduk(materialProduk mp) {
        String sql = "INSERT INTO material_produk (id, id_produk, id_material, jumlah) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, mp.id);
            stmt.setString(2, mp.idProduk);
            stmt.setString(3, mp.material.id);
            stmt.setInt(4, mp.jumlah);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // READ
    public List<materialProduk> getAllMaterialProduk() {
        List<materialProduk> list = new ArrayList<>();
        String sql = "SELECT mp.id, mp.id_produk, m.id AS id_material, m.name, m.kategori, m.min_stock, m.max_stock, m.stock, m.vendor, m.harga, mp.jumlah " +
                     "FROM material_produk mp " +
                     "JOIN material m ON mp.id_material = m.id";

        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                material m = new material(
                        rs.getString("id_material"),
                        rs.getString("name"),
                        rs.getString("kategori"),
                        rs.getInt("min_stock"),
                        rs.getInt("max_stock"),
                        rs.getInt("stock"),
                        rs.getString("vendor"),
                        rs.getDouble("harga")
                );

                materialProduk mp = new materialProduk(
                        rs.getInt("id"),
                        rs.getString("id_produk"),
                        m,
                        rs.getInt("jumlah")
                );

                list.add(mp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // UPDATE
    public void updateMaterialProduk(materialProduk mp) {
        String sql = "UPDATE material_produk SET id_produk = ?, id_material = ?, jumlah = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, mp.idProduk);
            stmt.setString(2, mp.material.id);
            stmt.setInt(3, mp.jumlah);
            stmt.setInt(4, mp.id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // DELETE
    public void deleteMaterialProduk(int id) {
        String sql = "DELETE FROM material_produk WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
