package com.smartstore.ui;

import com.smartstore.dao.ProductDAO;
import com.smartstore.model.Product;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;

public class DashboardFrame extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField txtName, txtPrice, txtStock;
    private ProductDAO productDAO;

    public DashboardFrame() {
        productDAO = new ProductDAO();

        setTitle("Smart Store - Inventory Dashboard (MongoDB)");
        setSize(700, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // --- Inputs Form ---
        JLabel lblName = new JLabel("Product Name:");
        lblName.setBounds(20, 20, 100, 25);
        add(lblName);

        txtName = new JTextField();
        txtName.setBounds(130, 20, 150, 25);
        add(txtName);

        JLabel lblPrice = new JLabel("Price:");
        lblPrice.setBounds(20, 60, 100, 25);
        add(lblPrice);

        txtPrice = new JTextField();
        txtPrice.setBounds(130, 60, 150, 25);
        add(txtPrice);

        JLabel lblStock = new JLabel("Stock Qty:");
        lblStock.setBounds(20, 100, 100, 25);
        add(lblStock);

        txtStock = new JTextField();
        txtStock.setBounds(130, 100, 150, 25);
        add(txtStock);

        JButton btnAdd = new JButton("Add Product");
        btnAdd.setBounds(130, 140, 150, 30);
        add(btnAdd);

        // --- JTable for Inventory Data ---
        String[] columns = {"ID", "Name", "Price", "Stock"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(300, 20, 360, 350);
        add(scrollPane);

        // Data Load Karo Database se
        refreshTable();

        // --- Button Click Event ---
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String name = txtName.getText();
                    double price = Double.parseDouble(txtPrice.getText());
                    int stock = Integer.parseInt(txtStock.getText());

                    // OOPs concepts ka dynamic use
                    Product p = new Product(name, price, stock, new HashMap<>());
                    productDAO.addProduct(p);

                    JOptionPane.showMessageDialog(null, "Product Added to MongoDB!");
                    refreshTable(); // Table refresh karo

                    // Fields clear karo
                    txtName.setText(""); txtPrice.setText(""); txtStock.setText("");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Please enter valid values!", "Input Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void refreshTable() {
        tableModel.setRowCount(0); // Pehle purana data clear karo
        List<Product> products = productDAO.getAllProducts();
        for (Product p : products) {
            Object[] row = { p.getId(), p.getName(), p.getPrice(), p.getStock() };
            tableModel.addRow(row);
        }
    }
}