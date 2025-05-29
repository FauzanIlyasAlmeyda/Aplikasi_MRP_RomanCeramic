/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kelompok5.kelompok5app.model;


public class materialProduk {
    protected String id;
    protected String idProduk;
    protected material material;
    protected int jumlah;

    public materialProduk(String id, String idProduk, material material, int jumlah) {
        this.id = id;
        this.idProduk = idProduk;
        this.material = material;
        this.jumlah = jumlah;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getIdProduk() {
        return idProduk;
    }
    public void setIdProduk(String idProduk) {
        this.idProduk = idProduk;
    }
    public material getMaterial() {
        return material;
    }
    public void setMaterial(material material) {
        this.material = material;
    }
    public int getJumlah() {
        return jumlah;
    }
    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }
}
