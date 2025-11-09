package com.charmstore.logic;

/**
 * Custom exception for charm store errors
 * Demonstrates: Exception Handling, Custom Exceptions
 */
public class CharmStoreException extends Exception {
    
    public CharmStoreException(String message) {
        super(message);
    }
    
    public CharmStoreException(String message, Throwable cause) {
        super(message, cause);
    }
}
