package com.kelompok5.kelompok5app.databaseAcces;

import com.kelompok5.kelompok5app.config.databaseConnection;
import com.kelompok5.kelompok5app.model.Barang;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BarangCRUD {
    private Connection conn;

    public BarangCRUD() {
        conn = databaseConnection.getConnection();
    }

    public String generateId() {
        String prefix = "BRG";
        String query = "SELECT id FROM barang ORDER BY id DESC LIMIT 1";
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                String lastId = rs.getString("id");
                int number = Integer.parseInt(lastId.replace(prefix, ""));
                return prefix + String.format("%03d", number + 1);
            }
        } catch (SQLException e) {
            System.err.println("Gagal generate ID: " + e.getMessage());
        }
        return prefix + "001";
    }

    public String generateId(String kategori) {
        String prefix;
        if (kategori.equalsIgnoreCase("material")) {
            prefix = "MTR";
        } else {
            prefix = "BRG";
        }

        String query = "SELECT id FROM barang WHERE id LIKE ? ORDER BY id DESC LIMIT 1";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, prefix + "%");
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String lastId = rs.getString("id");
                int number = Integer.parseInt(lastId.replace(prefix, ""));
                return prefix + String.format("%03d", number + 1);
            }
        } catch (SQLException e) {
            System.err.println("Gagal generate ID untuk kategori " + kategori + ": " + e.getMessage());
        }
        return prefix + "001";
    }

    public boolean insert(Barang b) {
        String sql = "INSERT INTO barang (id, nama, kategori, min_stock, max_stock, stock, `order`, vendor, harga, updated_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, b.getId());
            stmt.setString(2, b.getNama());
            stmt.setString(3, b.getKategori());
            stmt.setInt(4, b.getMin_stock());
            stmt.setInt(5, b.getMax_stock());
            stmt.setInt(6, b.getStock());
            stmt.setInt(7, b.getOrder());
            stmt.setString(8, b.getVendor());
            stmt.setDouble(9, b.getHarga());
            stmt.setString(10, b.getUpdated_at()); // atau bisa pakai new Timestamp(System.currentTimeMillis())

            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("❌ Gagal insert barang:");
            e.printStackTrace(); // <--- Tambahkan ini untuk lihat error detail
            return false;
        }
    }

    public List<Barang> getAll() {
        List<Barang> list = new ArrayList<>();
        String sql = "SELECT * FROM barang";
        try (Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Barang b = new Barang(
                        rs.getString("id"),
                        rs.getString("nama"),
                        rs.getString("kategori"),
                        rs.getInt("min_stock"),
                        rs.getInt("max_stock"),
                        rs.getInt("stock"),
                        rs.getInt("order"),
                        rs.getString("vendor"),
                        rs.getDouble("harga"),
                        rs.getString("updated_at"));
                list.add(b);
            }
        } catch (SQLException e) {
            System.err.println("Gagal ambil data barang: " + e.getMessage());
        }
        return list;
    }

    public List<Barang> getByNama(String nama) {
        List<Barang> list = new ArrayList<>();
        String sql = "SELECT * FROM barang WHERE nama LIKE ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + nama + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Barang b = new Barang(
                        rs.getString("id"),
                        rs.getString("nama"),
                        rs.getString("kategori"),
                        rs.getInt("min_stock"),
                        rs.getInt("max_stock"),
                        rs.getInt("stock"),
                        rs.getInt("order"),
                        rs.getString("vendor"),
                        rs.getDouble("harga"),
                        rs.getString("updated_at"));
                list.add(b);
            }
        } catch (SQLException e) {
            System.err.println("Gagal cari barang berdasarkan nama: " + e.getMessage());
        }
        return list;
    }

    public boolean update(Barang b) {
        String sql = "UPDATE barang SET nama=?, kategori=?, min_stock=?, max_stock=?, stock=?, `order`=?, vendor=?, harga=? WHERE id=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, b.getNama());
            stmt.setString(2, b.getKategori());
            stmt.setInt(3, b.getMin_stock());
            stmt.setInt(4, b.getMax_stock());
            stmt.setInt(5, b.getStock());
            stmt.setInt(6, b.getOrder());
            stmt.setString(7, b.getVendor());
            stmt.setDouble(8, b.getHarga());
            stmt.setString(9, b.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Gagal update barang: " + e.getMessage());
            return false;
        }
    }

    public boolean delete(String id) {
        String sql = "DELETE FROM barang WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Gagal hapus barang: " + e.getMessage());
            return false;
        }
    }
}
