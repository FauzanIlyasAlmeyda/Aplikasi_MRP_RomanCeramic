package com.kelompok5.kelompok5app.model;

public class Order {
    private String idOrder;
    private String idBarang;
    private int jumlah;
    private String waktuOrder;

    public Order(String idOrder, String idBarang, int jumlah, String waktuOrder) {
        this.idOrder = idOrder;
        this.idBarang = idBarang;
        this.jumlah = jumlah;
        this.waktuOrder = waktuOrder;
    }

    // Getters and Setters
    public String getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(String idOrder) {
        this.idOrder = idOrder;
    }

    public String getIdBarang() {
        return idBarang;
    }

    public void setIdBarang(String idBarang) {
        this.idBarang = idBarang;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public String getWaktuOrder() {
        return waktuOrder;
    }

    public void setWaktuOrder(String waktuOrder) {
        this.waktuOrder = waktuOrder;
    }
}
