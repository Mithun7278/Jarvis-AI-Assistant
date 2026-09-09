package com.jarvis.memory;

/**
 * Interface for Memory Services
 * 
 * Implementations of this interface can be swapped without changing core logic.
 * Examples: JSON file storage, SQLite database, cloud storage, etc.
 */
public interface MemoryService {
    /**
     * Save a key-value pair to memory
     * 
     * @param key The memory key
     * @param value The memory value
     * @throws Exception if saving fails
     */
    void save(String key, String value) throws Exception;
    
    /**
     * Retrieve a value from memory
     * 
     * @param key The memory key
     * @return The stored value, or null if not found
     * @throws Exception if retrieval fails
     */
    String get(String key) throws Exception;
    
    /**
     * Delete a value from memory
     * 
     * @param key The memory key
     * @throws Exception if deletion fails
     */
    void delete(String key) throws Exception;
    
    /**
     * Check if a key exists in memory
     * 
     * @param key The memory key
     * @return true if the key exists
     * @throws Exception if check fails
     */
    boolean exists(String key) throws Exception;
    
    /**
     * Clear all memory (with caution)
     * 
     * @throws Exception if clearing fails
     */
    void clear() throws Exception;
}
