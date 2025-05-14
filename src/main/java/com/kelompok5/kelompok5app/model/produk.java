/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kelompok5.kelompok5app.model;
import java.util.List;



/**
 *
 * @author ASUS
 */
public class produk {
    protected int id;
    protected String name;
    protected int stock;
    protected String kategori;
    protected List<materialProduk> materialList;
    
    public produk(int id, String name, int stock, String kategori, List<materialProduk> materialList) {
        this.id = id;
        this.name = name;
        this.stock = stock;
        this.kategori = kategori;
        this.materialList = materialList;
    }
}
