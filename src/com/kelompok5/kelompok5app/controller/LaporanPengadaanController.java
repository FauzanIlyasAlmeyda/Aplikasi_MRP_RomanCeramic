package com.kelompok5.kelompok5app.controller;

import com.kelompok5.kelompok5app.databaseAcces.LaporanPengadaanCRUD;
import com.kelompok5.kelompok5app.model.LaporanPengadaan;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class LaporanPengadaanController {
    private LaporanPengadaanCRUD crud;

    public LaporanPengadaanController() {
        crud = new LaporanPengadaanCRUD();
    }

    // Menampilkan data barang dengan stok < min stok ke tabel
    public void tampilkanBarangKurangStok(JTable tabel) {
        List<LaporanPengadaan> list = crud.getBarangStokKurangBelumDilaporkan();
        DefaultTableModel model = (DefaultTableModel) tabel.getModel();
        model.setRowCount(0); // reset isi tabel

        for (LaporanPengadaan lp : list) {
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

    public boolean simpanLaporanDariDashboard(JTable tabel) {
        List<LaporanPengadaan> data = crud.getBarangStokKurangBelumDilaporkan();

        if (data.isEmpty()) {
            return false;
        }

        // Simpan ke tabel laporan + detail
        com.kelompok5.kelompok5app.databaseAcces.LaporanCRUD laporanCRUD = new com.kelompok5.kelompok5app.databaseAcces.LaporanCRUD();
        boolean berhasil = laporanCRUD.simpanLaporan(data);

        if (berhasil) {
            // Tandai semua barang yang sudah dilaporkan
            crud.tandaiSudahDilaporkan(data);

            // Refresh tampilan tabel agar dashboard kosong
            tampilkanBarangKurangStok(tabel);
        }

        return berhasil;
    }

}
