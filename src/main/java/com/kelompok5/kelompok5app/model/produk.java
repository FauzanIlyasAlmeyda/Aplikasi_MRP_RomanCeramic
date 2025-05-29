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
    protected String id;
    protected String name;
    protected int stock;
    protected String kategori;
    protected List<materialProduk> materialList;
    
    public produk(String id, String name, int stock, String kategori, List<materialProduk> materialList) {
        this.id = id;
        this.name = name;
        this.stock = stock;
        this.kategori = kategori;
        this.materialList = materialList;
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
    public int getStock() {
        return stock;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }
    public String getKategori() {
        return kategori;
    }
    public void setKategori(String kategori) {
        this.kategori = kategori;
    }
    public List<materialProduk> getMaterialList() {
        return materialList;
    }
    public void setMaterialList(List<materialProduk> materialList) {
        this.materialList = materialList;
    }
}
