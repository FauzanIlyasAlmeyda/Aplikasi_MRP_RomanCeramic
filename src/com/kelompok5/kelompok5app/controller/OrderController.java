package com.kelompok5.kelompok5app.controller;

import com.kelompok5.kelompok5app.databaseAcces.BarangCRUD;
import com.kelompok5.kelompok5app.databaseAcces.OrderCRUD;
import com.kelompok5.kelompok5app.model.Barang;
import com.kelompok5.kelompok5app.model.Order_;
import com.kelompok5.kelompok5app.model.Produk;
import com.kelompok5.kelompok5app.databaseAcces.ProdukCRUD;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class OrderController {

    private OrderCRUD dao;

    public OrderController() {
        dao = new OrderCRUD();
    }

    // Tampilkan semua order ke JTable
    public void tampilkanSemuaOrder(JTable tabel) {
        DefaultTableModel model = (DefaultTableModel) tabel.getModel();
        model.setRowCount(0); // kosongkan tabel

        List<Order_> list = dao.getAll();

        for (Order_ ob : list) {
            model.addRow(new Object[] {
                    ob.getIdOrder(),
                    ob.getIdBarang(),
                    ob.getJumlah(),
                    ob.getWaktuOrder()
            });
        }
    }

    // Insert order baru
    public boolean insertOrder(Order_ order) {
        return dao.insert(order);
    }

    // Generate ID order baru
    public String generateId() {
        return dao.generateId();
    }

    public void tampilkanSemuaBarang(JTable tabel) {
        BarangCRUD barangDao = new BarangCRUD(); // bisa juga diatur di constructor jika ingin reuse
        DefaultTableModel model = (DefaultTableModel) tabel.getModel();
        model.setRowCount(0); // Kosongkan tabel

        List<Barang> list = barangDao.getAll();
        for (Barang b : list) {
            model.addRow(new Object[] {
                    b.getNama(),
                    b.getStock()
            });
        }
    }

    public void tampilkanSemuaProduk(JTable tabel) {
        ProdukCRUD produkCRUD = new ProdukCRUD();
        List<Produk> list = produkCRUD.getAll(); // sesuaikan
        DefaultTableModel model = (DefaultTableModel) tabel.getModel();
        model.setRowCount(0);
        for (Produk p : list) {
            model.addRow(new Object[] { p.getNama(), p.getStock() });
        }
    }

    public boolean insertOrderProduk(Order_ order) {
        return dao.insertProduk(order);
    }

}
