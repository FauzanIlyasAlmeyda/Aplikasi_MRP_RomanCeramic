package com.kelompok5.kelompok5app.databaseAcces;

import com.kelompok5.kelompok5app.config.databaseConnection;
import com.kelompok5.kelompok5app.model.Order;

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

    //Insert order dan update stok barang
    public boolean insert(Order order) {
        String insertSql = "INSERT INTO order_barang (id_order, id_barang, jumlah, waktu_order) VALUES (?, ?, ?, ?)";
        String updateBarangSql = "UPDATE barang SET stock = stock - ?, waktu_order_terakhir = ? WHERE id = ?";

        try {
            conn.setAutoCommit(false); // transaksi

            // Insert ke order_barang
            PreparedStatement insertStmt = conn.prepareStatement(insertSql);
            insertStmt.setString(1, order.getIdOrder());
            insertStmt.setString(2, order.getIdBarang());
            insertStmt.setInt(3, order.getJumlah());
            insertStmt.setString(4, order.getWaktuOrder());
            insertStmt.executeUpdate();

            // Update stok dan waktu_order_terakhir
            PreparedStatement updateStmt = conn.prepareStatement(updateBarangSql);
            updateStmt.setInt(1, order.getJumlah());
            updateStmt.setString(2, order.getWaktuOrder());
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

    // riwayat order
    public List<Order> getAll() {
        List<Order> list = new ArrayList<>();
        String sql = "SELECT * FROM order_barang ORDER BY waktu_order DESC";

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Order ob = new Order(
                    rs.getString("id_order"),
                    rs.getString("id_barang"),
                    rs.getInt("jumlah"),
                    rs.getString("waktu_order")
                );
                list.add(ob);
            }

        } catch (SQLException e) {
            System.err.println("Gagal ambil data order: " + e.getMessage());
        }

        return list;
    }
}
