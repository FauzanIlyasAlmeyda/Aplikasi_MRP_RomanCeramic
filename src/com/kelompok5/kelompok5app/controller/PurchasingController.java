package com.kelompok5.kelompok5app.controller;

import com.kelompok5.kelompok5app.databaseAcces.LaporanCRUD;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class PurchasingController {

    private LaporanCRUD crud = new LaporanCRUD();

    public void tampilkanLaporan(JTable tabel) {
        List<String[]> list = crud.getListLaporan();
        DefaultTableModel model = (DefaultTableModel) tabel.getModel();
        model.setRowCount(0);

        for (String[] row : list) {
            model.addRow(new Object[] {
                    row[1], // nama laporan
                    "ACC",
                    "TOLAK"
            });
        }
    }

    public boolean accLaporan(int index) {
        int id = getIdByIndex(index);
        return crud.accLaporan(id);
    }

    public boolean tolakLaporan(int index) {
        int id = getIdByIndex(index);
        return crud.tolakLaporan(id);
    }

    public int getIdByIndex(int row) {
        List<String[]> list = crud.getListLaporan();
        if (row >= 0 && row < list.size()) {
            return Integer.parseInt(list.get(row)[0]);
        }
        return -1;
    }
}
