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

    public void tampilkanSemuaBarang(JTable tabel) {
    DefaultTableModel model = (DefaultTableModel) tabel.getModel();
    model.setRowCount(0); // kosongkan isi tabel

    List<Barang> list = dao.getAll(); // ambil semua barang dari DAO
    for (Barang b : list) {
        model.addRow(new Object[] {
                b.getId(),
                b.getNama(),
                b.getKategori(),       // ✅ tambahkan kategori
                b.getMin_stock(),
                b.getMax_stock(),
                b.getStock(),
                b.getOrder(),          // ✅ tampilkan order
                b.getVendor(),
                b.getUpdated_at()      // ✅ tampilkan updated_at
        });
    }
}


    public boolean insertBarang(Barang b) {
        return dao.insert(b);
    }

    public String generateId() {
        return dao.generateId();
    }

    public String generateId(String kategori) {
    return dao.generateId(kategori);
}

    
    public boolean deleteBarang(String id) {
    return dao.delete(id);
}
    
    public boolean updateBarang(Barang b) {
    return dao.update(b);
}


}
