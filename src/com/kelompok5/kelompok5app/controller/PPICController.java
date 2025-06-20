package com.kelompok5.kelompok5app.controller;

import com.kelompok5.kelompok5app.databaseAcces.PPICCRUD;
import com.kelompok5.kelompok5app.databaseAcces.MaterialprodukCRUD;
import com.kelompok5.kelompok5app.databaseAcces.ProdukCRUD;
import com.kelompok5.kelompok5app.model.Materialproduk;
import com.kelompok5.kelompok5app.model.Produk;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

/**
 * Controller untuk halaman Dashboard PPIC.
 */
public class PPICController {

    private final ProdukCRUD         produkDao;
    private final PPICCRUD           ppicDao;
    private final MaterialprodukCRUD mpDao;

    public PPICController() {
        produkDao = new ProdukCRUD();
        ppicDao   = new PPICCRUD();
        mpDao     = new MaterialprodukCRUD();
    }

    /** Mengisi JTable di Dashboard PPIC. */
    public void tampilkanSemuaProduk(JTable tabel) {
        DefaultTableModel model = (DefaultTableModel) tabel.getModel();
        model.setRowCount(0);

        List<Produk> produkList = produkDao.getAll();
        for (Produk p : produkList) {
            StringBuilder bom = new StringBuilder();
            for (Materialproduk mp : mpDao.getByProdukId(p.getId())) {
                if (mp.getMaterial() != null) {
                    bom.append(mp.getMaterial().getNama())
                       .append(" x").append(mp.getJumlah())
                       .append(", ");
                }
            }
            if (bom.length() > 0) bom.setLength(bom.length() - 2);

            model.addRow(new Object[]{
                    p.getId(),
                    p.getName(),
                    bom.toString(),
                    p.getStock()
            });
        }
    }

    /**
     * Aksi tambah stok produk via PPICCRUD.
     *
     * @param parentComponent komponen induk (untuk JOptionPane)
     * @param idProduk        kode produk
     * @param qty             jumlah yang ingin ditambahkan
     */
    public void tambahStokProduk(java.awt.Component parentComponent,
                                 String idProduk, int qty) {

        PPICCRUD.StokError res = ppicDao.tambahStokProduk(idProduk, qty);

        switch (res) {
            case SUCCESS -> JOptionPane.showMessageDialog(parentComponent,
                    "Stok berhasil ditambah ☑️",
                    "Berhasil", JOptionPane.INFORMATION_MESSAGE);

            case INVALID_QTY       -> showErr(parentComponent, "Jumlah harus lebih dari 0");
            case PRODUCT_NOT_FOUND -> showErr(parentComponent, "Produk tidak ditemukan");
            case EXCEED_MAX_STOCK  -> showErr(parentComponent, "Stok melebihi batas maksimum produk");
            case NO_BOM_DEFINED    -> showErr(parentComponent, "Produk belum memiliki BOM");
            case MATERIAL_SHORTAGE -> showErr(parentComponent, "Stok material tidak mencukupi");
            case DB_ERROR          -> showErr(parentComponent, "Terjadi kesalahan pada database");
        }
    }

    private void showErr(java.awt.Component parent, String msg) {
        JOptionPane.showMessageDialog(parent, msg, "Gagal", JOptionPane.ERROR_MESSAGE);
    }
}
