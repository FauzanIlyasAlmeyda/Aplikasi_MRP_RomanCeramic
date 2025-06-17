package com.kelompok5.kelompok5app.model;

public class Material extends Barang { 

    public Material(String id, String nama, String kategori, int min_stock, int max_stock, int stock, int order, String vendor, double harga, String updated_at) {
        super(id, nama, kategori, min_stock, max_stock, stock, order, vendor, harga, updated_at);
    }

    // Jika ingin tetap menggunakan getter/setter sendiri (meskipun bisa langsung pakai milik Barang)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return nama;
    }

    public void setName(String nama) {
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

    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}
