package com.kelompok5.kelompok5app.model;

public class LaporanPengadaan {
    private String id;
    private String namaBarang;
    private int stokMin;
    private int stokTersedia;
    private int stokMax;
    private String vendor;
    private String waktuOrderTerakhir;
    private int penambahan;

    public LaporanPengadaan(String id, String namaBarang, int stokMin, int stokTersedia, int stokMax, String vendor, String waktuOrderTerakhir, int penambahan) {
        this.id = id;
        this.namaBarang = namaBarang;
        this.stokMin = stokMin;
        this.stokTersedia = stokTersedia;
        this.stokMax = stokMax;
        this.vendor = vendor;
        this.waktuOrderTerakhir = waktuOrderTerakhir;
        this.penambahan = penambahan;
    }

    // Getter & Setter
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

    public int getStokMin() {
        return stokMin;
    }

    public void setStokMin(int stokMin) {
        this.stokMin = stokMin;
    }

    public int getStokTersedia() {
        return stokTersedia;
    }

    public void setStokTersedia(int stokTersedia) {
        this.stokTersedia = stokTersedia;
    }

    public int getStokMax() {
        return stokMax;
    }

    public void setStokMax(int stokMax) {
        this.stokMax = stokMax;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getWaktuOrderTerakhir() {
        return waktuOrderTerakhir;
    }

    public void setWaktuOrderTerakhir(String waktuOrderTerakhir) {
        this.waktuOrderTerakhir = waktuOrderTerakhir;
    }

    public int getPenambahan() {
        return penambahan;
    }

    public void setPenambahan(int penambahan) {
        this.penambahan = penambahan;
    }
}
