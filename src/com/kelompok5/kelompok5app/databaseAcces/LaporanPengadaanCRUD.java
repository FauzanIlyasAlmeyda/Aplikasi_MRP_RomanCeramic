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

    public List<LaporanPengadaan> getSemuaLaporan() {
        List<LaporanPengadaan> list = new ArrayList<>();
        String sql = "SELECT id, nama, min_stock, max_stock, stock, vendor, updated_at FROM barang";

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                String id = rs.getString("id");
                String nama = rs.getString("nama");
                int stokMin = rs.getInt("min_stock");
                int stokMax = rs.getInt("max_stock");
                int stokTersedia = rs.getInt("stock");
                String vendor = rs.getString("vendor");
                String waktu = rs.getString("updated_at"); // pastikan kolom ini ada di tabel

                // Hitung penambahan jika stok tersedia < stokMin
                int penambahan = 0;
                if (stokTersedia < stokMin) {
                    penambahan = stokMax - stokTersedia;
                }

                // Buat objek laporan
                LaporanPengadaan laporan = new LaporanPengadaan(
                    id, nama, stokMin, stokTersedia, stokMax, vendor, waktu, penambahan
                );

                list.add(laporan);
            }

        } catch (SQLException e) {
            System.err.println("Gagal ambil data laporan pengadaan: " + e.getMessage());
        }

        return list;
    }
}
