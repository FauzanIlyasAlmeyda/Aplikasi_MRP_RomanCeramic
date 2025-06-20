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

    // Generate nama laporan otomatis
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

    // Simpan laporan dan detailnya
    public boolean simpanLaporan(List<LaporanPengadaan> detailLaporan) {
        String sqlLaporan = "INSERT INTO laporan (nama_laporan) VALUES (?)";
        String sqlDetail = "INSERT INTO laporan_detail (id_laporan, id_barang, nama_barang, stok_min, stok_max, stok, order_barang, vendor, waktu_order_terakhir, penambahan) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            conn.setAutoCommit(false); // mulai transaksi

            // Insert ke tabel laporan
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

            // Insert ke detail laporan
            PreparedStatement stmtDetail = conn.prepareStatement(sqlDetail);
            for (LaporanPengadaan lp : detailLaporan) {
                int order = ambilOrderTerakhir(lp.getId());
                String waktuOrderTerakhir = ambilWaktuOrderTerakhir(lp.getId());
                stmtDetail.setInt(1, idLaporan);
                stmtDetail.setString(2, lp.getId());
                stmtDetail.setString(3, lp.getNamaBarang());
                stmtDetail.setInt(4, lp.getStokMin());
                stmtDetail.setInt(5, lp.getStokMax());
                stmtDetail.setInt(6, lp.getStokTersedia());
                stmtDetail.setInt(7, order);
                stmtDetail.setString(8, lp.getVendor());
                stmtDetail.setString(9, waktuOrderTerakhir);
                stmtDetail.setInt(10, lp.getPenambahan());
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

    // Ambil daftar laporan (hanya header)
    public List<String[]> getListLaporan() {
        List<String[]> list = new ArrayList<>();
        String sql = "SELECT id, nama_laporan, waktu_dibuat FROM laporan ORDER BY id DESC";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new String[] {
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

    // Ambil detail laporan berdasarkan ID laporan
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
                        rs.getInt("stok_max"),
                        rs.getInt("stok"),
                        rs.getInt("order_barang"),
                        rs.getString("vendor"),
                        rs.getString("waktu_order_terakhir"),
                        rs.getInt("penambahan"));
                list.add(lp);
            }
        } catch (SQLException e) {
            System.err.println("Gagal ambil detail laporan: " + e.getMessage());
        }
        return list;
    }

    public String getNamaLaporanById(int idLaporan) {
        String nama = "";
        String sql = "SELECT nama_laporan FROM laporan WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idLaporan);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                nama = rs.getString("nama_laporan");
            }
        } catch (SQLException e) {
            System.err.println("❌ Gagal ambil nama laporan: " + e.getMessage());
        }
        return nama;
    }

    private String ambilWaktuOrderTerakhir(String idBarang) {
        String waktu = null;
        String sql = "SELECT waktu_order FROM order_barang WHERE id_barang = ? ORDER BY waktu_order DESC LIMIT 1";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, idBarang);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                waktu = rs.getString("waktu_order");
            }
        } catch (SQLException e) {
            System.err.println("❌ Gagal ambil waktu order terakhir: " + e.getMessage());
        }
        return waktu;
    }

    private int ambilOrderTerakhir(String idBarang) {
        String sql = "SELECT jumlah FROM order_barang WHERE id_barang = ? ORDER BY waktu_order DESC LIMIT 1";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, idBarang);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("jumlah");
            }
        } catch (SQLException e) {
            System.err.println("❌ Gagal ambil jumlah order: " + e.getMessage());
        }
        return 0;
    }

    public boolean tolakLaporan(int idLaporan) {
        try {
            conn.setAutoCommit(false);

            // Ambil semua id_barang dari laporan_detail sebelum dihapus
            String sqlAmbilBarang = "SELECT id_barang FROM laporan_detail WHERE id_laporan = ?";
            PreparedStatement stmtAmbil = conn.prepareStatement(sqlAmbilBarang);
            stmtAmbil.setInt(1, idLaporan);
            ResultSet rs = stmtAmbil.executeQuery();

            List<String> idBarangList = new ArrayList<>();
            while (rs.next()) {
                idBarangList.add(rs.getString("id_barang"));
            }

            // Hapus detail dan laporan
            String sql1 = "DELETE FROM laporan_detail WHERE id_laporan = ?";
            String sql2 = "DELETE FROM laporan WHERE id = ?";

            PreparedStatement stmt1 = conn.prepareStatement(sql1);
            stmt1.setInt(1, idLaporan);
            stmt1.executeUpdate();

            PreparedStatement stmt2 = conn.prepareStatement(sql2);
            stmt2.setInt(1, idLaporan);
            stmt2.executeUpdate();

            // Reset flag sudah_dilaporkan
            String sqlReset = "UPDATE barang SET sudah_dilaporkan = 0 WHERE id = ?";
            PreparedStatement stmtReset = conn.prepareStatement(sqlReset);
            for (String idBarang : idBarangList) {
                stmtReset.setString(1, idBarang);
                stmtReset.addBatch();
            }
            stmtReset.executeBatch();

            conn.commit();
            return true;
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                System.err.println("❌ Rollback gagal: " + ex.getMessage());
            }
            System.err.println("❌ Gagal tolak laporan: " + e.getMessage());
            return false;
        } finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                System.err.println("❌ Gagal set autoCommit true: " + e.getMessage());
            }
        }
    }

    public boolean accLaporan(int idLaporan) {
        String selectDetail = "SELECT * FROM laporan_detail WHERE id_laporan = ?";
        String updateBarang = "UPDATE barang SET stock = stock + ?, `order` = 0, waktu_butuh_order = NULL WHERE id = ?";
        String deleteOrder = "DELETE FROM order_barang WHERE id_barang = ?";
        String deleteDetail = "DELETE FROM laporan_detail WHERE id_laporan = ?";
        String deleteLaporan = "DELETE FROM laporan WHERE id = ?";

        try {
            conn.setAutoCommit(false);

            PreparedStatement stmtSelect = conn.prepareStatement(selectDetail);
            stmtSelect.setInt(1, idLaporan);
            ResultSet rs = stmtSelect.executeQuery();

            List<String> idBarangList = new ArrayList<>();

            while (rs.next()) {
                String idBarang = rs.getString("id_barang");
                int penambahan = rs.getInt("penambahan");

                // Update stok barang
                PreparedStatement stmtUpdate = conn.prepareStatement(updateBarang);
                stmtUpdate.setInt(1, penambahan);
                stmtUpdate.setString(2, idBarang);
                stmtUpdate.executeUpdate();

                // Hapus order terkait
                PreparedStatement stmtDeleteOrder = conn.prepareStatement(deleteOrder);
                stmtDeleteOrder.setString(1, idBarang);
                stmtDeleteOrder.executeUpdate();
            }

            // Hapus detail laporan
            PreparedStatement stmtDelDetail = conn.prepareStatement(deleteDetail);
            stmtDelDetail.setInt(1, idLaporan);
            stmtDelDetail.executeUpdate();

            // Hapus header laporan
            PreparedStatement stmtDelLaporan = conn.prepareStatement(deleteLaporan);
            stmtDelLaporan.setInt(1, idLaporan);
            stmtDelLaporan.executeUpdate();

            // Reset flag sudah_dilaporkan
            String reset = "UPDATE barang SET sudah_dilaporkan = 0 WHERE id = ?";
            PreparedStatement stmtReset = conn.prepareStatement(reset);
            for (String idBarang : idBarangList) {
                stmtReset.setString(1, idBarang);
                stmtReset.addBatch();
            }
            stmtReset.executeBatch();

            conn.commit();
            return true;
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                System.err.println("❌ Rollback gagal: " + ex.getMessage());
            }
            System.err.println("❌ Gagal ACC laporan: " + e.getMessage());
            return false;
        } finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                System.err.println("❌ Gagal set autoCommit true: " + e.getMessage());
            }
        }
    }

    public boolean hapusLaporan(int idLaporan) {
        try {
            conn.setAutoCommit(false);

            // Ambil semua id_barang
            String ambilBarangSQL = "SELECT id_barang FROM laporan_detail WHERE id_laporan = ?";
            PreparedStatement ambilStmt = conn.prepareStatement(ambilBarangSQL);
            ambilStmt.setInt(1, idLaporan);
            ResultSet rs = ambilStmt.executeQuery();

            List<String> idBarangList = new ArrayList<>();
            while (rs.next()) {
                idBarangList.add(rs.getString("id_barang"));
            }

            // Hapus laporan_detail
            PreparedStatement hapusDetail = conn.prepareStatement("DELETE FROM laporan_detail WHERE id_laporan = ?");
            hapusDetail.setInt(1, idLaporan);
            hapusDetail.executeUpdate();

            // Hapus laporan
            PreparedStatement hapusLaporan = conn.prepareStatement("DELETE FROM laporan WHERE id = ?");
            hapusLaporan.setInt(1, idLaporan);
            hapusLaporan.executeUpdate();

            // Set sudah_dilaporkan = 0
            PreparedStatement resetStmt = conn.prepareStatement("UPDATE barang SET sudah_dilaporkan = 0 WHERE id = ?");
            for (String idBarang : idBarangList) {
                resetStmt.setString(1, idBarang);
                resetStmt.addBatch();
            }
            resetStmt.executeBatch();

            conn.commit();
            return true;
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
            }
            System.err.println("❌ Gagal hapus laporan: " + e.getMessage());
            return false;
        } finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException e) {
            }
        }
    }

    public boolean simpanLaporanPPIC(List<LaporanPengadaan> detailLaporan) {
        String sqlLaporan = "INSERT INTO laporan (nama_laporan) VALUES (?)";
        String sqlDetail = "INSERT INTO laporan_detail (id_laporan, id_barang, nama_barang, stok_min, stok_max, stok, order_barang, vendor, waktu_order_terakhir, penambahan) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            conn.setAutoCommit(false);
            String namaLaporan = generateNamaLaporanPPIC();

            PreparedStatement stmtLaporan = conn.prepareStatement(sqlLaporan, Statement.RETURN_GENERATED_KEYS);
            stmtLaporan.setString(1, namaLaporan);
            stmtLaporan.executeUpdate();

            ResultSet rs = stmtLaporan.getGeneratedKeys();
            int idLaporan = -1;
            if (rs.next()) {
                idLaporan = rs.getInt(1);
            } else {
                conn.rollback();
                return false;
            }

            PreparedStatement stmtDetail = conn.prepareStatement(sqlDetail);
            for (LaporanPengadaan lp : detailLaporan) {
                stmtDetail.setInt(1, idLaporan);
                stmtDetail.setString(2, lp.getId());
                stmtDetail.setString(3, lp.getNamaBarang());
                stmtDetail.setInt(4, lp.getStokMin());
                stmtDetail.setInt(5, lp.getStokMax());
                stmtDetail.setInt(6, lp.getStokTersedia());
                stmtDetail.setInt(7, lp.getOrder());
                stmtDetail.setString(8, lp.getVendor());
                stmtDetail.setString(9, lp.getWaktuOrderTerakhir());
                stmtDetail.setInt(10, lp.getPenambahan());
                stmtDetail.addBatch();
            }
            stmtDetail.executeBatch();

            // tandai semua barang sudah dilaporkan
            for (LaporanPengadaan lp : detailLaporan) {
                PreparedStatement stmtFlag = conn
                        .prepareStatement("UPDATE barang SET sudah_dilaporkan = 1 WHERE id = ?");
                stmtFlag.setString(1, lp.getId());
                stmtFlag.executeUpdate();
            }

            conn.commit();
            return true;
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                System.err.println("Rollback gagal: " + ex.getMessage());
            }
            System.err.println("Gagal simpan laporanPPIC: " + e.getMessage());
            return false;
        } finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                System.err.println("Gagal setAutoCommit: " + e.getMessage());
            }
        }
    }

    public String generateNamaLaporanPPIC() {
        String base = "laporanPPIC_";
        String sql = "SELECT COUNT(*) AS total FROM laporan WHERE nama_laporan LIKE 'laporanPPIC_%'";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return base + (rs.getInt("total") + 1);
            }
        } catch (SQLException e) {
            System.err.println("Gagal generate nama laporanPPIC: " + e.getMessage());
        }
        return base + "1";
    }

}
