import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DashboardAdmin extends JFrame {
    private JTabbedPane tabbedPane;
    private JTable materialsTable, productsTable;
    private DefaultTableModel materialsModel, productsModel;
    private JButton addMaterialBtn, editMaterialBtn, deleteMaterialBtn;
    private JButton addProductBtn, editProductBtn, deleteProductBtn;
    private JButton logoutBtn;

    public DashboardAdmin() {
        initComponents();
        setupLayout();
        setupEventHandlers();
        loadSampleData();
    }

    private void initComponents() {
        setTitle("Dashboard Admin - Sistem MRP PT Roman Ceramic");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);

        tabbedPane = new JTabbedPane();
        
        String[] materialColumns = {"ID", "Nama Material", "Satuan", "Stok Minimum", "Stok Aktual", "Harga"};
        materialsModel = new DefaultTableModel(materialColumns, 0);
        materialsTable = new JTable(materialsModel);
        
        String[] productColumns = {"ID", "Nama Produk", "Kategori", "Material Dibutuhkan", "Status"};
        productsModel = new DefaultTableModel(productColumns, 0);
        productsTable = new JTable(productsModel);

        addMaterialBtn = new JButton("Tambah Material");
        editMaterialBtn = new JButton("Edit Material");
        deleteMaterialBtn = new JButton("Hapus Material");
        
        addProductBtn = new JButton("Tambah Produk");
        editProductBtn = new JButton("Edit Produk");
        deleteProductBtn = new JButton("Hapus Produk");
        
        logoutBtn = new JButton("Logout");
    }

    private void setupLayout() {
        setLayout(new BorderLayout());

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(41, 128, 185));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        JLabel titleLabel = new JLabel("Dashboard Admin");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(Color.WHITE);

        JLabel userLabel = new JLabel("Selamat datang, Admin!");
        userLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        userLabel.setForeground(Color.WHITE);

        logoutBtn.setBackground(new Color(231, 76, 60));
        logoutBtn.setForeground(Color.WHITE);
        logoutBtn.setFocusPainted(false);

        JPanel leftHeader = new JPanel(new GridLayout(2, 1));
        leftHeader.setOpaque(false);
        leftHeader.add(titleLabel);
        leftHeader.add(userLabel);

        headerPanel.add(leftHeader, BorderLayout.WEST);
        headerPanel.add(logoutBtn, BorderLayout.EAST);

        JPanel materialsPanel = new JPanel(new BorderLayout());
        materialsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JScrollPane materialsScrollPane = new JScrollPane(materialsTable);
        materialsScrollPane.setPreferredSize(new Dimension(0, 400));

        JPanel materialButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        materialButtonPanel.add(addMaterialBtn);
        materialButtonPanel.add(editMaterialBtn);
        materialButtonPanel.add(deleteMaterialBtn);

        materialsPanel.add(new JLabel("Manajemen Material"), BorderLayout.NORTH);
        materialsPanel.add(materialsScrollPane, BorderLayout.CENTER);
        materialsPanel.add(materialButtonPanel, BorderLayout.SOUTH);

        JPanel productsPanel = new JPanel(new BorderLayout());
        productsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JScrollPane productsScrollPane = new JScrollPane(productsTable);
        productsScrollPane.setPreferredSize(new Dimension(0, 400));

        JPanel productButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        productButtonPanel.add(addProductBtn);
        productButtonPanel.add(editProductBtn);
        productButtonPanel.add(deleteProductBtn);

        productsPanel.add(new JLabel("Manajemen Produk"), BorderLayout.NORTH);
        productsPanel.add(productsScrollPane, BorderLayout.CENTER);
        productsPanel.add(productButtonPanel, BorderLayout.SOUTH);

        JPanel overviewPanel = createOverviewPanel();

        tabbedPane.addTab("Overview", overviewPanel);
        tabbedPane.addTab("Material", materialsPanel);
        tabbedPane.addTab("Produk", productsPanel);

        add(headerPanel, BorderLayout.NORTH);
        add(tabbedPane, BorderLayout.CENTER);
    }

    private JPanel createOverviewPanel() {
        JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panel.add(createStatCard("Total Material", "25", new Color(52, 152, 219)));
        panel.add(createStatCard("Total Produk", "15", new Color(46, 204, 113)));
        panel.add(createStatCard("Material Kritis", "3", new Color(231, 76, 60)));
        panel.add(createStatCard("Produk Aktif", "12", new Color(155, 89, 182)));

        return panel;
    }

    private JPanel createStatCard(String title, String value, Color color) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(color);
        card.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        JLabel valueLabel = new JLabel(value);
        valueLabel.setForeground(Color.WHITE);
        valueLabel.setFont(new Font("Arial", Font.BOLD, 24));

        card.add(titleLabel, BorderLayout.NORTH);
        card.add(valueLabel, BorderLayout.CENTER);

        return card;
    }

    private void setupEventHandlers() {
        addMaterialBtn.addActionListener(e -> showAddMaterialDialog());
        editMaterialBtn.addActionListener(e -> showEditMaterialDialog());
        deleteMaterialBtn.addActionListener(e -> deleteMaterial());

        addProductBtn.addActionListener(e -> showAddProductDialog());
        editProductBtn.addActionListener(e -> showEditProductDialog());
        deleteProductBtn.addActionListener(e -> deleteProduct());

        logoutBtn.addActionListener(e -> {
            int choice = JOptionPane.showConfirmDialog(this, 
                "Apakah Anda yakin ingin logout?", "Konfirmasi", 
                JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                dispose();
                new LoginFrame().setVisible(true);
            }
        });
    }

    private void showAddMaterialDialog() {
        JDialog dialog = new JDialog(this, "Tambah Material", true);
        dialog.setSize(400, 300);
        dialog.setLocationRelativeTo(this);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JTextField nameField = new JTextField(20);
        JTextField unitField = new JTextField(20);
        JTextField minStockField = new JTextField(20);
        JTextField actualStockField = new JTextField(20);
        JTextField priceField = new JTextField(20);

        gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.WEST;
        panel.add(new JLabel("Nama Material:"), gbc);
        gbc.gridx = 1; panel.add(nameField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Satuan:"), gbc);
        gbc.gridx = 1; panel.add(unitField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("Stok Minimum:"), gbc);
        gbc.gridx = 1; panel.add(minStockField, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(new JLabel("Stok Aktual:"), gbc);
        gbc.gridx = 1; panel.add(actualStockField, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        panel.add(new JLabel("Harga:"), gbc);
        gbc.gridx = 1; panel.add(priceField, gbc);

        JButton saveBtn = new JButton("Simpan");
        JButton cancelBtn = new JButton("Batal");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(saveBtn);
        buttonPanel.add(cancelBtn);

        gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 2;
        panel.add(buttonPanel, gbc);

        saveBtn.addActionListener(e -> {
            String[] data = {
                String.valueOf(materialsModel.getRowCount() + 1),
                nameField.getText(),
                unitField.getText(),
                minStockField.getText(),
                actualStockField.getText(),
                priceField.getText()
            };
            materialsModel.addRow(data);
            dialog.dispose();
        });

        cancelBtn.addActionListener(e -> dialog.dispose());

        dialog.add(panel);
        dialog.setVisible(true);
    }

    private void showEditMaterialDialog() {
        int selectedRow = materialsTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Pilih material yang akan diedit!");
            return;
        }
        JOptionPane.showMessageDialog(this, "Fitur edit material akan diimplementasi");
    }

    private void deleteMaterial() {
        int selectedRow = materialsTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Pilih material yang akan dihapus!");
            return;
        }
        
        int choice = JOptionPane.showConfirmDialog(this, 
            "Apakah Anda yakin ingin menghapus material ini?", 
            "Konfirmasi", JOptionPane.YES_NO_OPTION);
        
        if (choice == JOptionPane.YES_OPTION) {
            materialsModel.removeRow(selectedRow);
        }
    }

    private void showAddProductDialog() {
        JOptionPane.showMessageDialog(this, "Dialog tambah produk akan diimplementasi");
    }

    private void showEditProductDialog() {
        int selectedRow = productsTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Pilih produk yang akan diedit!");
            return;
        }
        JOptionPane.showMessageDialog(this, "Fitur edit produk akan diimplementasi");
    }

    private void deleteProduct() {
        int selectedRow = productsTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Pilih produk yang akan dihapus!");
            return;
        }
        
        int choice = JOptionPane.showConfirmDialog(this, 
            "Apakah Anda yakin ingin menghapus produk ini?", 
            "Konfirmasi", JOptionPane.YES_NO_OPTION);
        
        if (choice == JOptionPane.YES_OPTION) {
            productsModel.removeRow(selectedRow);
        }
    }

    private void loadSampleData() {
        Object[][] materialsData = {
            {"1", "Tanah Liat", "Kg", "100", "250", "5000"},
            {"2", "Feldspar", "Kg", "50", "80", "8000"},
            {"3", "Silika", "Kg", "75", "45", "6000"},
            {"4", "Kaolin", "Kg", "60", "120", "7500"}
        };
        
        for (Object[] row : materialsData) {
            materialsModel.addRow(row);
        }

        Object[][] productsData = {
            {"1", "Keramik Lantai 40x40", "Lantai", "Tanah Liat, Feldspar", "Aktif"},
            {"2", "Keramik Dinding 20x25", "Dinding", "Tanah Liat, Kaolin", "Aktif"},
            {"3", "Keramik Granit 60x60", "Lantai", "Feldspar, Silika", "Aktif"}
        };
        
        for (Object[] row : productsData) {
            productsModel.addRow(row);
        }
    }
}
