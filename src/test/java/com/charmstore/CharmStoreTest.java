package com.charmstore;

import com.charmstore.logic.*;
import com.charmstore.model.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit tests for the Charm Bar Store application
 * Demonstrates: Testing, Exception Handling
 */
public class CharmStoreTest {
    private CharmBarLogic logic;
    
    @Before
    public void setUp() {
        logic = new CharmBarLogic();
    }
    
    /**
     * Test creating different types of charms
     * Demonstrates: Polymorphism testing
     */
    @Test
    public void testCreateCharms() throws InvalidSelectionException {
        Charm heart = logic.createCharm("heart", "silver", null);
        assertNotNull(heart);
        assertTrue(heart instanceof HeartCharm);
        assertEquals("Heart Charm", heart.getName());
        
        Charm star = logic.createCharm("star", "gold", null);
        assertNotNull(star);
        assertTrue(star instanceof StarCharm);
        
        Charm animal = logic.createCharm("animal", "silver", "cat");
        assertNotNull(animal);
        assertTrue(animal instanceof AnimalCharm);
        assertEquals("cat Charm", animal.getName());
    }
    
    /**
     * Test creating accessory bases
     * Demonstrates: Polymorphism testing
     */
    @Test
    public void testCreateBases() throws InvalidSelectionException {
        AccessoryBase bracelet = logic.createBase("bracelet", "silver");
        assertNotNull(bracelet);
        assertTrue(bracelet instanceof Bracelet);
        assertEquals("Bracelet", bracelet.getType());
        
        AccessoryBase necklace = logic.createBase("necklace", "gold");
        assertNotNull(necklace);
        assertTrue(necklace instanceof Necklace);
        assertEquals("Necklace", necklace.getType());
    }
    
    /**
     * Test accessory composition
     * Demonstrates: Composition testing
     */
    @Test
    public void testAccessoryComposition() throws InvalidSelectionException {
        AccessoryBase base = logic.createBase("bracelet", "silver");
        Accessory accessory = new Accessory(base);
        
        assertEquals(0, accessory.getCharmCount());
        assertTrue(accessory.canAddCharm());
        
        Charm charm = logic.createCharm("heart", "silver", null);
        logic.addCharmToAccessory(accessory, charm);
        
        assertEquals(1, accessory.getCharmCount());
        assertTrue(accessory.getCharms().size() == 1);
    }
    
    /**
     * Test charm capacity limit
     * Demonstrates: Exception handling testing
     */
    @Test(expected = InvalidSelectionException.class)
    public void testMaxCharmCapacity() throws InvalidSelectionException {
        AccessoryBase base = logic.createBase("bracelet", "silver");
        Accessory accessory = new Accessory(base);
        
        // Bracelet max is 8 charms - try to add 9
        for (int i = 0; i < 9; i++) {
            Charm charm = logic.createCharm("heart", "silver", null);
            logic.addCharmToAccessory(accessory, charm);
        }
    }
    
    /**
     * Test invalid charm type
     * Demonstrates: Exception handling testing
     */
    @Test(expected = InvalidSelectionException.class)
    public void testInvalidCharmType() throws InvalidSelectionException {
        logic.createCharm("invalid", "silver", null);
    }
    
    /**
     * Test price calculation with polymorphism
     * Demonstrates: Polymorphism testing
     */
    @Test
    public void testPriceCalculation() throws InvalidSelectionException {
        AccessoryBase base = logic.createBase("bracelet", "silver");
        Accessory accessory = new Accessory(base);
        
        double basePrice = accessory.calculatePrice();
        assertTrue(basePrice > 0);
        
        Charm charm = logic.createCharm("heart", "silver", null);
        logic.addCharmToAccessory(accessory, charm);
        
        double totalPrice = accessory.calculatePrice();
        assertTrue(totalPrice > basePrice);
    }
    
    /**
     * Test charm customization
     * Demonstrates: Polymorphism, Type-specific behavior
     */
    @Test
    public void testCharmCustomization() throws InvalidSelectionException {
        HeartCharm heart = (HeartCharm) logic.createCharm("heart", "silver", null);
        double basePrice = heart.calculatePrice();
        
        heart.setEngravingText("Love");
        assertTrue(heart.isEngraved());
        assertTrue(heart.calculatePrice() > basePrice);
        
        StarCharm star = (StarCharm) logic.createCharm("star", "gold", null);
        double starBasePrice = star.calculatePrice();
        
        star.setHasGemstone(true);
        assertTrue(star.calculatePrice() > starBasePrice);
    }
    
    /**
     * Test customer name validation
     * Demonstrates: Exception handling testing
     */
    @Test(expected = InvalidSelectionException.class)
    public void testEmptyCustomerName() throws InvalidSelectionException {
        logic.validateCustomerName("");
    }
    
    @Test(expected = InvalidSelectionException.class)
    public void testShortCustomerName() throws InvalidSelectionException {
        logic.validateCustomerName("A");
    }
    
    @Test
    public void testValidCustomerName() throws InvalidSelectionException {
        logic.validateCustomerName("John Doe");
        // Should not throw exception
    }
    
    /**
     * Test order completion
     */
    @Test
    public void testOrderCompletion() throws InvalidSelectionException {
        AccessoryBase base = logic.createBase("necklace", "gold");
        Accessory accessory = new Accessory(base);
        accessory.setCustomerName("Jane Doe");
        
        Charm charm = logic.createCharm("star", "gold", null);
        logic.addCharmToAccessory(accessory, charm);
        
        assertEquals(0, logic.getCompletedOrders().size());
        
        logic.completeOrder(accessory);
        
        assertEquals(1, logic.getCompletedOrders().size());
        assertNotNull(accessory.getOrderDate());
        assertFalse(accessory.getOrderDate().isEmpty());
    }
}
