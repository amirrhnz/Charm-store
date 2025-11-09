package com.charmstore.model;

/**
 * Interface for items that can be customized
 * Demonstrates: Subtyping, Abstraction
 */
public interface Customizable {
    /**
     * Get customization options available
     * @return description of customization options
     */
    String getCustomizationOptions();
    
    /**
     * Apply a custom finish or style
     * @param finish the finish type (e.g., "polished", "matte")
     */
    void applyFinish(String finish);
}
