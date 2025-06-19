package com.kelompok5.kelompok5app.model;

public class LaporanPengadaan {
    private String id;
    private String namaBarang;
    private String kategori;
    private int stokMin;
    private int stokMax;
    private int stokTersedia;
    private int order;
    private String vendor;
    private String waktuOrderTerakhir;
    private int penambahan;

    public LaporanPengadaan(String id, String namaBarang, String kategori, int stokMin, int stokMax,
                            int stokTersedia, int order, String vendor, String waktuOrderTerakhir, int penambahan) {
        this.id = id;
        this.namaBarang = namaBarang;
        this.kategori = kategori;
        this.stokMin = stokMin;
        this.stokMax = stokMax;
        this.stokTersedia = stokTersedia;
        this.order = order;
        this.vendor = vendor;
        this.waktuOrderTerakhir = waktuOrderTerakhir;
        this.penambahan = penambahan;
    }
    
    public LaporanPengadaan(String id, String namaBarang, int stokMin, int stokMax,
                            int stokTersedia, int order, String vendor, String waktuOrderTerakhir, int penambahan) {
        this.id = id;
        this.namaBarang = namaBarang;
        this.stokMin = stokMin;
        this.stokMax = stokMax;
        this.stokTersedia = stokTersedia;
        this.order = order;
        this.vendor = vendor;
        this.waktuOrderTerakhir = waktuOrderTerakhir;
        this.penambahan = penambahan;
    }

    // Getter dan Setter

    public String getId() {
        return id;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public String getKategori() {
        return kategori;
    }

    public int getStokMin() {
        return stokMin;
    }

    public int getStokMax() {
        return stokMax;
    }

    public int getStokTersedia() {
        return stokTersedia;
    }

    public int getOrder() {
        return order;
    }

    public String getVendor() {
        return vendor;
    }

    public String getWaktuOrderTerakhir() {
        return waktuOrderTerakhir;
    }

    public int getPenambahan() {
        return penambahan;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public void setStokMin(int stokMin) {
        this.stokMin = stokMin;
    }

    public void setStokMax(int stokMax) {
        this.stokMax = stokMax;
    }

    public void setStokTersedia(int stokTersedia) {
        this.stokTersedia = stokTersedia;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public void setWaktuOrderTerakhir(String waktuOrderTerakhir) {
        this.waktuOrderTerakhir = waktuOrderTerakhir;
    }

    public void setPenambahan(int penambahan) {
        this.penambahan = penambahan;
    }
}
