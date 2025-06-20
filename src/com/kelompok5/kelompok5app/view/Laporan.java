package com.kelompok5.kelompok5app.view;

import com.kelompok5.kelompok5app.controller.LaporanListController;

/**
 *
 * @author LENOVO
 */
public class Laporan extends javax.swing.JFrame {

    private LaporanListController controller;

    /**
     * Creates new form Laporan
     */
    public Laporan() {
        initComponents();
        controller = new LaporanListController();
        controller.tampilkanListLaporan(TabelLaporan);

        TabelLaporan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = TabelLaporan.rowAtPoint(evt.getPoint());
                int col = TabelLaporan.columnAtPoint(evt.getPoint());

                if (evt.getClickCount() == 2 && col == 2) {
                    int idLaporan = controller.getIdLaporanByRow(row);
                    new DetailLaporanBarang(idLaporan).setVisible(true);
                    dispose();
                } else if (col == 3) { // kolom "Hapus"
                    int confirm = javax.swing.JOptionPane.showConfirmDialog(null, "Hapus laporan ini?", "Konfirmasi",
                            javax.swing.JOptionPane.YES_NO_OPTION);
                    if (confirm == javax.swing.JOptionPane.YES_OPTION) {
                        boolean berhasil = controller.hapusLaporan(row);
                        if (berhasil) {
                            javax.swing.JOptionPane.showMessageDialog(null, "✅ Laporan berhasil dihapus!");
                            controller.tampilkanListLaporan(TabelLaporan); // refresh table
                        } else {
                            javax.swing.JOptionPane.showMessageDialog(null, "❌ Gagal menghapus laporan!");
                        }
                    }
                }
            }
        });

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        bg = new javax.swing.JPanel();
        Header = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        PanelKembali = new javax.swing.JPanel();
        BtnKembaliLaporan = new javax.swing.JButton();
        BottomPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TabelLaporan = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        bg.setBackground(new java.awt.Color(153, 153, 153));
        bg.setLayout(new java.awt.BorderLayout());

        Header.setOpaque(false);
        Header.setPreferredSize(new java.awt.Dimension(800, 75));
        Header.setLayout(new java.awt.BorderLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("List Laporan");
        jLabel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));
        Header.add(jLabel1, java.awt.BorderLayout.CENTER);

        PanelKembali.setOpaque(false);
        PanelKembali.setPreferredSize(new java.awt.Dimension(150, 75));
        PanelKembali.setLayout(new java.awt.GridBagLayout());

        BtnKembaliLaporan.setBackground(new java.awt.Color(0, 0, 0));
        BtnKembaliLaporan.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        BtnKembaliLaporan.setForeground(new java.awt.Color(255, 255, 255));
        BtnKembaliLaporan.setText("Kembali");
        BtnKembaliLaporan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKembaliLaporanActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        PanelKembali.add(BtnKembaliLaporan, gridBagConstraints);

        Header.add(PanelKembali, java.awt.BorderLayout.EAST);

        bg.add(Header, java.awt.BorderLayout.PAGE_START);

        BottomPanel.setLayout(new java.awt.BorderLayout());

        TabelLaporan.setModel(new javax.swing.table.DefaultTableModel(
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
                        "Nama Laporan", "Waktu", "Aksi", "Hapus"
                }) {
            Class[] types = new Class[] {
                    java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        });
        TabelLaporan.setShowGrid(true);
        jScrollPane1.setViewportView(TabelLaporan);

        BottomPanel.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        bg.add(BottomPanel, java.awt.BorderLayout.CENTER);

        getContentPane().add(bg, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnKembaliLaporanActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_BtnKembaliLaporanActionPerformed
        new DashboardDepartemenGudang().setVisible(true);
        dispose();
    }// GEN-LAST:event_BtnKembaliLaporanActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Laporan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel BottomPanel;
    private javax.swing.JButton BtnKembaliLaporan;
    private javax.swing.JPanel Header;
    private javax.swing.JPanel PanelKembali;
    private javax.swing.JTable TabelLaporan;
    private javax.swing.JPanel bg;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
