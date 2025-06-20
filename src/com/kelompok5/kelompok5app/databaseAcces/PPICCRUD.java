package com.kelompok5.kelompok5app.databaseAcces;

import com.kelompok5.kelompok5app.config.databaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * DAO khusus modul PPIC.
 * <p>
 * Fitur utama:
 * <ul>
 *   <li>Tambah stok produk dengan validasi stok maks & ketersediaan material BOM.</li>
 *   <li>Transaksi atomik (commit / rollback).</li>
 *   <li>Mengembalikan enum {@link StokError} agar controller bisa menampilkan pesan spesifik.</li>
 * </ul>
 */
public class PPICCRUD {

    /** Hasil operasi tambah stok. */
    public enum StokError {
        SUCCESS,
        INVALID_QTY,
        PRODUCT_NOT_FOUND,
        EXCEED_MAX_STOCK,
        NO_BOM_DEFINED,
        MATERIAL_SHORTAGE,
        DB_ERROR
    }

    private final Connection conn;

    public PPICCRUD() {
        this.conn = databaseConnection.getConnection();
    }

    /**
     * Tambah stok produk.
     *
     * @param idProduk  kode produk
     * @param qtyTambah jumlah stok yang ingin ditambahkan
     * @return enum {@link StokError} menggambarkan hasil
     */
    public StokError tambahStokProduk(String idProduk, int qtyTambah) {
        if (qtyTambah <= 0) return StokError.INVALID_QTY;

        try {
            conn.setAutoCommit(false);

            /* 1️⃣ Ambil stok & max_stock produk */
            String qProduk = "SELECT stock, max_stock FROM produk WHERE id = ? FOR UPDATE";
            int stokNow, stokMax;
            try (PreparedStatement ps = conn.prepareStatement(qProduk)) {
                ps.setString(1, idProduk);
                ResultSet rs = ps.executeQuery();
                if (!rs.next()) {
                    conn.rollback();
                    return StokError.PRODUCT_NOT_FOUND;
                }
                stokNow = rs.getInt("stock");
                stokMax = rs.getInt("max_stock");
            }

            if (stokNow + qtyTambah > stokMax) {
                conn.rollback();
                return StokError.EXCEED_MAX_STOCK;
            }

            /* 2️⃣ Validasi material BOM */
            String qBom =
                    "SELECT pm.id_material, pm.jumlah AS per_prod, b.stock AS stok_mat " +
                    "FROM   produk_material pm " +
                    "JOIN   barang b ON b.id = pm.id_material " +
                    "WHERE  pm.id_produk = ? FOR UPDATE";
            Map<String, Integer> needMap = new HashMap<>();
            try (PreparedStatement ps = conn.prepareStatement(qBom)) {
                ps.setString(1, idProduk);
                ResultSet rs = ps.executeQuery();

                boolean hasBom = false;
                while (rs.next()) {
                    hasBom = true;
                    String idMat  = rs.getString("id_material");
                    int perProd   = rs.getInt("per_prod");
                    int stokMat   = rs.getInt("stok_mat");
                    int totalNeed = perProd * qtyTambah;

                    if (stokMat < totalNeed) {
                        conn.rollback();
                        return StokError.MATERIAL_SHORTAGE;
                    }
                    needMap.put(idMat, totalNeed);
                }
                if (!hasBom) {
                    conn.rollback();
                    return StokError.NO_BOM_DEFINED;
                }
            }

            /* 3️⃣ Kurangi stok material */
            String uMat = "UPDATE barang SET stock = stock - ? WHERE id = ?";
            try (PreparedStatement ps = conn.prepareStatement(uMat)) {
                for (Map.Entry<String, Integer> e : needMap.entrySet()) {
                    ps.setInt   (1, e.getValue());
                    ps.setString(2, e.getKey());
                    ps.addBatch();
                }
                ps.executeBatch();
            }

            /* 4️⃣ Tambah stok produk */
            String uProd = "UPDATE produk SET stock = stock + ? WHERE id = ?";
            try (PreparedStatement ps = conn.prepareStatement(uProd)) {
                ps.setInt   (1, qtyTambah);
                ps.setString(2, idProduk);
                ps.executeUpdate();
            }

            conn.commit();
            return StokError.SUCCESS;

        } catch (SQLException ex) {
            try { conn.rollback(); } catch (SQLException ignore) {}
            System.err.println("[PPICCRUD] DB error: " + ex.getMessage());
            return StokError.DB_ERROR;
        } finally {
            try { conn.setAutoCommit(true); } catch (SQLException ignore) {}
        }
    }
}
