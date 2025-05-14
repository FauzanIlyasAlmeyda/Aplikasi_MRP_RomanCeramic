/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kelompok5.kelompok5app.model;

/**
 *
 * @author ASUS
 */
public class materialProduk {
    protected int id;
    protected String idProduk;
    protected material material;
    protected int jumlah;

    public materialProduk(int id, String idProduk, material material, int jumlah) {
        this.id = id;
        this.idProduk = idProduk;
        this.material = material;
        this.jumlah = jumlah;
    }
}
