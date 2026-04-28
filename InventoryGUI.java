import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.table.*;

public class InventoryGUI extends JFrame {
    private static final String[] FILE_PATHS = {"cakes.txt", "breads.txt", "pastries.txt", "snacks.txt"};
    private static final int LOW_STOCK_THRESHOLD = 10;
    private static final int HIGH_STOCK_THRESHOLD = 50;

    public InventoryGUI() {
        setTitle("Bakery Inventory Management");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Create main panel with buttons
        JPanel mainPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JButton viewButton = new JButton("View Products");
        JButton modifyButton = new JButton("Modify Products");
        JButton stockButton = new JButton("Stock Level Check");
        JButton exitButton = new JButton("Exit");
        
        // Style buttons
        styleButton(viewButton, new Color(70, 130, 180));
        styleButton(modifyButton, new Color(60, 179, 113));
        styleButton(stockButton, new Color(218, 165, 32));
        styleButton(exitButton, new Color(205, 92, 92));
        
        // Add action listeners
        viewButton.addActionListener(e -> showViewDialog());
        modifyButton.addActionListener(e -> showModifyDialog());
        stockButton.addActionListener(e -> showStockDialog());
        exitButton.addActionListener(e -> System.exit(0));
        
        // Add components to panel
        mainPanel.add(viewButton);
        mainPanel.add(modifyButton);
        mainPanel.add(stockButton);
        mainPanel.add(exitButton);
        
        add(mainPanel);
        setVisible(true);
    }
    
    private void styleButton(JButton button, Color color) {
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
    }
    
    private void showViewDialog() {
        JDialog dialog = new JDialog(this, "View Products", true);
        dialog.setSize(700, 500);
        
        JTabbedPane tabbedPane = new JTabbedPane();
        
        // Tab 1: View all products
        JPanel allProductsPanel = new JPanel(new BorderLayout());
        JTable allProductsTable = createTableForAllFiles();
        allProductsPanel.add(new JScrollPane(allProductsTable), BorderLayout.CENTER);
        tabbedPane.addTab("All Products", allProductsPanel);
        
        // Tab 2: View by category
        JPanel categoryPanel = new JPanel(new BorderLayout());
        JComboBox<String> categoryCombo = new JComboBox<>(new String[]{"Cakes", "Breads", "Pastries", "Snacks"});
        JTable categoryTable = new JTable();
        
        categoryCombo.addActionListener(e -> {
            String category = (String) categoryCombo.getSelectedItem();
            if (category != null) {
                String filePath = category.toLowerCase() + ".txt";
                updateTable(categoryTable, readFile(filePath));
            }
        });
        
        JPanel comboPanel = new JPanel();
        comboPanel.add(new JLabel("Select Category:"));
        comboPanel.add(categoryCombo);
        categoryPanel.add(comboPanel, BorderLayout.NORTH);
        categoryPanel.add(new JScrollPane(categoryTable), BorderLayout.CENTER);
        tabbedPane.addTab("By Category", categoryPanel);
        
        dialog.add(tabbedPane);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }
    
    private JTable createTableForAllFiles() {
        Vector<Vector<String>> data = new Vector<>();
        Vector<String> columns = new Vector<>(Arrays.asList("ID", "Name", "Quantity", "Price", "Cost"));
        
        for (String filePath : FILE_PATHS) {
            data.addAll(readFile(filePath));
        }
        
        DefaultTableModel model = new DefaultTableModel(data, columns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        return new JTable(model);
    }
    
    private Vector<Vector<String>> readFile(String filePath) {
        Vector<Vector<String>> data = new Vector<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null && !line.trim().isEmpty()) {
                String[] parts = line.split(",");
                if (parts.length >= 5) {
                    Vector<String> row = new Vector<>();
                    Collections.addAll(row, parts[0], parts[1], parts[2], parts[3], parts[4]);
                    data.add(row);
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error reading file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return data;
    }
    
    private void updateTable(JTable table, Vector<Vector<String>> data) {
        Vector<String> columns = new Vector<>(Arrays.asList("ID", "Name", "Quantity", "Price", "Cost"));
        DefaultTableModel model = new DefaultTableModel(data, columns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table.setModel(model);
    }
    
    private void showModifyDialog() {
        JDialog dialog = new JDialog(this, "Modify Products", true);
        dialog.setSize(600, 400);
        dialog.setLayout(new GridLayout(3, 1, 10, 10));
        
        JButton addButton = new JButton("Add New Product");
        JButton updateButton = new JButton("Update Existing Product");
        JButton deleteButton = new JButton("Delete Product");
        
        addButton.addActionListener(e -> showAddProductDialog());
        updateButton.addActionListener(e -> showUpdateDialog());
        deleteButton.addActionListener(e -> showDeleteDialog());
        
        dialog.add(addButton);
        dialog.add(updateButton);
        dialog.add(deleteButton);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }
    
    private void showAddProductDialog() {
        JDialog dialog = new JDialog(this, "Add Product", true);
        dialog.setSize(400, 300);
        dialog.setLayout(new GridLayout(6, 2, 5, 5));
        
        JComboBox<String> categoryCombo = new JComboBox<>(new String[]{"cakes", "breads", "pastries", "snacks"});
        JTextField nameField = new JTextField();
        JTextField quantityField = new JTextField();
        JTextField priceField = new JTextField();
        JTextField costField = new JTextField();
        JButton saveButton = new JButton("Save");
        
        dialog.add(new JLabel("Category:"));
        dialog.add(categoryCombo);
        dialog.add(new JLabel("Product Name:"));
        dialog.add(nameField);
        dialog.add(new JLabel("Quantity:"));
        dialog.add(quantityField);
        dialog.add(new JLabel("Sales Price:"));
        dialog.add(priceField);
        dialog.add(new JLabel("Cost:"));
        dialog.add(costField);
        dialog.add(new JLabel());
        dialog.add(saveButton);
        
        saveButton.addActionListener(e -> {
            try {
                String category = (String) categoryCombo.getSelectedItem();
                String name = nameField.getText();
                int quantity = Integer.parseInt(quantityField.getText());
                int price = Integer.parseInt(priceField.getText());
                int cost = Integer.parseInt(costField.getText());
                
                if (name.isEmpty()) {
                    JOptionPane.showMessageDialog(dialog, "Product name cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                String id = generateProductId(category);
                String newEntry = String.format("%s,%s,%d,%d,%d", id, name.toUpperCase(), quantity, price, cost);
                
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(category + ".txt", true))) {
                    writer.newLine();
                    writer.write(newEntry);
                    JOptionPane.showMessageDialog(dialog, "Product added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    dialog.dispose();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(dialog, "Error saving product: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "Please enter valid numbers for quantity, price, and cost", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }
    
    private String generateProductId(String category) {
        String prefix = category.substring(0, 3).toUpperCase();
        Random rand = new Random();
        return prefix + (rand.nextInt(900) + 100);
    }
    
    private void showUpdateDialog() {
        JDialog dialog = new JDialog(this, "Update Product", true);
        dialog.setSize(500, 300);
        dialog.setLayout(new BorderLayout());
        
        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        JComboBox<String> categoryCombo = new JComboBox<>(new String[]{"cakes", "breads", "pastries", "snacks"});
        JTextField idField = new JTextField();
        JTextField quantityField = new JTextField();
        JTextField priceField = new JTextField();
        JTextField costField = new JTextField();
        JButton updateButton = new JButton("Update");
        
        inputPanel.add(new JLabel("Category:"));
        inputPanel.add(categoryCombo);
        inputPanel.add(new JLabel("Product ID:"));
        inputPanel.add(idField);
        inputPanel.add(new JLabel("New Quantity:"));
        inputPanel.add(quantityField);
        inputPanel.add(new JLabel("New Price:"));
        inputPanel.add(priceField);
        inputPanel.add(new JLabel("New Cost:"));
        inputPanel.add(costField);
        
        dialog.add(inputPanel, BorderLayout.CENTER);
        dialog.add(updateButton, BorderLayout.SOUTH);
        
        updateButton.addActionListener(e -> {
            try {
                String category = (String) categoryCombo.getSelectedItem();
                String id = idField.getText().trim().toUpperCase();
                int newQuantity = Integer.parseInt(quantityField.getText());
                int newPrice = Integer.parseInt(priceField.getText());
                int newCost = Integer.parseInt(costField.getText());
                
                if (updateProduct(category + ".txt", id, newQuantity, newPrice, newCost)) {
                    JOptionPane.showMessageDialog(dialog, "Product updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    dialog.dispose();
                } else {
                    JOptionPane.showMessageDialog(dialog, "Product not found with ID: " + id, "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "Please enter valid numbers", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }
    
    private boolean updateProduct(String filePath, String id, int quantity, int price, int cost) {
        File file = new File(filePath);
        StringBuilder updatedContent = new StringBuilder();
        boolean found = false;
        
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null && !line.trim().isEmpty()) {
                String[] parts = line.split(",");
                if (parts.length >= 5 && parts[0].equalsIgnoreCase(id)) {
                    line = String.format("%s,%s,%d,%d,%d", parts[0], parts[1], quantity, price, cost);
                    found = true;
                }
                updatedContent.append(line).append("\n");
            }
        } catch (IOException e) {
            return false;
        }
        
        if (found) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write(updatedContent.toString());
            } catch (IOException e) {
                return false;
            }
        }
        return found;
    }
    
    private void showDeleteDialog() {
        JDialog dialog = new JDialog(this, "Delete Product", true);
        dialog.setSize(400, 150);
        dialog.setLayout(new GridLayout(3, 2, 5, 5));
        
        JComboBox<String> categoryCombo = new JComboBox<>(new String[]{"cakes", "breads", "pastries", "snacks"});
        JTextField idField = new JTextField();
        JButton deleteButton = new JButton("Delete");
        
        dialog.add(new JLabel("Category:"));
        dialog.add(categoryCombo);
        dialog.add(new JLabel("Product ID:"));
        dialog.add(idField);
        dialog.add(new JLabel());
        dialog.add(deleteButton);
        
        deleteButton.addActionListener(e -> {
            String category = (String) categoryCombo.getSelectedItem();
            String id = idField.getText().trim().toUpperCase();
            
            if (deleteProduct(category + ".txt", id)) {
                JOptionPane.showMessageDialog(dialog, "Product deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                dialog.dispose();
            } else {
                JOptionPane.showMessageDialog(dialog, "Product not found with ID: " + id, "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }
    
    private boolean deleteProduct(String filePath, String id) {
        File file = new File(filePath);
        StringBuilder newContent = new StringBuilder();
        boolean found = false;
        
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null && !line.trim().isEmpty()) {
                String[] parts = line.split(",");
                if (parts.length >= 5 && parts[0].equalsIgnoreCase(id)) {
                    found = true;
                    continue;
                }
                newContent.append(line).append("\n");
            }
        } catch (IOException e) {
            return false;
        }
        
        if (found) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write(newContent.toString());
            } catch (IOException e) {
                return false;
            }
        }
        return found;
    }
    
    private void showStockDialog() {
        JDialog dialog = new JDialog(this, "Stock Level Check", true);
        dialog.setSize(700, 500);
        
        JTabbedPane tabbedPane = new JTabbedPane();
        
        // Low stock tab
        JTable lowStockTable = createStockTable(true);
        tabbedPane.addTab("Low Stock", new JScrollPane(lowStockTable));
        
        // High stock tab
        JTable highStockTable = createStockTable(false);
        tabbedPane.addTab("High Stock", new JScrollPane(highStockTable));
        
        // All alerts tab
        JPanel alertsPanel = new JPanel(new BorderLayout());
        JTable alertsTable = createAlertsTable();
        alertsPanel.add(new JScrollPane(alertsTable), BorderLayout.CENTER);
        tabbedPane.addTab("All Alerts", alertsPanel);
        
        dialog.add(tabbedPane);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }
    
    private JTable createStockTable(boolean lowStock) {
        Vector<Vector<String>> data = new Vector<>();
        Vector<String> columns = new Vector<>(Arrays.asList("ID", "Name", "Quantity", "Category"));
        
        for (String filePath : FILE_PATHS) {
            String category = filePath.substring(0, filePath.indexOf('.'));
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = reader.readLine()) != null && !line.trim().isEmpty()) {
                    String[] parts = line.split(",");
                    if (parts.length >= 5) {
                        int quantity = Integer.parseInt(parts[2]);
                        if ((lowStock && quantity < LOW_STOCK_THRESHOLD) ||
                            (!lowStock && quantity > HIGH_STOCK_THRESHOLD)) {
                            Vector<String> row = new Vector<>();
                            Collections.addAll(row, parts[0], parts[1], parts[2], category);
                            data.add(row);
                        }
                    }
                }
            } catch (IOException | NumberFormatException e) {
                // Continue processing other files
            }
        }
        
        return new JTable(new DefaultTableModel(data, columns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
    }
    
    private JTable createAlertsTable() {
        Vector<Vector<String>> data = new Vector<>();
        Vector<String> columns = new Vector<>(Arrays.asList("ID", "Name", "Quantity", "Status", "Category"));
        
        for (String filePath : FILE_PATHS) {
            String category = filePath.substring(0, filePath.indexOf('.'));
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = reader.readLine()) != null && !line.trim().isEmpty()) {
                    String[] parts = line.split(",");
                    if (parts.length >= 5) {
                        int quantity = Integer.parseInt(parts[2]);
                        if (quantity < LOW_STOCK_THRESHOLD || quantity > HIGH_STOCK_THRESHOLD) {
                            Vector<String> row = new Vector<>();
                            String status = quantity < LOW_STOCK_THRESHOLD ? "LOW" : "HIGH";
                            Collections.addAll(row, parts[0], parts[1], parts[2], status, category);
                            data.add(row);
                        }
                    }
                }
            } catch (IOException | NumberFormatException e) {
                // Continue processing other files
            }
        }
        
        return new JTable(new DefaultTableModel(data, columns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new InventoryGUI());
    }
}