
package com.kelompok5.kelompok5app.model;
import java.util.List;

public class Produk extends Barang{
    protected List<Materialproduk> materialList;
    
    public Produk(String id, String nama, int stock, String kategori, List<Materialproduk> materialList) {
        super(id, nama,stock,kategori);
        this.materialList = materialList;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return nama;
    }
    public void setName(String nama) {
        this.nama = nama;
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
    public List<Materialproduk> getMaterialList() {
        return materialList;
    }
    public void setMaterialList(List<Materialproduk> materialList) {
        this.materialList = materialList;
    }
}
