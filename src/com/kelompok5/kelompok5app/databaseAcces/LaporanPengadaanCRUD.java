package com.kelompok5.kelompok5app.databaseAcces;

import com.kelompok5.kelompok5app.config.databaseConnection;
import com.kelompok5.kelompok5app.model.LaporanPengadaan;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LaporanPengadaanCRUD {

    private Connection conn;

    public LaporanPengadaanCRUD() {
        conn = databaseConnection.getConnection();
    }

    // ✅ Ambil hanya barang yang BELUM DILAPORKAN
    public List<LaporanPengadaan> getBarangStokKurangBelumDilaporkan() {
        List<LaporanPengadaan> list = new ArrayList<>();
        String sql = "SELECT id, nama, min_stock, max_stock, stock, `order`, vendor, waktu_butuh_order " +
                    "FROM barang WHERE stock < min_stock AND sudah_dilaporkan = false";

        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int penambahan = rs.getInt("max_stock") - rs.getInt("stock");

                LaporanPengadaan lp = new LaporanPengadaan(
                        rs.getString("id"),
                        rs.getString("nama"),
                        null, // kategori tidak dibutuhkan
                        rs.getInt("min_stock"),
                        rs.getInt("max_stock"),
                        rs.getInt("stock"),
                        rs.getInt("order"),
                        rs.getString("vendor"),
                        rs.getString("waktu_butuh_order"),
                        penambahan
                );

                list.add(lp);
            }
        } catch (SQLException e) {
            System.err.println("❌ Gagal ambil barang belum dilaporkan: " + e.getMessage());
        }

        return list;
    }

    // ✅ Menandai barang sudah dilaporkan
    public void tandaiSudahDilaporkan(List<LaporanPengadaan> list) {
        String sql = "UPDATE barang SET sudah_dilaporkan = true WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            for (LaporanPengadaan lp : list) {
                stmt.setString(1, lp.getId());
                stmt.addBatch();
            }
            stmt.executeBatch();
        } catch (SQLException e) {
            System.err.println("❌ Gagal menandai barang sudah dilaporkan: " + e.getMessage());
        }
    }
}
