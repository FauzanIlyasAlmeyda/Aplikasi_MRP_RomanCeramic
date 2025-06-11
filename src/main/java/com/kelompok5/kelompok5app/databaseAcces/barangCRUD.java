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
        String querry = "SELECT id FROM barang ORDER BY id DESC LIMIT 1"; // ini ambil id yang terahir
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(querry);
            if (rs.next()) {
                String idTerahir = rs.getString("id");
                int number = Integer.parseInt(idTerahir.replace(prefix, "")); // ekstrak angkanya
                return prefix + String.format("%03d", number + 1);
            }
        } catch (SQLException e) {
            System.err.println("Gagal generate ID: " + e.getMessage());
        }
        return prefix + "001"; //jika kosong tabelnya bang
    }

    
    public boolean insert(Barang b) {
        String sql = "INSERT INTO barang (id, nama, kategori, min_stock, max_stock, stock, vendor, harga) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, b.getId());
            stmt.setString(2, b.getNama());
            stmt.setString(3, b.getKategori());
            stmt.setInt(4, b.getMin_stock());
            stmt.setInt(5, b.getMax_stock());
            stmt.setInt(6, b.getStock());
            stmt.setString(7, b.getVendor());
            stmt.setDouble(8, b.getHarga());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Gagal insert barang: " + e.getMessage());
            return false;
        }
    }

    public List<Barang> getAll() {
        List<Barang> list = new ArrayList<>();
        String sql = "SELECT * FROM barang";
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Barang b = new Barang(
                    rs.getString("id"),
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
            System.err.println("Gagal ambil data barang: " + e.getMessage());
        }
        return list;
    }


    public List<Barang> getByNama(String nama) {
        List<Barang> list = new ArrayList<>();
        String sql = "SELECT * FROM barang WHERE nama LIKE ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
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
                    rs.getString("vendor"),
                    rs.getDouble("harga")
                );
                list.add(b);
            }
        } catch (SQLException e) {
            System.err.println("Gagal" + e.getMessage());
        }
        return list;
    }

    
    public boolean update(Barang b) {
        String sql = "UPDATE barang SET nama=?, kategori=?, min_stock=?, max_stock=?, stock=?, vendor=?, harga=? WHERE id=?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, b.getNama());
            stmt.setString(2, b.getKategori());
            stmt.setInt(3, b.getMin_stock());
            stmt.setInt(4, b.getMax_stock());
            stmt.setInt(5, b.getStock());
            stmt.setString(6, b.getVendor());
            stmt.setDouble(7, b.getHarga());
            stmt.setString(8, b.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Gagal update barang: " + e.getMessage());
            return false;
        }
    }

    
    public boolean delete(String id) {
        String sql = "DELETE FROM barang WHERE id = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Gagal hapus barang: " + e.getMessage());
            return false;
        }
    }
}
