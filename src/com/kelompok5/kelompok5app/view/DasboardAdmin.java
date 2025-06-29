/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.kelompok5.kelompok5app.view;

import com.kelompok5.kelompok5app.controller.BarangController;
import com.kelompok5.kelompok5app.controller.ProdukController;

import com.kelompok5.kelompok5app.model.Barang;
import com.kelompok5.kelompok5app.model.Material;
import com.kelompok5.kelompok5app.model.Produk;
import com.kelompok5.kelompok5app.model.Materialproduk;

import java.awt.CardLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author LENOVO
 */
public class DasboardAdmin extends javax.swing.JFrame {

    /**
     * Creates new form DasboardAdminW
     */
    private BarangController barangController;
    private ProdukController produkController;

    public DasboardAdmin() {
        initComponents();
        barangController = new BarangController(); // tanpa tipe di awal
        produkController = new ProdukController();

        barangController.tampilkanSemuaBarang(TabelBarang);
        produkController.tampilkanSemuaProduk(TabelProduk);
        // Set Layout untuk CardPanel menjadi CardLayout
        CardPanel.setLayout(new java.awt.CardLayout());

        // Menambahkan CardTabelBarang ke CardPanel dengan ID "card1" untuk tabel barang
        CardPanel.add(CardTabelBarang, "card1");

        // Menambahkan CardTabelProduk ke CardPanel dengan ID "card3" untuk tabel produk
        CardPanel.add(CardTabelProduk, "card2");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        bg = new javax.swing.JPanel();
        header = new javax.swing.JPanel();
        textadm = new javax.swing.JLabel();
        BtnPanel = new javax.swing.JPanel();
        BtnProduk = new javax.swing.JButton();
        BtnBarang = new javax.swing.JButton();
        Bottompanel = new javax.swing.JPanel();
        CardPanel = new javax.swing.JPanel();
        CardTabelProduk = new javax.swing.JPanel();
        ScrollTabelProduk = new javax.swing.JScrollPane();
        TabelProduk = new javax.swing.JTable();
        navbarProduk = new javax.swing.JPanel();
        navbarContainerP = new javax.swing.JPanel();
        BtnAddP = new javax.swing.JButton();
        BtnEditP = new javax.swing.JButton();
        BtnDelP = new javax.swing.JButton();
        BtnLogoutP = new javax.swing.JButton();
        txtJudulProduk = new javax.swing.JLabel();
        CardTabelBarang = new javax.swing.JPanel();
        navbarBarang = new javax.swing.JPanel();
        navbarContainerB = new javax.swing.JPanel();
        BtnAddB = new javax.swing.JButton();
        BtnEditB = new javax.swing.JButton();
        BtnDelB = new javax.swing.JButton();
        BtnLogoutB = new javax.swing.JButton();
        txtJudulBarang = new javax.swing.JLabel();
        ScrollTabelBarang = new javax.swing.JScrollPane();
        TabelBarang = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(800, 500));

        bg.setBackground(new java.awt.Color(153, 153, 153));
        bg.setMinimumSize(new java.awt.Dimension(800, 500));
        bg.setPreferredSize(new java.awt.Dimension(800, 500));
        bg.setLayout(new java.awt.BorderLayout());

        header.setMinimumSize(new java.awt.Dimension(800, 75));
        header.setOpaque(false);
        header.setPreferredSize(new java.awt.Dimension(800, 75));
        header.setLayout(new java.awt.BorderLayout());

        textadm.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        textadm.setText("Admin");
        textadm.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));
        header.add(textadm, java.awt.BorderLayout.WEST);

        BtnPanel.setMinimumSize(new java.awt.Dimension(215, 30));
        BtnPanel.setOpaque(false);
        BtnPanel.setPreferredSize(new java.awt.Dimension(215, 30));
        BtnPanel.setLayout(new java.awt.GridBagLayout());

        BtnProduk.setBackground(new java.awt.Color(0, 0, 0));
        BtnProduk.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        BtnProduk.setForeground(new java.awt.Color(255, 255, 255));
        BtnProduk.setText("Produk");
        BtnProduk.setMinimumSize(new java.awt.Dimension(85, 30));
        BtnProduk.setPreferredSize(new java.awt.Dimension(85, 30));
        BtnProduk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnProdukActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        BtnPanel.add(BtnProduk, gridBagConstraints);

        BtnBarang.setBackground(new java.awt.Color(0, 0, 0));
        BtnBarang.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        BtnBarang.setForeground(new java.awt.Color(255, 255, 255));
        BtnBarang.setText("Barang");
        BtnBarang.setMinimumSize(new java.awt.Dimension(85, 30));
        BtnBarang.setPreferredSize(new java.awt.Dimension(85, 30));
        BtnBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBarangActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        BtnPanel.add(BtnBarang, gridBagConstraints);

        header.add(BtnPanel, java.awt.BorderLayout.EAST);

        bg.add(header, java.awt.BorderLayout.PAGE_START);

        Bottompanel.setLayout(new java.awt.BorderLayout());

        CardPanel.setMinimumSize(new java.awt.Dimension(800, 425));
        CardPanel.setOpaque(false);
        CardPanel.setPreferredSize(new java.awt.Dimension(800, 425));
        CardPanel.setLayout(new java.awt.CardLayout());

        CardTabelProduk.setMinimumSize(new java.awt.Dimension(800, 425));
        CardTabelProduk.setPreferredSize(new java.awt.Dimension(800, 425));
        CardTabelProduk.setLayout(new java.awt.BorderLayout());

        ScrollTabelProduk.setMinimumSize(new java.awt.Dimension(800, 425));
        ScrollTabelProduk.setPreferredSize(new java.awt.Dimension(800, 425));

        TabelProduk.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][] {
                        { null, null, null, null },
                        { null, null, null, null },
                        { null, null, null, null },
                        { null, null, null, null },
                        { null, null, null, null },
                        { null, null, null, null },
                        { null, null, null, null },
                        { null, null, null, null },
                        { null, null, null, null },
                        { null, null, null, null },
                        { null, null, null, null },
                        { null, null, null, null },
                        { null, null, null, null },
                        { null, null, null, null },
                        { null, null, null, null },
                        { null, null, null, null },
                        { null, null, null, null },
                        { null, null, null, null },
                        { null, null, null, null },
                        { null, null, null, null },
                        { null, null, null, null },
                        { null, null, null, null },
                        { null, null, null, null },
                        { null, null, null, null },
                        { null, null, null, null }
                },
                new String[] {
                        "Id_Produk", "Nama_Produk", "Material_Produk", "Stok"
                }) {
            Class[] types = new Class[] {
                    java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        });
        TabelProduk.setMinimumSize(new java.awt.Dimension(800, 425));
        TabelProduk.setPreferredSize(new java.awt.Dimension(800, 425));
        TabelProduk.setShowGrid(true);
        ScrollTabelProduk.setViewportView(TabelProduk);

        CardTabelProduk.add(ScrollTabelProduk, java.awt.BorderLayout.CENTER);

        navbarProduk.setMinimumSize(new java.awt.Dimension(800, 75));
        navbarProduk.setPreferredSize(new java.awt.Dimension(500, 75));
        navbarProduk.setLayout(new java.awt.BorderLayout());

        navbarContainerP.setLayout(new java.awt.GridBagLayout());

        BtnAddP.setBackground(new java.awt.Color(0, 0, 0));
        BtnAddP.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        BtnAddP.setForeground(new java.awt.Color(255, 255, 255));
        BtnAddP.setText("Tambah");
        BtnAddP.setMinimumSize(new java.awt.Dimension(85, 30));
        BtnAddP.setPreferredSize(new java.awt.Dimension(85, 30));
        BtnAddP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAddPActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        navbarContainerP.add(BtnAddP, gridBagConstraints);

        BtnEditP.setBackground(new java.awt.Color(0, 0, 0));
        BtnEditP.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        BtnEditP.setForeground(new java.awt.Color(255, 255, 255));
        BtnEditP.setText("Edit");
        BtnEditP.setMinimumSize(new java.awt.Dimension(85, 30));
        BtnEditP.setPreferredSize(new java.awt.Dimension(85, 30));
        BtnEditP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEditPActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        navbarContainerP.add(BtnEditP, gridBagConstraints);

        BtnDelP.setBackground(new java.awt.Color(0, 0, 0));
        BtnDelP.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        BtnDelP.setForeground(new java.awt.Color(255, 255, 255));
        BtnDelP.setText("Hapus");
        BtnDelP.setMinimumSize(new java.awt.Dimension(85, 30));
        BtnDelP.setPreferredSize(new java.awt.Dimension(85, 30));
        BtnDelP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDelPActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        navbarContainerP.add(BtnDelP, gridBagConstraints);

        BtnLogoutP.setBackground(new java.awt.Color(0, 0, 0));
        BtnLogoutP.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        BtnLogoutP.setForeground(new java.awt.Color(255, 255, 255));
        BtnLogoutP.setText("Keluar");
        BtnLogoutP.setMinimumSize(new java.awt.Dimension(85, 30));
        BtnLogoutP.setPreferredSize(new java.awt.Dimension(85, 30));
        BtnLogoutP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnLogoutPActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        navbarContainerP.add(BtnLogoutP, gridBagConstraints);

        navbarProduk.add(navbarContainerP, java.awt.BorderLayout.EAST);

        txtJudulProduk.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        txtJudulProduk.setText("Tabel Produk");
        txtJudulProduk.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));
        navbarProduk.add(txtJudulProduk, java.awt.BorderLayout.CENTER);

        CardTabelProduk.add(navbarProduk, java.awt.BorderLayout.SOUTH);

        CardPanel.add(CardTabelProduk, "card2");

        CardTabelBarang.setMinimumSize(new java.awt.Dimension(800, 425));
        CardTabelBarang.setPreferredSize(new java.awt.Dimension(800, 425));
        CardTabelBarang.setLayout(new java.awt.BorderLayout());

        navbarBarang.setMinimumSize(new java.awt.Dimension(800, 75));
        navbarBarang.setPreferredSize(new java.awt.Dimension(500, 75));
        navbarBarang.setLayout(new java.awt.BorderLayout());

        navbarContainerB.setLayout(new java.awt.GridBagLayout());

        BtnAddB.setBackground(new java.awt.Color(0, 0, 0));
        BtnAddB.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        BtnAddB.setForeground(new java.awt.Color(255, 255, 255));
        BtnAddB.setText("Tambah");
        BtnAddB.setMinimumSize(new java.awt.Dimension(85, 30));
        BtnAddB.setPreferredSize(new java.awt.Dimension(85, 30));
        BtnAddB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAddBActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        navbarContainerB.add(BtnAddB, gridBagConstraints);

        BtnEditB.setBackground(new java.awt.Color(0, 0, 0));
        BtnEditB.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        BtnEditB.setForeground(new java.awt.Color(255, 255, 255));
        BtnEditB.setText("Edit");
        BtnEditB.setMinimumSize(new java.awt.Dimension(85, 30));
        BtnEditB.setPreferredSize(new java.awt.Dimension(85, 30));
        BtnEditB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEditBActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        navbarContainerB.add(BtnEditB, gridBagConstraints);

        BtnDelB.setBackground(new java.awt.Color(0, 0, 0));
        BtnDelB.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        BtnDelB.setForeground(new java.awt.Color(255, 255, 255));
        BtnDelB.setText("Hapus");
        BtnDelB.setMinimumSize(new java.awt.Dimension(85, 30));
        BtnDelB.setPreferredSize(new java.awt.Dimension(85, 30));
        BtnDelB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDelBActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        navbarContainerB.add(BtnDelB, gridBagConstraints);

        BtnLogoutB.setBackground(new java.awt.Color(0, 0, 0));
        BtnLogoutB.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        BtnLogoutB.setForeground(new java.awt.Color(255, 255, 255));
        BtnLogoutB.setText("Keluar");
        BtnLogoutB.setMinimumSize(new java.awt.Dimension(85, 30));
        BtnLogoutB.setPreferredSize(new java.awt.Dimension(85, 30));
        BtnLogoutB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnLogoutBActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        navbarContainerB.add(BtnLogoutB, gridBagConstraints);

        navbarBarang.add(navbarContainerB, java.awt.BorderLayout.EAST);

        txtJudulBarang.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        txtJudulBarang.setText("Tabel Barang");
        txtJudulBarang.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));
        navbarBarang.add(txtJudulBarang, java.awt.BorderLayout.CENTER);

        CardTabelBarang.add(navbarBarang, java.awt.BorderLayout.SOUTH);

        ScrollTabelBarang.setMinimumSize(new java.awt.Dimension(800, 425));
        ScrollTabelBarang.setPreferredSize(new java.awt.Dimension(800, 425));

        TabelBarang.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][] {
                        { null, null, null, null, null, null, null, null, null },
                        { null, null, null, null, null, null, null, null, null },
                        { null, null, null, null, null, null, null, null, null },
                        { null, null, null, null, null, null, null, null, null },
                        { null, null, null, null, null, null, null, null, null },
                        { null, null, null, null, null, null, null, null, null },
                        { null, null, null, null, null, null, null, null, null },
                        { null, null, null, null, null, null, null, null, null },
                        { null, null, null, null, null, null, null, null, null },
                        { null, null, null, null, null, null, null, null, null },
                        { null, null, null, null, null, null, null, null, null },
                        { null, null, null, null, null, null, null, null, null },
                        { null, null, null, null, null, null, null, null, null },
                        { null, null, null, null, null, null, null, null, null },
                        { null, null, null, null, null, null, null, null, null },
                        { null, null, null, null, null, null, null, null, null },
                        { null, null, null, null, null, null, null, null, null },
                        { null, null, null, null, null, null, null, null, null },
                        { null, null, null, null, null, null, null, null, null },
                        { null, null, null, null, null, null, null, null, null },
                        { null, null, null, null, null, null, null, null, null },
                        { null, null, null, null, null, null, null, null, null },
                        { null, null, null, null, null, null, null, null, null },
                        { null, null, null, null, null, null, null, null, null },
                        { null, null, null, null, null, null, null, null, null }
                },
                new String[] {
                        "Kode barang", "Nama Barang", "Kategori", "Min Stok", "Maks Stok", "Stok", "Order", "Vendor",
                        "Tanggal Diperbarui"
                }) {
            Class[] types = new Class[] {
                    java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class,
                    java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class,
                    java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        });
        TabelBarang.setMinimumSize(new java.awt.Dimension(800, 425));
        TabelBarang.setPreferredSize(new java.awt.Dimension(800, 425));
        TabelBarang.setShowHorizontalLines(true);
        TabelBarang.setShowVerticalLines(true);
        ScrollTabelBarang.setViewportView(TabelBarang);

        CardTabelBarang.add(ScrollTabelBarang, java.awt.BorderLayout.CENTER);

        CardPanel.add(CardTabelBarang, "card1");

        Bottompanel.add(CardPanel, java.awt.BorderLayout.CENTER);

        bg.add(Bottompanel, java.awt.BorderLayout.CENTER);

        getContentPane().add(bg, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnDelBActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_BtnDelBActionPerformed
        int selectedRow = TabelBarang.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Pilih baris yang ingin dihapus.", "Peringatan",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        DefaultTableModel model = (DefaultTableModel) TabelBarang.getModel();
        String id = (String) model.getValueAt(selectedRow, 0); // ambil kolom ID

        int konfirmasi = JOptionPane.showConfirmDialog(this, "Yakin ingin menghapus barang dengan ID " + id + "?",
                "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (konfirmasi == JOptionPane.YES_OPTION) {
            boolean sukses = barangController.deleteBarang(id);
            if (sukses) {
                model.removeRow(selectedRow);
                JOptionPane.showMessageDialog(this, "Barang berhasil dihapus.");
            } else {
                JOptionPane.showMessageDialog(this, "Gagal menghapus barang dari database.", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }// GEN-LAST:event_BtnDelBActionPerformed

    private void BtnAddBActionPerformed(java.awt.event.ActionEvent evt) {
        String nama = JOptionPane.showInputDialog(this, "Masukkan Nama Barang/Material:");
        String kategori = JOptionPane.showInputDialog(this, "Masukkan Kategori (barang/material):");
        String minStokStr = JOptionPane.showInputDialog(this, "Masukkan Min Stok:");
        String maxStokStr = JOptionPane.showInputDialog(this, "Masukkan Maks Stok:");
        String stokStr = JOptionPane.showInputDialog(this, "Masukkan Stok:");
        String vendor = JOptionPane.showInputDialog(this, "Masukkan Vendor:");

        if (nama == null || kategori == null || minStokStr == null || maxStokStr == null
                || stokStr == null || vendor == null) {
            JOptionPane.showMessageDialog(this, "Semua kolom wajib diisi.", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            int minStok = Integer.parseInt(minStokStr);
            int maxStok = Integer.parseInt(maxStokStr);
            int stok = Integer.parseInt(stokStr);
            int order = 0;

            // Generate ID berdasarkan kategori
            String id = barangController.generateId(kategori);
            boolean sukses = false;

            if (kategori.equalsIgnoreCase("material")) {
                Material materialBaru = new Material(id, nama, kategori, minStok, maxStok, stok, order, vendor,
                        null);
                sukses = barangController.insertBarang(materialBaru);
            } else {
                Barang barangBaru = new Barang(id, nama, kategori, minStok, maxStok, stok, order, vendor, null);
                sukses = barangController.insertBarang(barangBaru);
            }

            if (sukses) {
                JOptionPane.showMessageDialog(this, "Data berhasil ditambahkan.");
                barangController.tampilkanSemuaBarang(TabelBarang);
            } else {
                JOptionPane.showMessageDialog(this, "Gagal menambahkan ke database.", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Pastikan nilai stok, order, dan harga berupa angka.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void BtnEditBActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_BtnEditBActionPerformed
        int selectedRow = TabelBarang.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Pilih baris yang ingin diedit.", "Peringatan",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        DefaultTableModel model = (DefaultTableModel) TabelBarang.getModel();

        // Ambil data lama
        String id = (String) model.getValueAt(selectedRow, 0);
        String nama = (String) model.getValueAt(selectedRow, 1);
        String kategori = (String) model.getValueAt(selectedRow, 2);
        String minStokStr = model.getValueAt(selectedRow, 3).toString();
        String maksStokStr = model.getValueAt(selectedRow, 4).toString();
        String stokStr = model.getValueAt(selectedRow, 5).toString();
        String orderStr = model.getValueAt(selectedRow, 6).toString();
        String vendor = (String) model.getValueAt(selectedRow, 7);

        // Dialog input baru
        nama = JOptionPane.showInputDialog(this, "Edit Nama Barang:", nama);
        kategori = JOptionPane.showInputDialog(this, "Edit Kategori:", kategori);
        minStokStr = JOptionPane.showInputDialog(this, "Edit Min Stok:", minStokStr);
        maksStokStr = JOptionPane.showInputDialog(this, "Edit Maks Stok:", maksStokStr);
        stokStr = JOptionPane.showInputDialog(this, "Edit Stok:", stokStr);
        vendor = JOptionPane.showInputDialog(this, "Edit Vendor:", vendor);

        try {
            int minStok = Integer.parseInt(minStokStr);
            int maksStok = Integer.parseInt(maksStokStr);
            int stok = Integer.parseInt(stokStr);
            int order = Integer.parseInt(orderStr);

            Barang barangBaru = new Barang(id, nama, kategori, minStok, maksStok, stok, order, vendor, null);
            boolean sukses = barangController.updateBarang(barangBaru);

            if (sukses) {
                JOptionPane.showMessageDialog(this, "Barang berhasil diperbarui.");
                barangController.tampilkanSemuaBarang(TabelBarang); // Refresh isi tabel
            } else {
                JOptionPane.showMessageDialog(this, "Gagal mengupdate ke database.", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Format angka salah!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }// GEN-LAST:event_BtnEditBActionPerformed

    private void BtnProdukActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_BtnProdukActionPerformed
        CardTabelProduk.setVisible(true);
        CardTabelBarang.setVisible(false);
    }// GEN-LAST:event_BtnProdukActionPerformed

    private void BtnBarangActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_BtnBarangActionPerformed
        // TODO add your handling code here:
        CardTabelProduk.setVisible(false);
        CardTabelBarang.setVisible(true);
    }// GEN-LAST:event_BtnBarangActionPerformed

    private void BtnAddPActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_BtnAddPActionPerformed
        try {
            // 1. Generate ID produk
            String id = produkController.generateId();

            // 2. Input nama produk
            String nama = JOptionPane.showInputDialog(this, "Masukkan Nama Produk:");
            if (nama == null || nama.trim().isEmpty()) {
                return;
            }

            // 3. Input stok
            String stokStr = JOptionPane.showInputDialog(this, "Masukkan Stok Produk:");
            if (stokStr == null || stokStr.trim().isEmpty()) {
                return;
            }
            int stok = Integer.parseInt(stokStr.trim());

            // 4. Input material: Format "MTR001:3, MTR002:5"
            String materialInput = JOptionPane.showInputDialog(this, "Masukkan Material (format: ID:jumlah, ...):");
            if (materialInput == null || materialInput.trim().isEmpty()) {
                return;
            }

            List<Materialproduk> materialList = new ArrayList<>();

            String[] items = materialInput.split(",");
            for (String item : items) {
                String[] parts = item.trim().split(":");
                if (parts.length != 2) {
                    throw new Exception("Format material tidak valid: " + item);
                }

                String idMaterial = parts[0].trim();
                int jumlah = Integer.parseInt(parts[1].trim());

                // Buat Material dari ID (hanya ID saja yang dibutuhkan di relasi)
                Material m = new Material(idMaterial, "", "", 0, 0, 0, 0, "", "");

                // Buat relasi produk-material
                Materialproduk mp = new Materialproduk(id + "-" + idMaterial, id, m, jumlah);
                materialList.add(mp);
            }

            // 5. Simpan Produk
            Produk produkBaru = new Produk(id, nama, stok, "produk", materialList);
            boolean sukses = produkController.insertProduk(produkBaru);

            if (sukses) {
                JOptionPane.showMessageDialog(this, "Produk berhasil ditambahkan!");
                produkController.tampilkanSemuaProduk(TabelProduk);
            } else {
                JOptionPane.showMessageDialog(this, "Gagal menambahkan produk ke database.", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Stok harus berupa angka!", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }// GEN-LAST:event_BtnAddPActionPerformed

    private void BtnEditPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditPActionPerformed
        int selectedRow = TabelProduk.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Pilih baris yang ingin diedit.", "Peringatan",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        DefaultTableModel model = (DefaultTableModel) TabelProduk.getModel();

        // Ambil data yang ada
        String idProduk = (String) model.getValueAt(selectedRow, 0);
        String namaLama = (String) model.getValueAt(selectedRow, 1);
        int stokLama = Integer.parseInt(model.getValueAt(selectedRow, 3).toString());

        // Input baru dari pengguna
        String namaBaru = JOptionPane.showInputDialog(this, "Edit Nama Produk:", namaLama);
        if (namaBaru == null || namaBaru.trim().isEmpty()) {
            return;
        }

        String stokStr = JOptionPane.showInputDialog(this, "Edit Stok Produk:", String.valueOf(stokLama));
        if (stokStr == null || stokStr.trim().isEmpty()) {
            return;
        }
        int stokBaru = Integer.parseInt(stokStr.trim());

        String inputMaterialBaru = JOptionPane.showInputDialog(this, "Edit Material (format ID:jumlah, ...):");
        if (inputMaterialBaru == null || inputMaterialBaru.trim().isEmpty()) {
            return;
        }

        try {
            List<Materialproduk> materialBaruList = new ArrayList<>();
            String[] materialSplit = inputMaterialBaru.split(",");

            for (String item : materialSplit) {
                String[] part = item.trim().split(":");
                if (part.length != 2) {
                    throw new Exception("Format material tidak valid: " + item);
                }

                String idMaterial = part[0].trim();
                int jumlah = Integer.parseInt(part[1].trim());

                Material m = new Material(idMaterial, "", "", 0, 0, 0, 0, "", "");
                Materialproduk mp = new Materialproduk(idProduk + "-" + idMaterial, idProduk, m, jumlah);
                materialBaruList.add(mp);
            }

            // Update produk
            Produk produk = new Produk(idProduk, namaBaru, stokBaru, "produk", materialBaruList);

            // Langkah: hapus relasi lama → simpan relasi baru → update produk
            com.kelompok5.kelompok5app.databaseAcces.MaterialprodukCRUD mpDAO = new com.kelompok5.kelompok5app.databaseAcces.MaterialprodukCRUD();
            mpDAO.deleteByProdukId(idProduk); // hapus BOM lama

            boolean suksesSimpan = true;
            for (Materialproduk mp : materialBaruList) {
                if (!mpDAO.insert(mp)) {
                    suksesSimpan = false;
                    break;
                }
            }

            boolean updateProduk = produkController.updateProduk(produk); // kamu harus buat ini kalau belum

            if (suksesSimpan && updateProduk) {
                JOptionPane.showMessageDialog(this, "Produk berhasil diperbarui.");
                produkController.tampilkanSemuaProduk(TabelProduk);
            } else {
                JOptionPane.showMessageDialog(this, "Gagal memperbarui produk.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Stok dan jumlah material harus angka!", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_BtnEditPActionPerformed

    private void BtnDelPActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_BtnDelPActionPerformed
        int selectedRow = TabelProduk.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Pilih baris yang ingin dihapus.", "Peringatan",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        DefaultTableModel model = (DefaultTableModel) TabelProduk.getModel();
        String idProduk = model.getValueAt(selectedRow, 0).toString(); // kolom ID produk

        int konfirmasi = JOptionPane.showConfirmDialog(this,
                "Apakah Anda yakin ingin menghapus produk dengan ID: " + idProduk + "?",
                "Konfirmasi", JOptionPane.YES_NO_OPTION);

        if (konfirmasi == JOptionPane.YES_OPTION) {
            // Gunakan ProduksiController untuk hapus produk + relasinya
            com.kelompok5.kelompok5app.controller.ProduksiController produksiController = new com.kelompok5.kelompok5app.controller.ProduksiController();
            boolean sukses = produksiController.deleteProduk(idProduk);

            if (sukses) {
                JOptionPane.showMessageDialog(this, "Produk berhasil dihapus.");
                produkController.tampilkanSemuaProduk(TabelProduk); // Refresh tabel
            } else {
                JOptionPane.showMessageDialog(this, "Gagal menghapus produk dari database.", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }// GEN-LAST:event_BtnDelPActionPerformed

    private void BtnLogoutPActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_BtnLogoutPActionPerformed
        // TODO add your handling code here:
        new login().setVisible(true);

        // Menutup frame DetailLaporanBarang
        dispose();
    }// GEN-LAST:event_BtnLogoutPActionPerformed

    private void BtnLogoutBActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_BtnLogoutBActionPerformed
        // TODO add your handling code here:
        new login().setVisible(true);

        // Menutup frame DetailLaporanBarang
        dispose();
    }// GEN-LAST:event_BtnLogoutBActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        // <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
        // (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the default
         * look and feel.
         * For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DasboardAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DasboardAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DasboardAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DasboardAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        }
        // </editor-fold>
        // </editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DasboardAdmin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Bottompanel;
    private javax.swing.JButton BtnAddB;
    private javax.swing.JButton BtnAddP;
    private javax.swing.JButton BtnBarang;
    private javax.swing.JButton BtnDelB;
    private javax.swing.JButton BtnDelP;
    private javax.swing.JButton BtnEditB;
    private javax.swing.JButton BtnEditP;
    private javax.swing.JButton BtnLogoutB;
    private javax.swing.JButton BtnLogoutP;
    private javax.swing.JPanel BtnPanel;
    private javax.swing.JButton BtnProduk;
    private javax.swing.JPanel CardPanel;
    private javax.swing.JPanel CardTabelBarang;
    private javax.swing.JPanel CardTabelProduk;
    private javax.swing.JScrollPane ScrollTabelBarang;
    private javax.swing.JScrollPane ScrollTabelProduk;
    private javax.swing.JTable TabelBarang;
    private javax.swing.JTable TabelProduk;
    private javax.swing.JPanel bg;
    private javax.swing.JPanel header;
    private javax.swing.JPanel navbarBarang;
    private javax.swing.JPanel navbarContainerB;
    private javax.swing.JPanel navbarContainerP;
    private javax.swing.JPanel navbarProduk;
    private javax.swing.JLabel textadm;
    private javax.swing.JLabel txtJudulBarang;
    private javax.swing.JLabel txtJudulProduk;
    // End of variables declaration//GEN-END:variables
}
