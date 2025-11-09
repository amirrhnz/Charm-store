package com.charmstore.model;

/**
 * Interface for items that can be priced
 * Demonstrates: Subtyping, Abstraction
 */
public interface Priceable {
    /**
     * Calculate the price of the item
     * @return price in dollars
     */
    double calculatePrice();
    
    /**
     * Get the base price before any modifications
     * @return base price in dollars
     */
    double getBasePrice();
}
