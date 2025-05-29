package com.kelompok5.kelompok5app.model;
public class material {
    protected String id;
    protected String name;
    protected String kategori;
    protected int min_stock;
    protected int max_stock;
    protected int stock;
    protected String vendor;
    protected double harga;

    public material(String id, String name, String kategori, int min_stock, int max_stock, int stock, String vendor, double harga) {
        this.id = id;
        this.name = name;
        this.kategori = kategori;
        this.min_stock = min_stock;
        this.max_stock = max_stock;
        this.stock = stock;
        this.vendor = vendor;
        this.harga = harga;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
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


}
