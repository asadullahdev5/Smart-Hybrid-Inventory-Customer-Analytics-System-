package com.smartstore.model;

import java.util.Map;

public class Product {
    private String id; // MongoDB ka Object ID string format mein
    private String name;
    private double price;
    private int stock;
    private Map<String, Object> attributes; // Dynamic specs k liye (e.g., RAM, Color)

    // Constructor
    public Product(String name, double price, int stock, Map<String, Object> attributes) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.attributes = attributes;
    }

    // Getters aur Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getStock() { return stock; }
    public Map<String, Object> getAttributes() { return attributes; }
}