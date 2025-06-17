package com.kelompok5.kelompok5app.controller;

import com.kelompok5.kelompok5app.databaseAcces.OrderCRUD;
import com.kelompok5.kelompok5app.model.Order;

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

        List<Order> list = dao.getAll();

        for (Order ob : list) {
            model.addRow(new Object[]{
                ob.getIdOrder(),
                ob.getIdBarang(),
                ob.getJumlah(),
                ob.getWaktuOrder()
            });
        }
    }

    // Insert order baru
    public boolean insertOrder(Order order) {
        return dao.insert(order);
    }

    // Generate ID order baru
    public String generateId() {
        return dao.generateId();
    }
}
