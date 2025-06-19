package com.kelompok5.kelompok5app.controller;

import com.kelompok5.kelompok5app.databaseAcces.LaporanCRUD;
import javax.swing.JTable;
import com.kelompok5.kelompok5app.model.LaporanPengadaan;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class LaporanListController {

    private LaporanCRUD crud;

    public LaporanListController() {
        crud = new LaporanCRUD();
    }

    public void tampilkanListLaporan(JTable tabel) {
        List<String[]> list = crud.getListLaporan();
        DefaultTableModel model = (DefaultTableModel) tabel.getModel();
        model.setRowCount(0); // bersihkan isi tabel

        for (String[] row : list) {
            model.addRow(new Object[] {
                    row[1], // nama_laporan
                    row[2], // waktu_dibuat
                    "Detail", // tombol atau teks aksi
                    "Hapus" // tombol atau teks hapus
            });
        }
    }

    public void tampilkanDetailLaporan(int idLaporan, JTable tabel) {
        List<LaporanPengadaan> detailList = crud.getDetailLaporan(idLaporan);
        DefaultTableModel model = (DefaultTableModel) tabel.getModel();
        model.setRowCount(0);

        for (LaporanPengadaan lp : detailList) {
            model.addRow(new Object[] {
                    lp.getId(),
                    lp.getNamaBarang(),
                    lp.getStokMin(),
                    lp.getStokMax(),
                    lp.getStokTersedia(),
                    lp.getOrder(),
                    lp.getVendor(),
                    lp.getWaktuOrderTerakhir(),
                    lp.getPenambahan()
            });
        }
    }

    public int getIdLaporanByRow(int row) {
        List<String[]> list = crud.getListLaporan();
        if (row >= 0 && row < list.size()) {
            try {
                return Integer.parseInt(list.get(row)[0]); // indeks 0 adalah id
            } catch (NumberFormatException e) {
                System.err.println("❌ ID tidak valid: " + e.getMessage());
            }
        }
        return -1; // jika tidak valid
    }

    public String getNamaLaporanById(int idLaporan) {
        return crud.getNamaLaporanById(idLaporan);
    }

}
