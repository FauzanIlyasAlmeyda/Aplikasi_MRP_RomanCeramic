package com.kelompok5.kelompok5app.controller;

import com.kelompok5.kelompok5app.databaseAcces.LaporanPengadaanCRUD;
import com.kelompok5.kelompok5app.model.LaporanPengadaan;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class LaporanPengadaanController {
    private LaporanPengadaanCRUD dao;

    public LaporanPengadaanController() {
        dao = new LaporanPengadaanCRUD();
    }

    public void tampilkanLaporan(JTable tabel) {
        DefaultTableModel model = (DefaultTableModel) tabel.getModel();
        model.setRowCount(0); // Kosongkan tabel

        List<LaporanPengadaan> list = dao.getSemuaLaporan();
        for (LaporanPengadaan l : list) {
            model.addRow(new Object[]{
                l.getId(),
                l.getNamaBarang(),
                l.getStokMin(),
                l.getStokTersedia(),
                l.getStokMax(),
                l.getVendor(),
                l.getWaktuOrderTerakhir(),
                l.getPenambahan()
            });
        }
    }
}
