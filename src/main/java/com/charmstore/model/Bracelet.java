package com.charmstore.model;

/**
 * Bracelet accessory base
 * Demonstrates: Inheritance, Polymorphism
 */
public class Bracelet extends AccessoryBase {
    private String braceletStyle; // e.g., "chain", "bangle", "cuff"
    
    public Bracelet(String material, double length, double basePrice) {
        super(material, length, basePrice);
        this.maxCharms = 8; // bracelets hold fewer charms
        this.braceletStyle = "chain"; // default
    }
    
    public String getBraceletStyle() {
        return braceletStyle;
    }
    
    public void setBraceletStyle(String style) {
        this.braceletStyle = style;
    }
    
    /**
     * Polymorphic implementation
     * Demonstrates: Polymorphism, Method Overriding
     */
    @Override
    public String getType() {
        return "Bracelet";
    }
    
    /**
     * Polymorphic implementation
     * Demonstrates: Polymorphism, Method Overriding
     */
    @Override
    public String getDescription() {
        return braceletStyle + " style bracelet in " + getMaterial() + 
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
        if ("bangle".equals(braceletStyle)) {
            price += 5.0;
        } else if ("cuff".equals(braceletStyle)) {
            price += 7.0;
        }
        return price;
    }
}
