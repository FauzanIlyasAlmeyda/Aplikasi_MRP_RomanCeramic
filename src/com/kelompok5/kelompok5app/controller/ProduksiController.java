package com.kelompok5.kelompok5app.controller;

import com.kelompok5.kelompok5app.databaseAcces.MaterialCRUD;
import com.kelompok5.kelompok5app.databaseAcces.MaterialprodukCRUD;
import com.kelompok5.kelompok5app.databaseAcces.ProdukCRUD;
import com.kelompok5.kelompok5app.model.Material;
import com.kelompok5.kelompok5app.model.Materialproduk;
import com.kelompok5.kelompok5app.model.Produk;

import java.util.List;

public class ProduksiController {
    private final ProdukCRUD produkDAO;
    private final MaterialprodukCRUD mpDAO;
    private final MaterialCRUD materialDAO;

    public ProduksiController() {
        produkDAO = new ProdukCRUD();
        mpDAO = new MaterialprodukCRUD();
        materialDAO = new MaterialCRUD();
    }

    // Hitung jumlah maksimum produk yang bisa dibuat
    public int hitungMaksProduksi(Produk produk) {
        List<Materialproduk> bahanBaku = mpDAO.getByProdukId(produk.getId());

        int maxProduksi = Integer.MAX_VALUE;

        for (Materialproduk mp : bahanBaku) {
            Material m = mp.getMaterial();
            int jumlahPerProduk = mp.getJumlah();
            int stokBahan = m.getStock();

            int produksiDariBahanIni = stokBahan / jumlahPerProduk;

            if (produksiDariBahanIni < maxProduksi) {
                maxProduksi = produksiDariBahanIni;
            }
        }

        return maxProduksi;
    }

    // Lakukan produksi produk sebanyak `jumlahProduksi`
    public boolean produksi(Produk produk, int jumlahProduksi) {
        List<Materialproduk> bahanBaku = mpDAO.getByProdukId(produk.getId());

        // Cek apakah bahan cukup
        for (Materialproduk mp : bahanBaku) {
            Material m = mp.getMaterial();
            int totalButuh = mp.getJumlah() * jumlahProduksi;

            if (m.getStock() < totalButuh) {
                System.err.println("Stok bahan " + m.getNama() + " tidak cukup!");
                return false;
            }
        }

        // Kurangi stok bahan
        for (Materialproduk mp : bahanBaku) {
            Material m = mp.getMaterial();
            int totalButuh = mp.getJumlah() * jumlahProduksi;
            m.setStock(m.getStock() - totalButuh);
            materialDAO.updateMaterial(m); // Update ke DB
        }

        // Tambah stok produk
        produk.setStock(produk.getStock() + jumlahProduksi);
        return produkDAO.update(produk);
    }

    public boolean deleteProduk(String idProduk) {
    // Hapus semua materialproduk dulu (BOM-nya)
    boolean hapusBahan = materialDAO.deleteByProdukId(idProduk);
    // Hapus produk-nya
    boolean hapusProduk = produkDAO.delete(idProduk);

    return hapusProduk;
}

}
