package com.kelompok5.kelompok5app.controller;

import com.kelompok5.kelompok5app.databaseAcces.ProdukCRUD;
import com.kelompok5.kelompok5app.databaseAcces.MaterialprodukCRUD;
import com.kelompok5.kelompok5app.model.Produk;
import com.kelompok5.kelompok5app.model.Materialproduk;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ProdukController {
    private ProdukCRUD produkDao;
    private MaterialprodukCRUD materialDao;

    public ProdukController() {
        produkDao = new ProdukCRUD();
        materialDao = new MaterialprodukCRUD();
    }

    public void tampilkanSemuaProduk(JTable tabel) {
        DefaultTableModel model = (DefaultTableModel) tabel.getModel();
        model.setRowCount(0); // Kosongkan tabel

        List<Produk> list = produkDao.getAll();

        for (Produk p : list) {
            // Gabungkan daftar material
            StringBuilder bahan = new StringBuilder();
            for (Materialproduk mp : p.getMaterialList()) {
                if (mp.getMaterial() != null) {
                    bahan.append(mp.getMaterial().getNama())
                        .append(" x").append(mp.getJumlah())
                        .append(", ");
                }
            }

            if (bahan.length() > 0) {
                bahan.setLength(bahan.length() - 2); // hapus koma terakhir
            }

            model.addRow(new Object[] {
                p.getId(),
                p.getName(),
                bahan.toString(), // Kolom material
                p.getStock()      // Kolom stok
            });
        }
    }

    public boolean insertProduk(Produk p) {
        boolean produkBerhasil = produkDao.insert(p);
        if (!produkBerhasil) return false;

        for (Materialproduk mp : p.getMaterialList()) {
            if (!materialDao.insert(mp)) {
                System.err.println("Gagal simpan materialproduk untuk: " + mp.getMaterial().getNama());
                return false;
            }
        }

        return true;
    }

    public String generateId() {
        return produkDao.generateId();
    }
    
    public boolean updateProduk(Produk p) {
    return produkDao.update(p);
}

}
