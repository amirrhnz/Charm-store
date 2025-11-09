package com.charmstore.logic;

/**
 * Exception thrown when trying to add too many charms
 * Demonstrates: Exception Handling, Inheritance in exceptions
 */
public class InvalidSelectionException extends CharmStoreException {
    
    public InvalidSelectionException(String message) {
        super(message);
    }
}
