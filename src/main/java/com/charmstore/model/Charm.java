package com.charmstore.model;

/**
 * Abstract base class for all charms
 * Demonstrates: Abstraction, Hierarchy, Inheritance, Encapsulation, Information Hiding
 */
public abstract class Charm implements Priceable, Customizable {
    // Private fields - demonstrates Encapsulation and Information Hiding
    private String name;
    private String material;
    private double basePrice;
    private String finish; // e.g., polished, matte
    
    // Protected field - accessible to subclasses (Information Hiding)
    protected String charmType;
    
    /**
     * Constructor for Charm
     * @param name the name of the charm
     * @param material the material (e.g., "silver", "gold")
     * @param basePrice the base price
     */
    public Charm(String name, String material, double basePrice) {
        this.name = name;
        this.material = material;
        this.basePrice = basePrice;
        this.finish = "polished"; // default finish
    }
    
    // Getters and Setters - demonstrates Encapsulation
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getMaterial() {
        return material;
    }
    
    public void setMaterial(String material) {
        this.material = material;
    }
    
    @Override
    public double getBasePrice() {
        return basePrice;
    }
    
    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }
    
    public String getFinish() {
        return finish;
    }
    
    @Override
    public void applyFinish(String finish) {
        this.finish = finish;
    }
    
    public String getCharmType() {
        return charmType;
    }
    
    /**
     * Abstract method that subclasses must implement
     * Demonstrates: Abstraction, Polymorphism
     * @return description of the charm
     */
    public abstract String getDescription();
    
    /**
     * Calculate price with finish modifier
     * Demonstrates: Reuse (shared implementation)
     */
    @Override
    public double calculatePrice() {
        double price = basePrice;
        // Add premium for special finishes
        if ("matte".equals(finish)) {
            price += 2.0;
        } else if ("antique".equals(finish)) {
            price += 3.0;
        }
        return price;
    }
    
    @Override
    public String getCustomizationOptions() {
        return "Available finishes: polished, matte, antique";
    }
    
    @Override
    public String toString() {
        return name + " (" + material + ", " + finish + " finish) - $" + calculatePrice();
    }
}
