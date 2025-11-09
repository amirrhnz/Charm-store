package com.charmstore.model;

/**
 * Necklace accessory base
 * Demonstrates: Inheritance, Polymorphism
 */
public class Necklace extends AccessoryBase {
    private String necklaceStyle; // e.g., "chain", "pendant", "choker"
    
    public Necklace(String material, double length, double basePrice) {
        super(material, length, basePrice);
        this.maxCharms = 12; // necklaces hold more charms
        this.necklaceStyle = "chain"; // default
    }
    
    public String getNecklaceStyle() {
        return necklaceStyle;
    }
    
    public void setNecklaceStyle(String style) {
        this.necklaceStyle = style;
    }
    
    /**
     * Polymorphic implementation
     * Demonstrates: Polymorphism, Method Overriding
     */
    @Override
    public String getType() {
        return "Necklace";
    }
    
    /**
     * Polymorphic implementation
     * Demonstrates: Polymorphism, Method Overriding
     */
    @Override
    public String getDescription() {
        return necklaceStyle + " style necklace in " + getMaterial() + 
               " (" + getLength() + " inches)";
    }
    
    /**
     * Override to add style premium
     * Demonstrates: Polymorphism
     */
    @Override
    public double calculatePrice() {
        double price = super.calculatePrice();
        // Premium for certain styles
        if ("choker".equals(necklaceStyle)) {
            price += 8.0;
        } else if ("pendant".equals(necklaceStyle)) {
            price += 10.0;
        }
        return price;
    }
}
