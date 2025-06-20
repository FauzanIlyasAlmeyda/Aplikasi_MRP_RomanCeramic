package com.kelompok5.kelompok5app.databaseAcces;

import com.kelompok5.kelompok5app.config.databaseConnection;
import com.kelompok5.kelompok5app.model.Order_;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderCRUD {
    private Connection conn;

    public OrderCRUD() {
        conn = databaseConnection.getConnection();
    }

    // Generate ID
    public String generateId() {
        String prefix = "ORD";
        String sql = "SELECT id_order FROM order_barang ORDER BY id_order DESC LIMIT 1";
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                String lastId = rs.getString("id_order");
                int number = Integer.parseInt(lastId.replace(prefix, ""));
                return prefix + String.format("%03d", number + 1);
            }
        } catch (SQLException e) {
            System.err.println("Gagal generate ID order: " + e.getMessage());
        }
        return prefix + "001";
    }

    // Insert order dan update stok barang
    // Insert order dan update stok barang
    public boolean insert(Order_ order) {
        if (order.getJumlah() <= 0) {
            System.err.println("❌ Jumlah order tidak boleh nol atau negatif.");
            return false;
        }

        String insertSql = "INSERT INTO order_barang (id_order, id_barang, jumlah, waktu_order,tipe) VALUES (?, ?, ?, ?, ?)";
        String getBarangSql = "SELECT stock, min_stock, `order`, waktu_butuh_order FROM barang WHERE id = ?";
        String updateBarangSql = "UPDATE barang SET stock = ?, `order` = ?, waktu_butuh_order = ? WHERE id = ?";

        try {
            conn.setAutoCommit(false); // Mulai transaksi

            // Ambil data barang
            PreparedStatement getStmt = conn.prepareStatement(getBarangSql);
            getStmt.setString(1, order.getIdBarang());
            ResultSet rs = getStmt.executeQuery();

            if (!rs.next()) {
                System.err.println("❌ Barang tidak ditemukan.");
                conn.rollback();
                return false;
            }

            int stock = rs.getInt("stock");
            int minStock = rs.getInt("min_stock");
            int existingOrder = rs.getInt("order");

            int newStock = stock - order.getJumlah();

            if (newStock < 0) {
                System.err.println("❌ Stok tidak mencukupi. Order dibatalkan.");
                conn.rollback();
                return false;
            }

            int newOrderValue = existingOrder;
            Timestamp waktuButuhOrder = rs.getTimestamp("waktu_butuh_order");

            // Logika utama update order & waktu
            if (newStock < minStock) {
                newOrderValue = existingOrder + order.getJumlah();
                waktuButuhOrder = Timestamp.valueOf(order.getWaktuOrder());
            } else {
                newOrderValue = 0;
                waktuButuhOrder = null;
            }

            // Insert order
            if (newStock < minStock) {
                PreparedStatement insertStmt = conn.prepareStatement(insertSql);
                insertStmt.setString(1, order.getIdOrder());
                insertStmt.setString(2, order.getIdBarang());
                insertStmt.setInt(3, order.getJumlah());
                insertStmt.setString(4, order.getWaktuOrder());
                insertStmt.setString(5, "barang");
                insertStmt.executeUpdate();
            }

            // Update barang
            PreparedStatement updateStmt = conn.prepareStatement(updateBarangSql);
            updateStmt.setInt(1, newStock);
            updateStmt.setInt(2, newOrderValue);
            if (waktuButuhOrder != null) {
                updateStmt.setTimestamp(3, waktuButuhOrder);
            } else {
                updateStmt.setNull(3, Types.TIMESTAMP);
            }
            updateStmt.setString(4, order.getIdBarang());
            updateStmt.executeUpdate();

            conn.commit();
            return true;

        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                System.err.println("Rollback gagal: " + ex.getMessage());
            }
            System.err.println("Gagal insert order: " + e.getMessage());
            return false;
        } finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                System.err.println("Gagal reset autocommit: " + e.getMessage());
            }
        }
    }

    
    public List<Order_> getAll() {
        List<Order_> list = new ArrayList<>();
        String sql = "SELECT * FROM order_barang ORDER BY waktu_order DESC";

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Order_ ob = new Order_(
                        rs.getString("id_order"),
                        rs.getString("id_barang"),
                        rs.getInt("jumlah"),
                        rs.getString("waktu_order"));
                list.add(ob);
            }

        } catch (SQLException e) {
            System.err.println("Gagal ambil data order: " + e.getMessage());
        }

        return list;
    }

    public boolean insertProduk(Order_ order) {
        if (order.getJumlah() <= 0) {
            System.err.println("❌ Jumlah order tidak boleh nol atau negatif.");
            return false;
        }

        String insertSql = "INSERT INTO order_barang (id_order, id_barang, jumlah, waktu_order, tipe) VALUES (?, ?, ?, ?, ?)";
        String getProdukSql = "SELECT stock, `order` FROM produk WHERE id = ?";
        String updateProdukSql = "UPDATE produk SET stock = ?, `order` = ? WHERE id = ?";

        try {
            conn.setAutoCommit(false); 

            
            PreparedStatement getStmt = conn.prepareStatement(getProdukSql);
            getStmt.setString(1, order.getIdBarang());
            ResultSet rs = getStmt.executeQuery();

            if (!rs.next()) {
                System.err.println("❌ Produk tidak ditemukan.");
                conn.rollback();
                return false;
            }

            int stock = rs.getInt("stock");
            int existingOrder = rs.getInt("order");

            int newStock = stock - order.getJumlah();

            if (newStock < 0) {
                System.err.println("❌ Jumlah order melebihi stok produk yang tersedia.");
                conn.rollback();
                return false;
            }

            int newOrder = (newStock <= 0) ? existingOrder + order.getJumlah() : 0;

            
            PreparedStatement insertStmt = conn.prepareStatement(insertSql);
            insertStmt.setString(1, order.getIdOrder());
            insertStmt.setString(2, order.getIdBarang());
            insertStmt.setInt(3, order.getJumlah());
            insertStmt.setString(4, order.getWaktuOrder());
            insertStmt.setString(5, "produk"); 
            insertStmt.executeUpdate();

            
            PreparedStatement updateStmt = conn.prepareStatement(updateProdukSql);
            updateStmt.setInt(1, newStock);
            updateStmt.setInt(2, newOrder);
            updateStmt.setString(3, order.getIdBarang());
            updateStmt.executeUpdate();

            conn.commit();
            return true;

        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                System.err.println("Rollback gagal: " + ex.getMessage());
            }
            System.err.println("Gagal insert order produk: " + e.getMessage());
            return false;
        } finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                System.err.println("Gagal reset autocommit: " + e.getMessage());
            }
        }
    }

}