/*import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DashboardGudang extends JFrame {
    private JTabbedPane tabbedPane;
    private JTable stockTable, mrpReportTable, requestTable;
    private DefaultTableModel stockModel, mrpReportModel, requestModel;
    private JButton generateMRPBtn, approveRequestBtn, rejectRequestBtn;
    private JButton updateStockBtn, logoutBtn;
    private JTextArea mrpTextArea;

    public DashboardGudang() {
        initComponents();
        setupLayout();
        setupEventHandlers();
        loadSampleData();
    }

    private void initComponents() {
        setTitle("Dashboard Gudang - Sistem MRP PT Roman Ceramic");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);

        tabbedPane = new JTabbedPane();
        
        String[] stockColumns = {"ID", "Nama Material", "Stok Aktual", "Stok Minimum", "Status", "Lokasi"};
        stockModel = new DefaultTableModel(stockColumns, 0);
        stockTable = new JTable(stockModel);
        
        String[] mrpColumns = {"Material", "Stok Aktual", "Stok Minimum", "Kebutuhan", "Status"};
        mrpReportModel = new DefaultTableModel(mrpColumns, 0);
        mrpReportTable = new JTable(mrpReportTable);
        
        String[] requestColumns = {"ID", "Pemohon", "Material", "Jumlah", "Tanggal", "Status"};
        requestModel = new DefaultTableModel(requestColumns, 0);
        requestTable = new JTable(requestModel);

        generateMRPBtn = new JButton("Generate Laporan MRP");
        approveRequestBtn = new JButton("Setujui Permintaan");
        rejectRequestBtn = new JButton("Tolak Permintaan");
        updateStockBtn = new JButton("Update Stok");
        logoutBtn = new JButton("Logout");
        
        mrpTextArea = new JTextArea(10, 30);
        mrpTextArea.setEditable(false);
        mrpTextArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
    }

    private void setupLayout() {
        setLayout(new BorderLayout());

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(39, 174, 96));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        JLabel titleLabel = new JLabel("Dashboard Gudang");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(Color.WHITE);

        JLabel userLabel = new JLabel("Departemen Gudang - PT Roman Ceramic");
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

        JPanel stockPanel = new JPanel(new BorderLayout());
        stockPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JScrollPane stockScrollPane = new JScrollPane(stockTable);
        stockScrollPane.setPreferredSize(new Dimension(0, 350));

        JPanel stockButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        stockButtonPanel.add(updateStockBtn);

        JLabel stockLabel = new JLabel("Manajemen Stok Material");
        stockLabel.setFont(new Font("Arial", Font.BOLD, 16));

        stockPanel.add(stockLabel, BorderLayout.NORTH);
        stockPanel.add(stockScrollPane, BorderLayout.CENTER);
        stockPanel.add(stockButtonPanel, BorderLayout.SOUTH);

        JPanel mrpPanel = new JPanel(new BorderLayout());
        mrpPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel mrpTopPanel = new JPanel(new BorderLayout());
        JLabel mrpLabel = new JLabel("Laporan MRP (Material Requirement Planning)");
        mrpLabel.setFont(new Font("Arial", Font.BOLD, 16));
        mrpTopPanel.add(mrpLabel, BorderLayout.WEST);
        mrpTopPanel.add(generateMRPBtn, BorderLayout.EAST);

        JScrollPane mrpScrollPane = new JScrollPane(mrpTextArea);
        mrpScrollPane.setBorder(BorderFactory.createTitledBorder("Laporan MRP Otomatis"));

        mrpPanel.add(mrpTopPanel, BorderLayout.NORTH);
        mrpPanel.add(mrpScrollPane, BorderLayout.CENTER);

        JPanel requestPanel = new JPanel(new BorderLayout());
        requestPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JScrollPane requestScrollPane = new JScrollPane(requestTable);
        requestScrollPane.setPreferredSize(new Dimension(0, 300));

        JPanel requestButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        requestButtonPanel.add(approveRequestBtn);
        requestButtonPanel.add(rejectRequestBtn);

        JLabel requestLabel = new JLabel("Permintaan Material dari User");
        requestLabel.setFont(new Font("Arial", Font.BOLD, 16));

        requestPanel.add(requestLabel, BorderLayout.NORTH);
        requestPanel.add(requestScrollPane, BorderLayout.CENTER);
        requestPanel.add(requestButtonPanel, BorderLayout.SOUTH);

        JPanel overviewPanel = createOverviewPanel();

        tabbedPane.addTab("Overview", overviewPanel);
        tabbedPane.addTab("Stok Material", stockPanel);
        tabbedPane.addTab("Laporan MRP", mrpPanel);
        tabbedPane.addTab("Permintaan Material", requestPanel);

        add(headerPanel, BorderLayout.NORTH);
        add(tabbedPane, BorderLayout.CENTER);
    }

    private JPanel createOverviewPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel statsPanel = new JPanel(new GridLayout(1, 4, 10, 10));
        statsPanel.add(createStatCard("Total Material", "25", new Color(52, 152, 219)));
        statsPanel.add(createStatCard("Stok Kritis", "3", new Color(231, 76, 60)));
        statsPanel.add(createStatCard("Permintaan Pending", "5", new Color(243, 156, 18)));
        statsPanel.add(createStatCard("Laporan MRP", "12", new Color(155, 89, 182)));

        JPanel activitiesPanel = new JPanel(new BorderLayout());
        activitiesPanel.setBorder(BorderFactory.createTitledBorder("Aktivitas Terbaru"));
        
        String[] activities = {
            "10:30 - Material Tanah Liat mencapai stok minimum",
            "09:15 - Permintaan material dari User Production",
            "08:45 - Laporan MRP berhasil digenerate",
            "08:00 - Update stok Feldspar (+50 Kg)"
        };
        
        JList<String> activityList = new JList<>(activities);
        activityList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane activityScrollPane = new JScrollPane(activityList);
        activityScrollPane.setPreferredSize(new Dimension(0, 200));
        
        activitiesPanel.add(activityScrollPane, BorderLayout.CENTER);

        panel.add(statsPanel, BorderLayout.NORTH);
        panel.add(activitiesPanel, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createStatCard(String title, String value, Color color) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(color);
        card.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel valueLabel = new JLabel(value);
        valueLabel.setForeground(Color.WHITE);
        valueLabel.setFont(new Font("Arial", Font.BOLD, 24));
        valueLabel.setHorizontalAlignment(SwingConstants.CENTER);

        card.add(titleLabel, BorderLayout.NORTH);
        card.add(valueLabel, BorderLayout.CENTER);

        return card;
    }

    private void setupEventHandlers() {
        generateMRPBtn.addActionListener(e -> generateMRPReport());

        updateStockBtn.addActionListener(e -> showUpdateStockDialog());

        approveRequestBtn.addActionListener(e -> approveRequest());
        rejectRequestBtn.addActionListener(e -> rejectRequest());

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

    private void generateMRPReport() {
        StringBuilder report = new StringBuilder();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        
        report.append("=== LAPORAN MRP (Material Requirement Planning) ===\n");
        report.append("PT Roman Ceramic\n");
        report.append("Tanggal: ").append(sdf.format(new Date())).append("\n\n");
        
        report.append("ANALISIS KEBUTUHAN MATERIAL:\n");
        report.append("----------------------------------------\n");
        
        for (int i = 0; i < stockModel.getRowCount(); i++) {
            String material = (String) stockModel.getValueAt(i, 1);
            int actualStock = Integer.parseInt((String) stockModel.getValueAt(i, 2));
            int minStock = Integer.parseInt((String) stockModel.getValueAt(i, 3));
            String status = (String) stockModel.getValueAt(i, 4);
            
            report.append("Material: ").append(material).append("\n");
            report.append("  Stok Aktual: ").append(actualStock).append("\n");
            report.append("  Stok Minimum: ").append(minStock).append("\n");
            
            if (actualStock <= minStock) {
                int needed = (minStock * 2) - actualStock;
                report.append("  STATUS: PERLU PENGADAAN\n");
                report.append("  Jumlah yang disarankan: ").append(needed).append("\n");
            } else {
                report.append("  STATUS: STOK AMAN\n");
            }
            report.append("\n");
        }
        
        report.append("=== REKOMENDASI PENGADAAN ===\n");
        report.append("1. Tanah Liat: 150 Kg (Prioritas Tinggi)\n");
        report.append("2. Silika: 100 Kg (Prioritas Sedang)\n\n");
        
        report.append("Laporan digenerate otomatis oleh sistem MRP\n");
        report.append("Departemen Gudang - PT Roman Ceramic\n");
        
        mrpTextArea.setText(report.toString());
        JOptionPane.showMessageDialog(this, "Laporan MRP berhasil digenerate!");
    }

    private void showUpdateStockDialog() {
        int selectedRow = stockTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Pilih material yang akan diupdate stoknya!");
            return;
        }

        String materialName = (String) stockModel.getValueAt(selectedRow, 1);
        String currentStock = (String) stockModel.getValueAt(selectedRow, 2);

        JDialog dialog = new JDialog(this, "Update Stok - " + materialName, true);
        dialog.setSize(350, 200);
        dialog.setLocationRelativeTo(this);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel currentLabel = new JLabel("Stok Saat Ini: " + currentStock);
        JTextField newStockField = new JTextField(15);
        JTextArea noteArea = new JTextArea(3, 15);
        noteArea.setBorder(BorderFactory.createTitledBorder("Keterangan"));

        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        panel.add(currentLabel, gbc);

        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 1;
        panel.add(new JLabel("Stok Baru:"), gbc);
        gbc.gridx = 1;
        panel.add(newStockField, gbc);

        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        panel.add(new JScrollPane(noteArea), gbc);

        JButton saveBtn = new JButton("Simpan");
        JButton cancelBtn = new JButton("Batal");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(saveBtn);
        buttonPanel.add(cancelBtn);

        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        panel.add(buttonPanel, gbc);

        saveBtn.addActionListener(e -> {
            try {
                int newStock = Integer.parseInt(newStockField.getText());
                stockModel.setValueAt(String.valueOf(newStock), selectedRow, 2);
                
                int minStock = Integer.parseInt((String) stockModel.getValueAt(selectedRow, 3));
                String status = newStock <= minStock ? "Kritis" : "Aman";
                stockModel.setValueAt(status, selectedRow, 4);
                
                JOptionPane.showMessageDialog(dialog, "Stok berhasil diupdate!");
                dialog.dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "Masukkan angka yang valid!");
            }
        });

        cancelBtn.addActionListener(e -> dialog.dispose());

        dialog.add(panel);
        dialog.setVisible(true);
    }

    private void approveRequest() {
        int selectedRow = requestTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Pilih permintaan yang akan disetujui!");
            return;
        }

        String status = (String) requestModel.getValueAt(selectedRow, 5);
        if (!"Pending".equals(status)) {
            JOptionPane.showMessageDialog(this, "Permintaan sudah diproses!");
            return;
        }

        int choice = JOptionPane.showConfirmDialog(this, 
            "Apakah Anda yakin ingin menyetujui permintaan ini?", 
            "Konfirmasi", JOptionPane.YES_NO_OPTION);

        if (choice == JOptionPane.YES_OPTION) {
            requestModel.setValueAt("Disetujui", selectedRow, 5);
            JOptionPane.showMessageDialog(this, "Permintaan berhasil disetujui!");
        }
    }

    private void rejectRequest() {
        int selectedRow = requestTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Pilih permintaan yang akan ditolak!");
            return;
        }

        String status = (String) requestModel.getValueAt(selectedRow, 5);
        if (!"Pending".equals(status)) {
            JOptionPane.showMessageDialog(this, "Permintaan sudah diproses!");
            return;
        }

        String reason = JOptionPane.showInputDialog(this, "Masukkan alasan penolakan:");
        if (reason != null && !reason.trim().isEmpty()) {
            requestModel.setValueAt("Ditolak", selectedRow, 5);
            JOptionPane.showMessageDialog(this, "Permintaan berhasil ditolak!");
        }
    }

    private void loadSampleData() {
        Object[][] stockData = {
            {"1", "Tanah Liat", "250", "100", "Aman", "Gudang A-1"},
            {"2", "Feldspar", "80", "50", "Aman", "Gudang A-2"},
            {"3", "Silika", "45", "75", "Kritis", "Gudang B-1"},
            {"4", "Kaolin", "120", "60", "Aman", "Gudang B-2"},
            {"5", "Alumina", "30", "40", "Kritis", "Gudang C-1"}
        };
        
        for (Object[] row : stockData) {
            stockModel.addRow(row);
        }

        Object[][] requestData = {
            {"1", "User Production", "Tanah Liat", "50", "27/05/2025", "Pending"},
            {"2", "User Finishing", "Kaolin", "25", "27/05/2025", "Pending"},
            {"3", "User Quality", "Silika", "10", "26/05/2025", "Disetujui"},
            {"4", "User Production", "Feldspar", "30", "26/05/2025", "Pending"}
        };
        
        for (Object[] row : requestData) {
            requestModel.addRow(row);
        }
    }
}*/
