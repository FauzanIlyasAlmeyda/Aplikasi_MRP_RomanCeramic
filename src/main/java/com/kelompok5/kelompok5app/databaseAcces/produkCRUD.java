package com.kelompok5.kelompok5app.databaseAcces;

import com.kelompok5.kelompok5app.config.databaseConnection;
import com.kelompok5.kelompok5app.model.Produk;
import com.kelompok5.kelompok5app.model.Materialproduk;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdukCRUD {
    private Connection conn;

    public ProdukCRUD() {
        conn = databaseConnection.getConnection();
    }

    // ✅ Generate ID otomatis (PRD001, PRD002, ...)
    public String generateId() {
        String prefix = "PRD";
        String sql = "SELECT id FROM produk ORDER BY id DESC LIMIT 1";
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                String lastId = rs.getString("id");
                int number = Integer.parseInt(lastId.replace(prefix, ""));
                return prefix + String.format("%03d", number + 1);
            }
        } catch (SQLException e) {
            System.err.println("Gagal generate ID produk: " + e.getMessage());
        }
        return prefix + "001";
    }

    // ✅ Insert produk (tanpa materialList dulu)
    public boolean insert(Produk p) {
        String sql = "INSERT INTO produk (id, nama, kategori, stock) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, p.getId());
            stmt.setString(2, p.getName());
            stmt.setString(3, p.getKategori());
            stmt.setInt(4, p.getStock());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Gagal insert produk: " + e.getMessage());
            return false;
        }
    }

    public List<Produk> getAll() {
        List<Produk> list = new ArrayList<>();
        String sql = "SELECT * FROM produk";
        MaterialprodukCRUD mpDao = new MaterialprodukCRUD(); 

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String idProduk = rs.getString("id");
                List<Materialproduk> materialList = mpDao.getByProdukId(idProduk);

                Produk p = new Produk(
                        idProduk,
                        rs.getString("nama"),
                        rs.getInt("stock"),
                        rs.getString("kategori"),
                        materialList
                );

                list.add(p);
            }
        } catch (SQLException e) {
            System.err.println("Gagal ambil data produk: " + e.getMessage());
        }

        return list;
    }

    // ✅ Cari produk berdasarkan nama
    public List<Produk> getByNama(String nama) {
        List<Produk> list = new ArrayList<>();
        String sql = "SELECT * FROM produk WHERE nama LIKE ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + nama + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Produk p = new Produk(
                        rs.getString("id"),
                        rs.getString("nama"),
                        rs.getInt("stock"),
                        rs.getString("kategori"),
                        null);
                list.add(p);
            }
        } catch (SQLException e) {
            System.err.println("Gagal cari produk berdasarkan nama: " + e.getMessage());
        }
        return list;
    }

    // ✅ Update produk
    public boolean update(Produk p) {
        String sql = "UPDATE produk SET nama=?, kategori=?, stock=? WHERE id=?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, p.getName());
            stmt.setString(2, p.getKategori());
            stmt.setInt(3, p.getStock());
            stmt.setString(4, p.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Gagal update produk: " + e.getMessage());
            return false;
        }
    }

    // ✅ Delete produk
    public boolean delete(String id) {
        String sql = "DELETE FROM produk WHERE id = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Gagal hapus produk: " + e.getMessage());
            return false;
        }
    }
}
