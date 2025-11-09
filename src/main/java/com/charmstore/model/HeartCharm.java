package com.charmstore.model;

/**
 * Heart-shaped charm
 * Demonstrates: Inheritance, Polymorphism
 */
public class HeartCharm extends Charm {
    private boolean engraved;
    private String engravingText;
    
    public HeartCharm(String material, double basePrice) {
        super("Heart Charm", material, basePrice);
        this.charmType = "Heart";
        this.engraved = false;
        this.engravingText = "";
    }
    
    public boolean isEngraved() {
        return engraved;
    }
    
    public void setEngraved(boolean engraved) {
        this.engraved = engraved;
    }
    
    public String getEngravingText() {
        return engravingText;
    }
    
    public void setEngravingText(String text) {
        this.engravingText = text;
        this.engraved = true;
    }
    
    /**
     * Polymorphic implementation of abstract method
     * Demonstrates: Polymorphism, Method Overriding
     */
    @Override
    public String getDescription() {
        String desc = "Beautiful heart-shaped charm in " + getMaterial();
        if (engraved && engravingText != null && !engravingText.isEmpty()) {
            desc += " with engraving: '" + engravingText + "'";
        }
        return desc;
    }
    
    /**
     * Override to add engraving cost
     * Demonstrates: Polymorphism, Method Overriding
     */
    @Override
    public double calculatePrice() {
        double price = super.calculatePrice(); // Reuse parent calculation
        if (engraved) {
            price += 5.0; // engraving fee
        }
        return price;
    }
}
