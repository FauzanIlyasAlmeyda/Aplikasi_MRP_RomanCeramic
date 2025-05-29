/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kelompok5.kelompok5app.model;

/**
 *
 * @author ASUS
 */
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
    
    public String getid(){
        return id;
    }
    public void setid(){
        this.id=id;
    }
    public String getname(){
        return name;
    }
    public void setname(){
        this.name = name;
    }
    public String getkategori(){
        return kategori;
    }
    
}
