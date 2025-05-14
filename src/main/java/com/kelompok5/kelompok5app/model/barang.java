/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kelompok5.kelompok5app.model;

/**
 *
 * @author ASUS
 */
public class barang {
    protected int id;
    protected String nama;
    protected String kategori;
    protected int min_stock;
    protected int max_stock;
    protected int stock;
    protected String vendor;
    protected double harga;

    barang(int id, String nama, String kategori, int min_stock, int max_stock, int stock, String vendor, double harga) {
        this.id = id;
        this.nama = nama;
        this.kategori = kategori;
        this.min_stock = min_stock;
        this.max_stock = max_stock;
        this.stock = stock;
        this.vendor = vendor;
        this.harga = harga;
    }

    
}
