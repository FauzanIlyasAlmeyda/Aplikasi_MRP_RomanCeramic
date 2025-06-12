/*import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MaterialManagementFrame extends JFrame {
    private JTable materialTable;
    private DefaultTableModel tableModel;
    private MaterialDAO materialDAO;
    
    private JTextField codeField, nameField, unitField, priceField, 
                      minStockField, currentStockField, categoryField;
    private JButton addButton, updateButton, deleteButton, clearButton, refreshButton;
    private int selectedMaterialId = -1;
    
    public MaterialManagementFrame() {
        materialDAO = new MaterialDAO();
        initializeComponents();
        setupLayout();
        loadMaterialData();
        setupEventListeners();
    }
    
    private void initializeComponents() {
        setTitle("Material Management - Roman Ceramic MRP");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);
        
        String[] columnNames = {"ID", "Code", "Name", "Unit", "Price", "Min Stock", "Current Stock", "Category"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; 
            }
        };
        materialTable = new JTable(tableModel);
        materialTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        codeField = new JTextField(15);
        nameField = new JTextField(15);
        unitField = new JTextField(15);
        priceField = new JTextField(15);
        minStockField = new JTextField(15);
        currentStockField = new JTextField(15);
        categoryField = new JTextField(15);
        
        addButton = new JButton("Add Material");
        updateButton = new JButton("Update Material");
        deleteButton = new JButton("Delete Material");
        clearButton = new JButton("Clear Form");
        refreshButton = new JButton("Refresh Data");
        
        addButton.setBackground(new Color(76, 175, 80));
        addButton.setForeground(Color.WHITE);
        updateButton.setBackground(new Color(33, 150, 243));
        updateButton.setForeground(Color.WHITE);
        deleteButton.setBackground(new Color(244, 67, 54));
        deleteButton.setForeground(Color.WHITE);
        clearButton.setBackground(new Color(158, 158, 158));
        clearButton.setForeground(Color.WHITE);
        refreshButton.setBackground(new Color(255, 152, 0));
        refreshButton.setForeground(Color.WHITE);
    }
    
    private void setupLayout() {
        setLayout(new BorderLayout());
        
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(63, 81, 181));
        JLabel titleLabel = new JLabel("MATERIAL MANAGEMENT SYSTEM");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(Color.WHITE);
        titlePanel.add(titleLabel);
        add(titlePanel, BorderLayout.NORTH);
        
        // Table Panel
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBorder(BorderFactory.createTitledBorder("Material List"));
        JScrollPane scrollPane = new JScrollPane(materialTable);
        scrollPane.setPreferredSize(new Dimension(0, 300));
        tablePanel.add(scrollPane, BorderLayout.CENTER);
        
        // Form Panel
        JPanel formPanel = createFormPanel();
        
        // Button Panel
        JPanel buttonPanel = createButtonPanel();
        
        // Main content panel
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.add(tablePanel, BorderLayout.CENTER);
        
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(formPanel, BorderLayout.CENTER);
        bottomPanel.add(buttonPanel, BorderLayout.SOUTH);
        contentPanel.add(bottomPanel, BorderLayout.SOUTH);
        
        add(contentPanel, BorderLayout.CENTER);
    }
    
    private JPanel createFormPanel() {
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createTitledBorder("Material Information"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        
        // Row 1
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("Code:"), gbc);
        gbc.gridx = 1;
        formPanel.add(codeField, gbc);
        
        gbc.gridx = 2;
        formPanel.add(new JLabel("Name:"), gbc);
        gbc.gridx = 3;
        formPanel.add(nameField, gbc);
        
        // Row 2
        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(new JLabel("Unit:"), gbc);
        gbc.gridx = 1;
        formPanel.add(unitField, gbc);
        
        gbc.gridx = 2;
        formPanel.add(new JLabel("Price:"), gbc);
        gbc.gridx = 3;
        formPanel.add(priceField, gbc);
        
        // Row 3
        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(new JLabel("Min Stock:"), gbc);
        gbc.gridx = 1;
        formPanel.add(minStockField, gbc);
        
        gbc.gridx = 2;
        formPanel.add(new JLabel("Current Stock:"), gbc);
        gbc.gridx = 3;
        formPanel.add(currentStockField, gbc);
        
        // Row 4
        gbc.gridx = 0; gbc.gridy = 3;
        formPanel.add(new JLabel("Category:"), gbc);
        gbc.gridx = 1;
        formPanel.add(categoryField, gbc);
        
        return formPanel;
    }
    
    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(refreshButton);
        return buttonPanel;
    }
    
    private void setupEventListeners() {
        // Table selection listener
        materialTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = materialTable.getSelectedRow();
                if (selectedRow >= 0) {
                    populateFormFromTable(selectedRow);
                }
            }
        });
        
        // Button listeners
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addMaterial();
            }
        });
        
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateMaterial();
            }
        });
        
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteMaterial();
            }
        });
        
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearForm();
            }
        });
        
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadMaterialData();
            }
        });
    }
    
    private void loadMaterialData() {
        tableModel.setRowCount(0); // Clear existing data
        List<Material> materials = materialDAO.getAllMaterials();
        
        for (Material material : materials) {
            Object[] rowData = {
                material.getId(),
                material.getCode(),
                material.getName(),
                material.getUnit(),
                material.getPrice(),
                material.getStockMinimum(),
                material.getCurrentStock(),
                material.getCategory()
            };
            tableModel.addRow(rowData);
        }
    }
    
    private void populateFormFromTable(int row) {
        selectedMaterialId = (Integer) tableModel.getValueAt(row, 0);
        codeField.setText((String) tableModel.getValueAt(row, 1));
        nameField.setText((String) tableModel.getValueAt(row, 2));
        unitField.setText((String) tableModel.getValueAt(row, 3));
        priceField.setText(String.valueOf(tableModel.getValueAt(row, 4)));
        minStockField.setText(String.valueOf(tableModel.getValueAt(row, 5)));
        currentStockField.setText(String.valueOf(tableModel.getValueAt(row, 6)));
        categoryField.setText((String) tableModel.getValueAt(row, 7));
    }
    
    private void addMaterial() {
        if (!validateForm()) return;
        
        try {
            Material material = new Material(
                codeField.getText().trim(),
                nameField.getText().trim(),
                unitField.getText().trim(),
                Double.parseDouble(priceField.getText().trim()),
                Integer.parseInt(minStockField.getText().trim()),
                Integer.parseInt(currentStockField.getText().trim()),
                categoryField.getText().trim()
            );
            
            if (materialDAO.addMaterial(material)) {
                JOptionPane.showMessageDialog(this, "Material added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                clearForm();
                loadMaterialData();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add material!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter valid numeric values!", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void updateMaterial() {
        if (selectedMaterialId == -1) {
            JOptionPane.showMessageDialog(this, "Please select a material to update!", "Selection Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if (!validateForm()) return;
        
        try {
            Material material = new Material(
                codeField.getText().trim(),
                nameField.getText().trim(),
                unitField.getText().trim(),
                Double.parseDouble(priceField.getText().trim()),
                Integer.parseInt(minStockField.getText().trim()),
                Integer.parseInt(currentStockField.getText().trim()),
                categoryField.getText().trim()
            );
            material.setId(selectedMaterialId);
            
            if (materialDAO.updateMaterial(material)) {
                JOptionPane.showMessageDialog(this, "Material updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                clearForm();
                loadMaterialData();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to update material!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter valid numeric values!", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void deleteMaterial() {
        if (selectedMaterialId == -1) {
            JOptionPane.showMessageDialog(this, "Please select a material to delete!", "Selection Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int confirm = JOptionPane.showConfirmDialog(this, 
            "Are you sure you want to delete this material?", 
            "Confirm Delete", 
            JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            if (materialDAO.deleteMaterial(selectedMaterialId)) {
                JOptionPane.showMessageDialog(this, "Material deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                clearForm();
                loadMaterialData();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to delete material!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void clearForm() {
        codeField.setText("");
        nameField.setText("");
        unitField.setText("");
        priceField.setText("");
        minStockField.setText("");
        currentStockField.setText("");
        categoryField.setText("");
        selectedMaterialId = -1;
        materialTable.clearSelection();
    }
    
    private boolean validateForm() {
        if (codeField.getText().trim().isEmpty() || 
            nameField.getText().trim().isEmpty() || 
            unitField.getText().trim().isEmpty() || 
            priceField.getText().trim().isEmpty() || 
            minStockField.getText().trim().isEmpty() || 
            currentStockField.getText().trim().isEmpty() || 
            categoryField.getText().trim().isEmpty()) {
            
            JOptionPane.showMessageDialog(this, "Please fill all fields!", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        try {
            Double.parseDouble(priceField.getText().trim());
            Integer.parseInt(minStockField.getText().trim());
            Integer.parseInt(currentStockField.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter valid numeric values for Price, Min Stock, and Current Stock!", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        return true;
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeel());
            } catch (Exception e) {
                e.printStackTrace();
            }
            new MaterialManagementFrame().setVisible(true);
        });
    }
}

// ProductManagementFrame.java - GUI untuk manajemen produk
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ProductManagementFrame extends JFrame {
    private JTable productTable;
    private DefaultTableModel tableModel;
    private ProductDAO productDAO;
    
    // Form components
    private JTextField codeField, nameField, descriptionField, categoryField, priceField;
    private JButton addButton, updateButton, deleteButton, clearButton, refreshButton;
    private int selectedProductId = -1;
    
    public ProductManagementFrame() {
        productDAO = new ProductDAO();
        initializeComponents();
        setupLayout();
        loadProductData();
        setupEventListeners();
    }
    
    private void initializeComponents() {
        setTitle("Product Management - Roman Ceramic MRP");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);
        
        // Create table
        String[] columnNames = {"ID", "Code", "Name", "Description", "Category", "Price"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        productTable = new JTable(tableModel);
        productTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        // Create form fields
        codeField = new JTextField(20);
        nameField = new JTextField(20);
        descriptionField = new JTextField(20);
        categoryField = new JTextField(20);
        priceField = new JTextField(20);
        
        // Create buttons
        addButton = new JButton("Add Product");
        updateButton = new JButton("Update Product");
        deleteButton = new JButton("Delete Product");
        clearButton = new JButton("Clear Form");
        refreshButton = new JButton("Refresh Data");
        
        addButton.setBackground(new Color(76, 175, 80));
        addButton.setForeground(Color.WHITE);
        updateButton.setBackground(new Color(33, 150, 243));
        updateButton.setForeground(Color.WHITE);
        deleteButton.setBackground(new Color(244, 67, 54));
        deleteButton.setForeground(Color.WHITE);
        clearButton.setBackground(new Color(158, 158, 158));
        clearButton.setForeground(Color.WHITE);
        refreshButton.setBackground(new Color(255, 152, 0));
        refreshButton.setForeground(Color.WHITE);
    }
    
    private void setupLayout() {
        setLayout(new BorderLayout());
        
        // Title Panel
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(63, 81, 181));
        JLabel titleLabel = new JLabel("PRODUCT MANAGEMENT SYSTEM");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(Color.WHITE);
        titlePanel.add(titleLabel);
        add(titlePanel, BorderLayout.NORTH);
        
        // Table Panel
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBorder(BorderFactory.createTitledBorder("Product List"));
        JScrollPane scrollPane = new JScrollPane(productTable);
        scrollPane.setPreferredSize(new Dimension(0, 300));
        tablePanel.add(scrollPane, BorderLayout.CENTER);
        
        // Form Panel
        JPanel formPanel = createFormPanel();
        
        // Button Panel
        JPanel buttonPanel = createButtonPanel();
        
        // Main content panel
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.add(tablePanel, BorderLayout.CENTER);
        
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(formPanel, BorderLayout.CENTER);
        bottomPanel.add(buttonPanel, BorderLayout.SOUTH);
        contentPanel.add(bottomPanel, BorderLayout.SOUTH);
        
        add(contentPanel, BorderLayout.CENTER);
    }
    
    private JPanel createFormPanel() {
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createTitledBorder("Product Information"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        
        // Row 1
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("Code:"), gbc);
        gbc.gridx = 1;
        formPanel.add(codeField, gbc);
        
        gbc.gridx = 2;
        formPanel.add(new JLabel("Name:"), gbc);
        gbc.gridx = 3;
        formPanel.add(nameField, gbc);
        
        // Row 2
        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(new JLabel("Description:"), gbc);
        gbc.gridx = 1;
        formPanel.add(descriptionField, gbc);
        
        gbc.gridx = 2;
        formPanel.add(new JLabel("Category:"), gbc);
        gbc.gridx = 3;
        formPanel.add(categoryField, gbc);
        
        // Row 3
        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(new JLabel("Price:"), gbc);
        gbc.gridx = 1;
        formPanel.add(priceField, gbc);
        
        return formPanel;
    }
    
    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(refreshButton);
        return buttonPanel;
    }
    
    private void setupEventListeners() {
        // Table selection listener
        productTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = productTable.getSelectedRow();
                if (selectedRow >= 0) {
                    populateFormFromTable(selectedRow);
                }
            }
        });
        
        // Button listeners
        addButton.addActionListener(e -> addProduct());
        updateButton.addActionListener(e -> updateProduct());
        deleteButton.addActionListener(e -> deleteProduct());
        clearButton.addActionListener(e -> clearForm());
        refreshButton.addActionListener(e -> loadProductData());
    }
    
    private void loadProductData() {
        tableModel.setRowCount(0);
        List<Product> products = productDAO.getAllProducts();
        
        for (Product product : products) {
            Object[] rowData = {
                product.getId(),
                product.getCode(),
                product.getName(),
                product.getDescription(),
                product.getCategory(),
                product.getPrice()
            };
            tableModel.addRow(rowData);
        }
    }
    
    private void populateFormFromTable(int row) {
        selectedProductId = (Integer) tableModel.getValueAt(row, 0);
        codeField.setText((String) tableModel.getValueAt(row, 1));
        nameField.setText((String) tableModel.getValueAt(row, 2));
        descriptionField.setText((String) tableModel.getValueAt(row, 3));
        categoryField.setText((String) tableModel.getValueAt(row, 4));
        priceField.setText(String.valueOf(tableModel.getValueAt(row, 5)));
    }
    
    private void addProduct() {
        if (!validateForm()) return;
        
        try {
            Product product = new Product(
                codeField.getText().trim(),
                nameField.getText().trim(),
                descriptionField.getText().trim(),
                categoryField.getText().trim(),
                Double.parseDouble(priceField.getText().trim())
            );
            
            if (productDAO.addProduct(product)) {
                JOptionPane.showMessageDialog(this, "Product added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                clearForm();
                loadProductData();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add product!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter valid numeric value for price!", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void updateProduct() {
        if (selectedProductId == -1) {
            JOptionPane.showMessageDialog(this, "Please select a product to update!", "Selection Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if (!validateForm()) return;
        
        try {
            Product product = new Product(
                codeField.getText().trim(),
                nameField.getText().trim(),
                descriptionField.getText().trim(),
                categoryField.getText().trim(),
                Double.parseDouble(priceField.getText().trim())
            );
            product.setId(selectedProductId);
            
            if (productDAO.updateProduct(product)) {
                JOptionPane.showMessageDialog(this, "Product updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                clearForm();
                loadProductData();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to update product!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter valid numeric value for price!", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void deleteProduct() {
        if (selectedProductId == -1) {
            JOptionPane.showMessageDialog(this, "Please select a product to delete!", "Selection Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int confirm = JOptionPane.showConfirmDialog(this, 
            "Are you sure you want to delete this product?", 
            "Confirm Delete", 
            JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            if (productDAO.deleteProduct(selectedProductId)) {
                JOptionPane.showMessageDialog(this, "Product deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                clearForm();
                loadProductData();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to delete product!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void clearForm() {
        codeField.setText("");
        nameField.setText("");
        descriptionField.setText("");
        categoryField.setText("");
        priceField.setText("");
        selectedProductId = -1;
        productTable.clearSelection();
    }
    
    private boolean validateForm() {
        if (codeField.getText().trim().isEmpty() || 
            nameField.getText().trim().isEmpty() || 
            descriptionField.getText().trim().isEmpty() || 
            categoryField.getText().trim().isEmpty() || 
            priceField.getText().trim().isEmpty()) {
            
            JOptionPane.showMessageDialog(this, "Please fill all fields!", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        try {
            Double.parseDouble(priceField.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter valid numeric value for price!", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        return true;
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeel());
            } catch (Exception e) {
                e.printStackTrace();
            }
            new ProductManagementFrame().setVisible(true);
        });
    }
}
 */