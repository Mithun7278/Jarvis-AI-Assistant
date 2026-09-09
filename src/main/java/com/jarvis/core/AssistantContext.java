package com.jarvis.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.HashMap;
import java.util.Map;

/**
 * AssistantContext - Holds conversation and application state
 * 
 * This context is passed between services to maintain conversation state,
 * user preferences, and temporary data during processing.
 */
public class AssistantContext {
    private static final Logger logger = LoggerFactory.getLogger(AssistantContext.class);
    
    private final Map<String, Object> contextData;
    private String lastUserInput;
    private String lastResponse;
    private long lastInteractionTime;
    private String userLanguagePreference; // "en", "ta", "tanglish"
    
    public AssistantContext() {
        this.contextData = new HashMap<>();
        this.userLanguagePreference = "tanglish"; // Default language
        this.lastInteractionTime = System.currentTimeMillis();
        
        logger.debug("AssistantContext created");
    }
    
    /**
     * Store data in context
     */
    public void put(String key, Object value) {
        contextData.put(key, value);
        logger.debug("Context data set: {} = {}", key, value);
    }
    
    /**
     * Retrieve data from context
     */
    public Object get(String key) {
        return contextData.get(key);
    }
    
    /**
     * Retrieve data as String
     */
    public String getString(String key) {
        Object value = get(key);
        return value != null ? value.toString() : null;
    }
    
    /**
     * Check if context has key
     */
    public boolean has(String key) {
        return contextData.containsKey(key);
    }
    
    /**
     * Record user input
     */
    public void setLastUserInput(String input) {
        this.lastUserInput = input;
        this.lastInteractionTime = System.currentTimeMillis();
    }
    
    /**
     * Get last user input
     */
    public String getLastUserInput() {
        return lastUserInput;
    }
    
    /**
     * Record assistant response
     */
    public void setLastResponse(String response) {
        this.lastResponse = response;
    }
    
    /**
     * Get last assistant response
     */
    public String getLastResponse() {
        return lastResponse;
    }
    
    /**
     * Set user language preference
     */
    public void setUserLanguagePreference(String language) {
        this.userLanguagePreference = language;
        logger.info("User language preference changed to: {}", language);
    }
    
    /**
     * Get user language preference
     */
    public String getUserLanguagePreference() {
        return userLanguagePreference;
    }
    
    /**
     * Get time since last interaction (in milliseconds)
     */
    public long getTimeSinceLastInteraction() {
        return System.currentTimeMillis() - lastInteractionTime;
    }
}
