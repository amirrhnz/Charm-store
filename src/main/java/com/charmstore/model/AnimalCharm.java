package com.charmstore.model;

/**
 * Animal-themed charm
 * Demonstrates: Inheritance, Polymorphism
 */
public class AnimalCharm extends Charm {
    private String animalType; // e.g., "cat", "dog", "dolphin", "butterfly"
    private boolean hasColorEnamel;
    
    public AnimalCharm(String animalType, String material, double basePrice) {
        super(animalType + " Charm", material, basePrice);
        this.charmType = "Animal";
        this.animalType = animalType;
        this.hasColorEnamel = false;
    }
    
    public String getAnimalType() {
        return animalType;
    }
    
    public void setAnimalType(String animalType) {
        this.animalType = animalType;
        setName(animalType + " Charm"); // update name
    }
    
    public boolean hasColorEnamel() {
        return hasColorEnamel;
    }
    
    public void setHasColorEnamel(boolean hasColorEnamel) {
        this.hasColorEnamel = hasColorEnamel;
    }
    
    /**
     * Polymorphic implementation of abstract method
     * Demonstrates: Polymorphism, Method Overriding
     */
    @Override
    public String getDescription() {
        String desc = "Adorable " + animalType + " charm in " + getMaterial();
        if (hasColorEnamel) {
            desc += " with colored enamel details";
        }
        return desc;
    }
    
    /**
     * Override to add enamel cost
     * Demonstrates: Polymorphism, Method Overriding
     */
    @Override
    public double calculatePrice() {
        double price = super.calculatePrice();
        if (hasColorEnamel) {
            price += 6.0; // enamel work
        }
        return price;
    }
}
