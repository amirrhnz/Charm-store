package com.charmstore.logic;

import com.charmstore.model.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Business logic for the charm bar store
 * Demonstrates: Modularity, Encapsulation, Exception Handling, Reuse
 */
public class CharmBarLogic {
    // Available inventory - demonstrates encapsulation
    private List<Charm> availableCharms;
    private List<AccessoryBase> availableBases;
    
    // Order history
    private List<Accessory> completedOrders;
    
    /**
     * Constructor - initializes the store inventory
     */
    public CharmBarLogic() {
        this.availableCharms = new ArrayList<>();
        this.availableBases = new ArrayList<>();
        this.completedOrders = new ArrayList<>();
        initializeInventory();
    }
    
    /**
     * Initialize the store with available items
     * Demonstrates: Modularity, Reuse
     */
    private void initializeInventory() {
        // Initialize charms
        availableCharms.add(new HeartCharm("silver", 10.0));
        availableCharms.add(new HeartCharm("gold", 15.0));
        availableCharms.add(new StarCharm("silver", 12.0));
        availableCharms.add(new StarCharm("gold", 18.0));
        availableCharms.add(new AnimalCharm("cat", "silver", 11.0));
        availableCharms.add(new AnimalCharm("dog", "silver", 11.0));
        availableCharms.add(new AnimalCharm("butterfly", "gold", 16.0));
        availableCharms.add(new AnimalCharm("dolphin", "silver", 13.0));
        
        // Initialize bases
        availableBases.add(new Bracelet("silver", 7.5, 20.0));
        availableBases.add(new Bracelet("gold", 7.5, 35.0));
        availableBases.add(new Necklace("silver", 18.0, 30.0));
        availableBases.add(new Necklace("gold", 18.0, 50.0));
    }
    
    /**
     * Get available charms
     * @return copy of available charms list (information hiding)
     */
    public List<Charm> getAvailableCharms() {
        return new ArrayList<>(availableCharms);
    }
    
    /**
     * Get available bases
     * @return copy of available bases list
     */
    public List<AccessoryBase> getAvailableBases() {
        return new ArrayList<>(availableBases);
    }
    
    /**
     * Create a new charm based on type
     * Demonstrates: Polymorphism, Factory pattern
     * @param charmType type of charm ("heart", "star", "animal")
     * @param material material type
     * @param animalType specific animal (if charm type is animal)
     * @return new charm instance
     * @throws InvalidSelectionException if invalid type
     */
    public Charm createCharm(String charmType, String material, String animalType) 
            throws InvalidSelectionException {
        
        if (charmType == null || material == null) {
            throw new InvalidSelectionException("Charm type and material cannot be null");
        }
        
        double basePrice = "gold".equalsIgnoreCase(material) ? 15.0 : 10.0;
        
        switch (charmType.toLowerCase()) {
            case "heart":
                return new HeartCharm(material, basePrice);
            case "star":
                return new StarCharm(material, basePrice + 2);
            case "animal":
                if (animalType == null || animalType.isEmpty()) {
                    throw new InvalidSelectionException("Animal type must be specified");
                }
                return new AnimalCharm(animalType, material, basePrice + 1);
            default:
                throw new InvalidSelectionException("Unknown charm type: " + charmType);
        }
    }
    
    /**
     * Create a new accessory base
     * Demonstrates: Polymorphism, Factory pattern
     * @param baseType "bracelet" or "necklace"
     * @param material material type
     * @return new base instance
     * @throws InvalidSelectionException if invalid type
     */
    public AccessoryBase createBase(String baseType, String material) 
            throws InvalidSelectionException {
        
        if (baseType == null || material == null) {
            throw new InvalidSelectionException("Base type and material cannot be null");
        }
        
        boolean isGold = "gold".equalsIgnoreCase(material);
        
        switch (baseType.toLowerCase()) {
            case "bracelet":
                return new Bracelet(material, 7.5, isGold ? 35.0 : 20.0);
            case "necklace":
                return new Necklace(material, 18.0, isGold ? 50.0 : 30.0);
            default:
                throw new InvalidSelectionException("Unknown base type: " + baseType);
        }
    }
    
    /**
     * Assemble an accessory with polymorphic charm addition
     * Demonstrates: Polymorphism, Exception Handling
     * @param accessory the accessory to add charm to
     * @param charm the charm to add
     * @throws InvalidSelectionException if cannot add charm
     */
    public void addCharmToAccessory(Accessory accessory, Charm charm) 
            throws InvalidSelectionException {
        
        if (accessory == null || charm == null) {
            throw new InvalidSelectionException("Accessory and charm cannot be null");
        }
        
        if (!accessory.canAddCharm()) {
            throw new InvalidSelectionException(
                "Cannot add more charms. Maximum capacity (" + 
                accessory.getBase().getMaxCharms() + ") reached.");
        }
        
        accessory.addCharm(charm);
    }
    
    /**
     * Complete an order and add to history
     * @param accessory the completed accessory
     */
    public void completeOrder(Accessory accessory) {
        if (accessory != null) {
            // Set order date
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            accessory.setOrderDate(sdf.format(new Date()));
            completedOrders.add(accessory);
        }
    }
    
    /**
     * Get order history
     * @return list of completed orders
     */
    public List<Accessory> getCompletedOrders() {
        return new ArrayList<>(completedOrders);
    }
    
    /**
     * Save an accessory to a file
     * Demonstrates: Exception Handling, File I/O
     * @param accessory the accessory to save
     * @param filename the file name
     * @throws CharmStoreException if save fails
     */
    public void saveAccessoryToFile(Accessory accessory, String filename) 
            throws CharmStoreException {
        
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            writer.println(accessory.getDetailedDescription());
            writer.println("\n--- Saved on " + new Date() + " ---");
        } catch (IOException e) {
            throw new CharmStoreException("Failed to save accessory to file: " + filename, e);
        }
    }
    
    /**
     * Validate customer name
     * Demonstrates: Exception Handling
     * @param name customer name
     * @throws InvalidSelectionException if invalid
     */
    public void validateCustomerName(String name) throws InvalidSelectionException {
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidSelectionException("Customer name cannot be empty");
        }
        if (name.length() < 2) {
            throw new InvalidSelectionException("Customer name must be at least 2 characters");
        }
    }
}
