package com.kelompok5.kelompok5app.databaseAcces;

import com.kelompok5.kelompok5app.config.databaseConnection;
import com.kelompok5.kelompok5app.model.material;
import com.kelompok5.kelompok5app.model.materialProduk;
import com.kelompok5.kelompok5app.model.produk;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class produkCRUD {
    private Connection conn = databaseConnection.getConnection();

    // CREATE
    public void tambahProduk(produk p) {
        String sql = "INSERT INTO produk (id, name, stock, kategori) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, p.id);
            stmt.setString(2, p.name);
            stmt.setInt(3, p.stock);
            stmt.setString(4, p.kategori);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // READ
    public List<produk> getAllProduk() {
        List<produk> list = new ArrayList<>();
        String sql = "SELECT * FROM produk";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int idProduk = rs.getInt("id");

                // Ambil list material yang digunakan
                List<materialProduk> materialList = getMaterialForProduk(idProduk);

                produk p = new produk(
                        idProduk,
                        rs.getString("name"),
                        rs.getInt("stock"),
                        rs.getString("kategori"),
                        materialList
                );
                list.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    private List<materialProduk> getMaterialForProduk(int produkId) {
        List<materialProduk> list = new ArrayList<>();
        String sql = "SELECT mp.id, mp.id_produk, mp.jumlah, m.* " +
                     "FROM material_produk mp JOIN material m ON mp.id_material = m.id " +
                     "WHERE mp.id_produk = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, produkId);
            ResultSet rs = stmt.executeQuery();
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
    public void updateProduk(produk p) {
        String sql = "UPDATE produk SET name = ?, stock = ?, kategori = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, p.name);
            stmt.setInt(2, p.stock);
            stmt.setString(3, p.kategori);
            stmt.setInt(4, p.id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // DELETE
    public void deleteProduk(int id) {
        String sql = "DELETE FROM produk WHERE id=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
