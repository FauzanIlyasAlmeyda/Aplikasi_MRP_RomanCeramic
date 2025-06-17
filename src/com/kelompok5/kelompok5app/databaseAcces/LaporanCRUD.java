package com.kelompok5.kelompok5app.databaseAcces;

import com.kelompok5.kelompok5app.config.databaseConnection;
import com.kelompok5.kelompok5app.model.LaporanPengadaan;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LaporanCRUD {
    private Connection conn;

    public LaporanCRUD() {
        conn = databaseConnection.getConnection();
    }

    ///Generate nama laporan
    public String generateNamaLaporan() {
        String base = "Laporan";
        String sql = "SELECT COUNT(*) AS total FROM laporan";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                int count = rs.getInt("total") + 1;
                return base + count;
            }
        } catch (SQLException e) {
            System.err.println("Gagal generate nama laporan: " + e.getMessage());
        }
        return base + "1";
    }

    //Insert laporan + detail
    public boolean simpanLaporan(List<LaporanPengadaan> detailLaporan) {
        String sqlLaporan = "INSERT INTO laporan (nama_laporan) VALUES (?)";
        String sqlDetail = "INSERT INTO laporan_detail (id_laporan, id_barang, nama_barang, stok_min, stok, stok_max, vendor, waktu_order_terakhir, penambahan) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            conn.setAutoCommit(false); // mulai transaksi

            // Insert laporan
            String namaLaporan = generateNamaLaporan();
            PreparedStatement stmtLaporan = conn.prepareStatement(sqlLaporan, Statement.RETURN_GENERATED_KEYS);
            stmtLaporan.setString(1, namaLaporan);
            stmtLaporan.executeUpdate();

            ResultSet generatedKeys = stmtLaporan.getGeneratedKeys();
            int idLaporan = -1;
            if (generatedKeys.next()) {
                idLaporan = generatedKeys.getInt(1);
            } else {
                conn.rollback();
                return false;
            }

            // Insert detail laporan
            PreparedStatement stmtDetail = conn.prepareStatement(sqlDetail);
            for (LaporanPengadaan lp : detailLaporan) {
                stmtDetail.setInt(1, idLaporan);
                stmtDetail.setString(2, lp.getId());
                stmtDetail.setString(3, lp.getNamaBarang());
                stmtDetail.setInt(4, lp.getStokMin());
                stmtDetail.setInt(5, lp.getStokTersedia());
                stmtDetail.setInt(6, lp.getStokMax());
                stmtDetail.setString(7, lp.getVendor());
                stmtDetail.setString(8, lp.getWaktuOrderTerakhir());
                stmtDetail.setInt(9, lp.getPenambahan());
                stmtDetail.addBatch();
            }
            stmtDetail.executeBatch();

            conn.commit(); 
            return true;
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                System.err.println("Rollback gagal: " + ex.getMessage());
            }
            System.err.println("Gagal simpan laporan: " + e.getMessage());
            return false;
        } finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                System.err.println("Gagal set autoCommit true: " + e.getMessage());
            }
        }
    }

    // (untuk List Laporan)
    public List<String[]> getListLaporan() {
        List<String[]> list = new ArrayList<>();
        String sql = "SELECT id, nama_laporan, waktu_dibuat FROM laporan ORDER BY id DESC";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new String[]{
                        String.valueOf(rs.getInt("id")),
                        rs.getString("nama_laporan"),
                        rs.getString("waktu_dibuat")
                });
            }
        } catch (SQLException e) {
            System.err.println("Gagal ambil list laporan: " + e.getMessage());
        }
        return list;
    }

    // detail laporan berdasarkan ID
    public List<LaporanPengadaan> getDetailLaporan(int idLaporan) {
        List<LaporanPengadaan> list = new ArrayList<>();
        String sql = "SELECT * FROM laporan_detail WHERE id_laporan = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idLaporan);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                LaporanPengadaan lp = new LaporanPengadaan(
                        rs.getString("id_barang"),
                        rs.getString("nama_barang"),
                        rs.getInt("stok_min"),
                        rs.getInt("stok"),
                        rs.getInt("stok_max"),
                        rs.getString("vendor"),
                        rs.getString("waktu_order_terakhir"),
                        rs.getInt("penambahan")
                );
                list.add(lp);
            }
        } catch (SQLException e) {
            System.err.println("Gagal ambil detail laporan: " + e.getMessage());
        }
        return list;
    }
}
