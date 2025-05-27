package com.kelompok5.kelompok5app.databaseAcces;

import com.kelompok5.kelompok5app.config.databaseConnection;
import com.kelompok5.kelompok5app.model.material;
import com.kelompok5.kelompok5app.model.materialProduk;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class materialProdukCRUD {
    private Connection conn = databaseConnection.getConnection();
    private materialCRUD mCRUD = new materialCRUD();

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

    public List<materialProduk> getByProdukId(int idProduk) {
        List<materialProduk> list = new ArrayList<>();
        String sql = "SELECT * FROM material_produk WHERE id_produk = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idProduk);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String idMaterial = rs.getString("id_material");
                    material mat = null;
                    for (material m : mCRUD.getAllMaterial()) {
                        if (m.id.equals(idMaterial)) {
                            mat = m;
                            break;
                        }
                    }
                    materialProduk mp = new materialProduk(
                            rs.getInt("id"),
                            rs.getString("id_produk"),
                            mat,
                            rs.getInt("jumlah")
                    );
                    list.add(mp);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
