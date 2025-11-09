package com.charmstore.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Complete accessory composed of a base and charms
 * Demonstrates: Composition (has-a relationship), Encapsulation, Modularity
 */
public class Accessory implements Priceable {
    // Composition - Accessory HAS-A base and HAS-A list of charms
    private AccessoryBase base;
    private List<Charm> charms;
    private String customerName;
    private String orderDate;
    
    /**
     * Constructor
     * @param base the accessory base (bracelet or necklace)
     */
    public Accessory(AccessoryBase base) {
        this.base = base;
        this.charms = new ArrayList<>();
        this.customerName = "";
        this.orderDate = "";
    }
    
    /**
     * Add a charm to the accessory
     * Demonstrates: Composition, Encapsulation
     * @param charm the charm to add
     * @return true if successful, false if at capacity
     */
    public boolean addCharm(Charm charm) {
        if (charms.size() < base.getMaxCharms()) {
            charms.add(charm);
            return true;
        }
        return false;
    }
    
    /**
     * Remove a charm from the accessory
     * @param index the index of the charm to remove
     * @return true if successful
     */
    public boolean removeCharm(int index) {
        if (index >= 0 && index < charms.size()) {
            charms.remove(index);
            return true;
        }
        return false;
    }
    
    /**
     * Get the accessory base
     * @return the base
     */
    public AccessoryBase getBase() {
        return base;
    }
    
    /**
     * Get list of charms
     * @return copy of charms list (information hiding)
     */
    public List<Charm> getCharms() {
        return new ArrayList<>(charms);
    }
    
    /**
     * Get number of charms attached
     * @return charm count
     */
    public int getCharmCount() {
        return charms.size();
    }
    
    /**
     * Check if can add more charms
     * @return true if space available
     */
    public boolean canAddCharm() {
        return charms.size() < base.getMaxCharms();
    }
    
    public String getCustomerName() {
        return customerName;
    }
    
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    
    public String getOrderDate() {
        return orderDate;
    }
    
    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }
    
    @Override
    public double getBasePrice() {
        return base.getBasePrice();
    }
    
    /**
     * Calculate total price using polymorphism
     * Demonstrates: Polymorphism, Composition
     */
    @Override
    public double calculatePrice() {
        double total = base.calculatePrice();
        
        // Polymorphic call - each charm calculates its own price
        for (Charm charm : charms) {
            total += charm.calculatePrice();
        }
        
        return total;
    }
    
    /**
     * Get detailed description of the complete accessory
     * Demonstrates: Composition
     * @return description
     */
    public String getDetailedDescription() {
        StringBuilder desc = new StringBuilder();
        desc.append("=== Custom Accessory ===\n");
        
        if (customerName != null && !customerName.isEmpty()) {
            desc.append("Customer: ").append(customerName).append("\n");
        }
        if (orderDate != null && !orderDate.isEmpty()) {
            desc.append("Order Date: ").append(orderDate).append("\n");
        }
        
        desc.append("\nBase: ").append(base.getDescription()).append("\n");
        desc.append("Price: $").append(String.format("%.2f", base.calculatePrice())).append("\n");
        
        desc.append("\nCharms (").append(charms.size()).append("/").append(base.getMaxCharms()).append("):\n");
        
        if (charms.isEmpty()) {
            desc.append("  (no charms added yet)\n");
        } else {
            for (int i = 0; i < charms.size(); i++) {
                Charm charm = charms.get(i);
                desc.append("  ").append(i + 1).append(". ").append(charm.getDescription());
                desc.append(" - $").append(String.format("%.2f", charm.calculatePrice())).append("\n");
            }
        }
        
        desc.append("\nTotal Price: $").append(String.format("%.2f", calculatePrice()));
        
        return desc.toString();
    }
    
    @Override
    public String toString() {
        return base.getType() + " with " + charms.size() + " charm(s) - $" + 
               String.format("%.2f", calculatePrice());
    }
}
