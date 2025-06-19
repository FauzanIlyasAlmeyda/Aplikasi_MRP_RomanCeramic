package com.kelompok5.kelompok5app.view;

import com.kelompok5.kelompok5app.controller.LaporanListController;

public class DetailLaporanBarang extends javax.swing.JFrame {

    private int idLaporan;

    public DetailLaporanBarang(int idLaporan) {
        initComponents();

        this.idLaporan = idLaporan;

        // Panggil controller untuk tampilkan data
        LaporanListController controller = new LaporanListController();
        controller.tampilkanDetailLaporan(idLaporan, TabelDetailLaporan);

        String namaLaporan = controller.getNamaLaporanById(idLaporan);
        jLabelJudul.setText("Detail Laporan: " + namaLaporan);
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        bg = new javax.swing.JPanel();
        Header = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        PanelKembali = new javax.swing.JPanel();
        BtnKembaliDetailLaporan = new javax.swing.JButton();
        BottomPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TabelDetailLaporan = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Detail Laporan");

        bg.setBackground(new java.awt.Color(153, 153, 153));
        bg.setLayout(new java.awt.BorderLayout());

        Header.setOpaque(false);
        Header.setPreferredSize(new java.awt.Dimension(800, 75));
        Header.setLayout(new java.awt.BorderLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24));
        jLabel1.setText("Detail Laporan");
        jLabel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));
        Header.add(jLabel1, java.awt.BorderLayout.CENTER);

        PanelKembali.setOpaque(false);
        PanelKembali.setPreferredSize(new java.awt.Dimension(150, 75));
        PanelKembali.setLayout(new java.awt.GridBagLayout());

        BtnKembaliDetailLaporan.setBackground(new java.awt.Color(0, 0, 0));
        BtnKembaliDetailLaporan.setFont(new java.awt.Font("Segoe UI", 1, 14));
        BtnKembaliDetailLaporan.setForeground(new java.awt.Color(255, 255, 255));
        BtnKembaliDetailLaporan.setText("Kembali");
        BtnKembaliDetailLaporan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKembaliDetailLaporanActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        PanelKembali.add(BtnKembaliDetailLaporan, gridBagConstraints);

        Header.add(PanelKembali, java.awt.BorderLayout.EAST);

        bg.add(Header, java.awt.BorderLayout.PAGE_START);

        BottomPanel.setOpaque(false);
        BottomPanel.setLayout(new java.awt.BorderLayout());

        jLabelJudul = new javax.swing.JLabel();
        jLabelJudul.setFont(new java.awt.Font("Segoe UI", 1, 20));
        jLabelJudul.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelJudul.setText("Detail Laporan");
        BottomPanel.add(jLabelJudul, java.awt.BorderLayout.NORTH);

        TabelDetailLaporan.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][] {},
                new String[] {
                        "Kode Barang", "Nama Barang", "Min Stok", "Maks Stok", "Stok Tersedia",
                        "Order", "Vendor", "Waktu", "Penambahan Stok"
                }) {
            Class[] types = new Class[] {
                    java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class,
                    java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class,
                    java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        });
        TabelDetailLaporan.setShowGrid(true);
        jScrollPane1.setViewportView(TabelDetailLaporan);

        BottomPanel.add(jScrollPane1, java.awt.BorderLayout.CENTER);
        bg.add(BottomPanel, java.awt.BorderLayout.CENTER);

        getContentPane().add(bg, java.awt.BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(null); // agar window muncul di tengah
    }

    private void BtnKembaliDetailLaporanActionPerformed(java.awt.event.ActionEvent evt) {
        new Laporan().setVisible(true);
        dispose();
    }

    // Variables declaration
    private javax.swing.JPanel BottomPanel;
    private javax.swing.JLabel jLabelJudul;
    private javax.swing.JButton BtnKembaliDetailLaporan;
    private javax.swing.JPanel Header;
    private javax.swing.JPanel PanelKembali;
    private javax.swing.JTable TabelDetailLaporan;
    private javax.swing.JPanel bg;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
}
