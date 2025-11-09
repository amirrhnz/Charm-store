package com.charmstore.model;

/**
 * Star-shaped charm
 * Demonstrates: Inheritance, Polymorphism
 */
public class StarCharm extends Charm {
    private int numberOfPoints; // 5-point or 6-point star
    private boolean hasGemstone;
    
    public StarCharm(String material, double basePrice) {
        super("Star Charm", material, basePrice);
        this.charmType = "Star";
        this.numberOfPoints = 5; // default
        this.hasGemstone = false;
    }
    
    public int getNumberOfPoints() {
        return numberOfPoints;
    }
    
    public void setNumberOfPoints(int points) {
        if (points == 5 || points == 6) {
            this.numberOfPoints = points;
        }
    }
    
    public boolean hasGemstone() {
        return hasGemstone;
    }
    
    public void setHasGemstone(boolean hasGemstone) {
        this.hasGemstone = hasGemstone;
    }
    
    /**
     * Polymorphic implementation of abstract method
     * Demonstrates: Polymorphism, Method Overriding
     */
    @Override
    public String getDescription() {
        String desc = "Shining " + numberOfPoints + "-point star charm in " + getMaterial();
        if (hasGemstone) {
            desc += " with gemstone center";
        }
        return desc;
    }
    
    /**
     * Override to add gemstone cost
     * Demonstrates: Polymorphism, Method Overriding
     */
    @Override
    public double calculatePrice() {
        double price = super.calculatePrice();
        if (hasGemstone) {
            price += 8.0; // gemstone addition
        }
        return price;
    }
}
