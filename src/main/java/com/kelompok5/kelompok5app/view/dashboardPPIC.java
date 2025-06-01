package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DashboardPPIC extends JFrame {
    private JTable tabelRencanaProduksi;
    private JTable tabelKebutuhanMaterial;
    private DefaultTableModel modelRencanaProduksi;
    private DefaultTableModel modelKebutuhanMaterial;
    private JTextField txtProduk, txtTarget, txtTanggalProduksi;
    private JButton btnTambahRencana, btnHitungKebutuhan, btnSimpanRencana;
    private JLabel lblTotalKebutuhan;
    
    public DashboardPPIC() {
        initComponents();
        setupLayout();
        setupEventHandlers();
    }
    
    private void initComponents() {
        setTitle("Dashboard PPIC - PT Roman Ceramic MRP System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        // Initialize table models
        modelRencanaProduksi = new DefaultTableModel(
            new String[]{"ID", "Produk", "Target Produksi", "Tanggal Produksi", "Status"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        modelKebutuhanMaterial = new DefaultTableModel(
            new String[]{"Material", "Kebutuhan per Unit", "Total Kebutuhan", "Stok Tersedia", "Selisih"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        tabelRencanaProduksi = new JTable(modelRencanaProduksi);
        tabelKebutuhanMaterial = new JTable(modelKebutuhanMaterial);
        
        // Initialize input components
        txtProduk = new JTextField(20);
        txtTarget = new JTextField(10);
        txtTanggalProduksi = new JTextField(15);
        
        btnTambahRencana = new JButton("Tambah Rencana");
        btnHitungKebutuhan = new JButton("Hitung Kebutuhan Material");
        btnSimpanRencana = new JButton("Simpan Rencana Produksi");
        
        lblTotalKebutuhan = new JLabel("Total Item Kebutuhan: 0");
        
        // Set current date as default
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        txtTanggalProduksi.setText(sdf.format(new Date()));
    }
    
    private void setupLayout() {
        setLayout(new BorderLayout());
        
        // Header Panel
        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        headerPanel.setBackground(new Color(41, 128, 185));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        
        JLabel lblTitle = new JLabel("Dashboard PPIC - Production Planning and Inventory Control");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitle.setForeground(Color.WHITE);
        headerPanel.add(lblTitle);
        
        // Main Content Panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Input Form Panel
        JPanel formPanel = createFormPanel();
        
        // Tables Panel
        JPanel tablesPanel = createTablesPanel();
        
        // Button Panel
        JPanel buttonPanel = createButtonPanel();
        
        mainPanel.add(formPanel, BorderLayout.NORTH);
        mainPanel.add(tablesPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        add(headerPanel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);
    }
    
    private JPanel createFormPanel() {
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createTitledBorder("Form Rencana Produksi"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        
        // Row 1
        gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(new JLabel("Produk:"), gbc);
        gbc.gridx = 1;
        formPanel.add(txtProduk, gbc);
        
        gbc.gridx = 2;
        formPanel.add(new JLabel("Target Produksi:"), gbc);
        gbc.gridx = 3;
        formPanel.add(txtTarget, gbc);
        
        gbc.gridx = 4;
        formPanel.add(new JLabel("Tanggal Produksi:"), gbc);
        gbc.gridx = 5;
        formPanel.add(txtTanggalProduksi, gbc);
        
        gbc.gridx = 6;
        formPanel.add(btnTambahRencana, gbc);
        
        return formPanel;
    }
    
    private JPanel createTablesPanel() {
        JPanel tablesPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        
        // Production Plan Table
        JPanel produksiPanel = new JPanel(new BorderLayout());
        produksiPanel.setBorder(BorderFactory.createTitledBorder("Rencana Produksi"));
        
        JScrollPane scrollProduksi = new JScrollPane(tabelRencanaProduksi);
        scrollProduksi.setPreferredSize(new Dimension(0, 200));
        produksiPanel.add(scrollProduksi, BorderLayout.CENTER);
        
        // Material Requirements Table
        JPanel materialPanel = new JPanel(new BorderLayout());
        materialPanel.setBorder(BorderFactory.createTitledBorder("Kebutuhan Material"));
        
        JPanel materialHeaderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        materialHeaderPanel.add(lblTotalKebutuhan);
        materialPanel.add(materialHeaderPanel, BorderLayout.NORTH);
        
        JScrollPane scrollMaterial = new JScrollPane(tabelKebutuhanMaterial);
        scrollMaterial.setPreferredSize(new Dimension(0, 200));
        materialPanel.add(scrollMaterial, BorderLayout.CENTER);
        
        tablesPanel.add(produksiPanel);
        tablesPanel.add(materialPanel);
        
        return tablesPanel;
    }
    
    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        
        btnHitungKebutuhan.setBackground(new Color(39, 174, 96));
        btnHitungKebutuhan.setForeground(Color.WHITE);
        btnHitungKebutuhan.setFont(new Font("Arial", Font.BOLD, 12));
        
        btnSimpanRencana.setBackground(new Color(41, 128, 185));
        btnSimpanRencana.setForeground(Color.WHITE);
        btnSimpanRencana.setFont(new Font("Arial", Font.BOLD, 12));
        
        buttonPanel.add(btnHitungKebutuhan);
        buttonPanel.add(btnSimpanRencana);
        
        return buttonPanel;
    }
    
    private void setupEventHandlers() {
        btnTambahRencana.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tambahRencanaProduksi();
            }
        });
        
        btnHitungKebutuhan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hitungKebutuhanMaterial();
            }
        });
        
        btnSimpanRencana.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                simpanRencanaProduksi();
            }
        });
    }
    
    private void tambahRencanaProduksi() {
        String produk = txtProduk.getText().trim();
        String targetStr = txtTarget.getText().trim();
        String tanggal = txtTanggalProduksi.getText().trim();
        
        if (produk.isEmpty() || targetStr.isEmpty() || tanggal.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Semua field harus diisi!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
            int target = Integer.parseInt(targetStr);
            if (target <= 0) {
                JOptionPane.showMessageDialog(this, "Target produksi harus lebih dari 0!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            int id = modelRencanaProduksi.getRowCount() + 1;
            modelRencanaProduksi.addRow(new Object[]{id, produk, target, tanggal, "Draft"});
            
            // Clear form
            txtProduk.setText("");
            txtTarget.setText("");
            txtTanggalProduksi.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
            
            JOptionPane.showMessageDialog(this, "Rencana produksi berhasil ditambahkan!", "Success", JOptionPane.INFORMATION_MESSAGE);
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Target produksi harus berupa angka!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void hitungKebutuhanMaterial() {
        if (modelRencanaProduksi.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Tidak ada rencana produksi untuk dihitung!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Clear previous calculations
        modelKebutuhanMaterial.setRowCount(0);
        
        // Sample material requirements calculation
        // In real implementation, this would fetch from database based on product BOM
        for (int i = 0; i < modelRencanaProduksi.getRowCount(); i++) {
            String produk = modelRencanaProduksi.getValueAt(i, 1).toString();
            int target = Integer.parseInt(modelRencanaProduksi.getValueAt(i, 2).toString());
            
            // Sample materials for ceramic products
            addMaterialRequirement("Clay Powder", 2.5, target, 150);
            addMaterialRequirement("Silica Sand", 1.8, target, 200);
            addMaterialRequirement("Feldspar", 1.2, target, 80);
            addMaterialRequirement("Water", 0.5, target, 500);
            addMaterialRequirement("Glazing Material", 0.3, target, 45);
        }
        
        lblTotalKebutuhan.setText("Total Item Kebutuhan: " + modelKebutuhanMaterial.getRowCount());
        JOptionPane.showMessageDialog(this, "Kebutuhan material berhasil dihitung!", "Success", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void addMaterialRequirement(String material, double kebutuhanPerUnit, int target, int stokTersedia) {
        double totalKebutuhan = kebutuhanPerUnit * target;
        double selisih = stokTersedia - totalKebutuhan;
        
        modelKebutuhanMaterial.addRow(new Object[]{
            material,
            String.format("%.2f kg", kebutuhanPerUnit),
            String.format("%.2f kg", totalKebutuhan),
            stokTersedia + " kg",
            String.format("%.2f kg", selisih)
        });
    }
    
    private void simpanRencanaProduksi() {
        if (modelRencanaProduksi.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Tidak ada rencana produksi untuk disimpan!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Update status to "Approved"
        for (int i = 0; i < modelRencanaProduksi.getRowCount(); i++) {
            modelRencanaProduksi.setValueAt("Approved", i, 4);
        }
        
        JOptionPane.showMessageDialog(this, 
            "Rencana produksi berhasil disimpan dan disetujui!\n" +
            "Data akan diteruskan ke departemen terkait.", 
            "Success", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeel());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                new DashboardPPIC().setVisible(true);
            }
        });
    }
}
