package com.kelompok5.kelompok5app.databaseAcces;

import com.kelompok5.kelompok5app.config.databaseConnection;
import com.kelompok5.kelompok5app.model.produk;
import com.kelompok5.kelompok5app.model.materialProduk;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class produkCRUD {
    private Connection conn = databaseConnection.getConnection();
    private materialProdukCRUD mpCRUD = new materialProdukCRUD();

    public void tambahProduk(produk p) {
        String sql = "INSERT INTO produk (id, name, stock, kategori) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, p.id);
            stmt.setString(2, p.name);
            stmt.setInt(3, p.stock);
            stmt.setString(4, p.kategori);
            stmt.executeUpdate();

            // Simpan material produk yang berkaitan
            for (materialProduk mp : p.materialList) {
                mpCRUD.tambahMaterialProduk(mp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<produk> getAllProduk() {
        List<produk> list = new ArrayList<>();
        String sql = "SELECT * FROM produk";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                produk p = new produk(
                        id,
                        rs.getString("name"),
                        rs.getInt("stock"),
                        rs.getString("kategori"),
                        mpCRUD.getByProdukId(id)
                );
                list.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
