package com.charmstore.model;

/**
 * Abstract base class for accessory bases (bracelet or necklace)
 * Demonstrates: Abstraction, Hierarchy, Inheritance, Encapsulation
 */
public abstract class AccessoryBase implements Priceable {
    // Private fields - Encapsulation
    private String material;
    private double length; // in inches
    private double basePrice;
    private String claspType;
    
    // Protected field
    protected int maxCharms; // different for bracelet vs necklace
    
    /**
     * Constructor
     * @param material the material type
     * @param length the length in inches
     * @param basePrice the base price
     */
    public AccessoryBase(String material, double length, double basePrice) {
        this.material = material;
        this.length = length;
        this.basePrice = basePrice;
        this.claspType = "lobster"; // default
    }
    
    // Getters and setters
    public String getMaterial() {
        return material;
    }
    
    public void setMaterial(String material) {
        this.material = material;
    }
    
    public double getLength() {
        return length;
    }
    
    public void setLength(double length) {
        this.length = length;
    }
    
    @Override
    public double getBasePrice() {
        return basePrice;
    }
    
    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }
    
    public String getClaspType() {
        return claspType;
    }
    
    public void setClaspType(String claspType) {
        this.claspType = claspType;
    }
    
    public int getMaxCharms() {
        return maxCharms;
    }
    
    /**
     * Abstract method - subclasses define their type
     * Demonstrates: Abstraction
     */
    public abstract String getType();
    
    /**
     * Get description of the base
     * Demonstrates: Abstraction
     */
    public abstract String getDescription();
    
    /**
     * Calculate price of the base
     * Demonstrates: Reuse
     */
    @Override
    public double calculatePrice() {
        double price = basePrice;
        // Premium for certain clasp types
        if ("magnetic".equals(claspType)) {
            price += 3.0;
        }
        return price;
    }
    
    @Override
    public String toString() {
        return getType() + " (" + material + ", " + length + "in) - $" + calculatePrice();
    }
}
