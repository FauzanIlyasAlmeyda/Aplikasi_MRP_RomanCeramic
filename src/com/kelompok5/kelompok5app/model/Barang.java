package com.kelompok5.kelompok5app.model;

public class Barang {
    protected String id;
    protected String nama;
    protected String kategori;
    protected int min_stock;
    protected int max_stock;
    protected int stock;
    protected int order;
    protected String vendor;
    protected String updated_at;

    // Constructor lengkap
    public Barang(String id, String nama, String kategori, int min_stock, int max_stock, int stock, int order, String vendor, String updated_at) {
        this.id = id;
        this.nama = nama;
        this.kategori = kategori;
        this.min_stock = min_stock;
        this.max_stock = max_stock;
        this.stock = stock;
        this.order = order;
        this.vendor = vendor;
        this.updated_at = updated_at;
    }

    // Constructor sederhana
    public Barang(String id, String nama, int stock, String kategori) {
        this.id = id;
        this.nama = nama;
        this.stock = stock;
        this.kategori = kategori;
    }

    // Getter & Setter
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public int getMin_stock() {
        return min_stock;
    }

    public void setMin_stock(int min_stock) {
        this.min_stock = min_stock;
    }

    public int getMax_stock() {
        return max_stock;
    }

    public void setMax_stock(int max_stock) {
        this.max_stock = max_stock;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}
