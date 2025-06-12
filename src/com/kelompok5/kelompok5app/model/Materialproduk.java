/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kelompok5.kelompok5app.model;


public class Materialproduk {
    protected String id;
    protected String idProduk;
    protected Material material;
    protected int jumlah;

    public Materialproduk(String id, String idProduk, Material material, int jumlah) {
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
    public Material getMaterial() {
        return material;
    }
    public void setMaterial(Material material) {
        this.material = material;
    }
    public int getJumlah() {
        return jumlah;
    }
    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }
}
