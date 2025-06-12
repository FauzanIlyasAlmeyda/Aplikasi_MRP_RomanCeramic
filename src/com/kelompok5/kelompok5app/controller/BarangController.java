package com.kelompok5.kelompok5app.controller;

import com.kelompok5.kelompok5app.databaseAcces.BarangCRUD;
import com.kelompok5.kelompok5app.model.Barang;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class BarangController {
    private BarangCRUD dao;

    public BarangController() {
        dao = new BarangCRUD();
    }

    // Tampilkan semua data dari tabel `barang`
    public void tampilkanSemuaBarang(JTable tabel) {
        DefaultTableModel model = (DefaultTableModel) tabel.getModel();
        model.setRowCount(0); // kosongkan isi tabel

        List<Barang> list = dao.getAll(); // ambil semua barang dari DAO
        for (Barang b : list) {
            model.addRow(new Object[] {
                    b.getId(),
                    b.getNama(),
                    b.getMin_stock(),
                    b.getMax_stock(),
                    b.getStock(),
                    "-", // kolom Order belum digunakan
                    b.getVendor(),
                    "-" // kolom Tanggal Diperbarui belum tersedia
            });
        }

    }

    public boolean insertBarang(Barang b) {
        return dao.insert(b);
    }

    public String generateId() {
        return dao.generateId();
    }
}
