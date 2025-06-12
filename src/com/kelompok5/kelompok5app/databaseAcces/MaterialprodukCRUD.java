package com.kelompok5.kelompok5app.databaseAcces;

import com.kelompok5.kelompok5app.config.databaseConnection;
import com.kelompok5.kelompok5app.model.Material;
import com.kelompok5.kelompok5app.model.Materialproduk;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MaterialprodukCRUD {
    private Connection conn;

    public MaterialprodukCRUD() {
        conn = databaseConnection.getConnection();
    }

    
    public String generateId() {
        String prefix = "MP";
        String sql = "SELECT id FROM produk_material ORDER BY id DESC LIMIT 1";
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                String lastId = rs.getString("id");
                int num = Integer.parseInt(lastId.replace(prefix, ""));
                return prefix + String.format("%03d", num + 1);
            }
        } catch (SQLException e) {
            System.err.println("Gagal generate ID material produk: " + e.getMessage());
        }
        return prefix + "001";
    }

    
    public boolean insert(Materialproduk mp) {
        String sql = "INSERT INTO produk_material (id, produk_id, material_id, jumlah) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, mp.getId());
            stmt.setString(2, mp.getIdProduk());
            stmt.setString(3, mp.getMaterial().getId());
            stmt.setInt(4, mp.getJumlah());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Gagal insert materialproduk: " + e.getMessage());
            return false;
        }
    }

    
    public List<Materialproduk> getByProdukId(String idProduk) {
        List<Materialproduk> list = new ArrayList<>();
        String sql = "SELECT pm.id, pm.jumlah, m.* FROM produk_material pm " +
                "JOIN material m ON pm.material_id = m.id " +
                "WHERE pm.produk_id = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, idProduk);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Material m = new Material(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("kategori"),
                        rs.getInt("min_stock"),
                        rs.getInt("max_stock"),
                        rs.getInt("stock"),
                        rs.getString("vendor"),
                        rs.getDouble("harga"));

                Materialproduk mp = new Materialproduk(
                        rs.getString("pm.id"),
                        idProduk,
                        m,
                        rs.getInt("jumlah"));

                list.add(mp);
            }
        } catch (SQLException e) {
            System.err.println("Gagal ambil BOM produk: " + e.getMessage());
        }
        return list;
    }

    
    public boolean deleteByProdukId(String idProduk) {
        String sql = "DELETE FROM produk_material WHERE produk_id = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, idProduk);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Gagal hapus BOM produk: " + e.getMessage());
            return false;
        }
    }

    public boolean deleteByProdukAndMaterial(String idProduk, String idMaterial) {
        String sql = "DELETE FROM produk_material WHERE produk_id = ? AND material_id = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, idProduk);
            stmt.setString(2, idMaterial);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Gagal hapus satu bahan dari produk: " + e.getMessage());
            return false;
        }
    }

}
