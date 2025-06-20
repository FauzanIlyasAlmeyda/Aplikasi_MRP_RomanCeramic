package com.kelompok5.kelompok5app.model;

public class Order_ {
    private String idOrder;
    private String idBarang;
    private int jumlah;
    private String waktuOrder;
    private String tipe;

    public Order_(String idOrder, String idBarang, int jumlah, String waktuOrder) {
        this.idOrder = idOrder;
        this.idBarang = idBarang;
        this.jumlah = jumlah;
        this.waktuOrder = waktuOrder;
    }

    public Order_(String idOrder, String idBarang, int jumlah, String waktuOrder, String tipe) {
        this.idOrder = idOrder;
        this.idBarang = idBarang;
        this.jumlah = jumlah;
        this.waktuOrder = waktuOrder;
        this.tipe = tipe;
    }

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }

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
