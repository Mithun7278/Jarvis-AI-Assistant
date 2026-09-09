package com.jarvis.intent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Intent Detection Service
 * 
 * Analyzes user input to understand intent.
 * This is a simple keyword-based detector that can be replaced with ML models later.
 */
public class IntentDetector {
    private static final Logger logger = LoggerFactory.getLogger(IntentDetector.class);
    
    /**
     * Detect user intent from input
     */
    public Intent detectIntent(String userInput) {
        if (userInput == null || userInput.trim().isEmpty()) {
            return Intent.UNKNOWN;
        }
        
        String normalized = userInput.toLowerCase().trim();
        logger.debug("Detecting intent for: {}", normalized);
        
        // Time-related
        if (normalized.contains("time") || normalized.contains("neram")) {
            return Intent.GET_TIME;
        }
        
        // Date-related
        if (normalized.contains("date") || normalized.contains("today") || normalized.contains("naal")) {
            return Intent.GET_DATE;
        }
        
        // News-related
        if (normalized.contains("news") || normalized.contains("what's happening")) {
            return Intent.GET_NEWS;
        }
        
        // Search-related
        if (normalized.contains("search") || normalized.contains("find") || normalized.contains("look")) {
            return Intent.WEB_SEARCH;
        }
        
        // Memory-related
        if (normalized.contains("remember") || normalized.contains("save")) {
            return Intent.SAVE_MEMORY;
        }
        
        if (normalized.contains("remind") || normalized.contains("recall") || normalized.contains("what do you remember")) {
            return Intent.RETRIEVE_MEMORY;
        }
        
        if (normalized.contains("forget") || normalized.contains("delete")) {
            return Intent.DELETE_MEMORY;
        }
        
        // System commands
        if (normalized.contains("help") || normalized.contains("commands")) {
            return Intent.HELP;
        }
        
        if (normalized.contains("exit") || normalized.contains("quit") || normalized.contains("bye")) {
            return Intent.EXIT;
        }
        
        // Default to conversation
        return Intent.CONVERSATION;
    }
    
    /**
     * Extract search query from input
     */
    public String extractSearchQuery(String userInput) {
        String normalized = userInput.toLowerCase();
        
        if (normalized.contains("search for")) {
            return userInput.replaceAll("(?i).*search for\s+", "").trim();
        }
        
        if (normalized.contains("search")) {
            return userInput.replaceAll("(?i).*search\s+", "").trim();
        }
        
        if (normalized.contains("find")) {
            return userInput.replaceAll("(?i).*find\s+", "").trim();
        }
        
        return userInput.trim();
    }
    
    /**
     * Extract memory key from input
     */
    public String extractMemoryKey(String userInput) {
        // Simple extraction - can be improved with NLP
        String normalized = userInput.toLowerCase();
        
        if (normalized.contains("remember that")) {
            return userInput.replaceAll("(?i).*remember that\s+", "").trim();
        }
        
        if (normalized.contains("save")) {
            return userInput.replaceAll("(?i).*save\s+", "").trim();
        }
        
        return userInput.trim();
    }
}
