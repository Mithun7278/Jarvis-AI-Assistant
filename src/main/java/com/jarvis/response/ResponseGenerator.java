package com.jarvis.response;

import com.jarvis.core.AssistantContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Response Generator Service
 * 
 * Generates natural language responses based on command results.
 * Supports multiple languages and styles.
 */
public class ResponseGenerator {
    private static final Logger logger = LoggerFactory.getLogger(ResponseGenerator.class);
    
    /**
     * Generate a response for the user
     */
    public String generateResponse(String message, AssistantContext context) {
        if (message == null || message.isEmpty()) {
            return "Hi sir. I'm ready to assist.";
        }
        
        String language = context.getUserLanguagePreference();
        return generateResponse(message, language);
    }
    
    /**
     * Generate response in specified language
     */
    public String generateResponse(String message, String language) {
        logger.debug("Generating response in language: {} for message: {}", language, message);
        
        switch (language.toLowerCase()) {
            case "ta":
            case "tamil":
                return generateTamilResponse(message);
            case "tanglish":
                return generateTanglishResponse(message);
            case "en":
            case "english":
            default:
                return generateEnglishResponse(message);
        }
    }
    
    /**
     * Generate English response
     */
    private String generateEnglishResponse(String message) {
        if (!message.startsWith("Hi sir")) {
            return "Hi sir. " + message;
        }
        return message;
    }
    
    /**
     * Generate Tanglish response
     */
    private String generateTanglishResponse(String message) {
        // Convert English to Tanglish format
        String tanglish = convertToTanglish(message);
        
        if (!tanglish.startsWith("Hi sir")) {
            return "Hi sir. " + tanglish;
        }
        return tanglish;
    }
    
    /**
     * Generate Tamil response
     */
    private String generateTamilResponse(String message) {
        // Would require proper Tamil translation
        // For now, return English with Tamil greeting
        if (!message.startsWith("Vanakkam")) {
            return "Vanakkam sir. " + message;
        }
        return message;
    }
    
    /**
     * Convert English message to Tanglish-style format
     */
    private String convertToTanglish(String message) {
        // Simple Tanglish conversion
        String converted = message
            .replace("hello", "vanakkam")
            .replace("goodbye", "poi varuvom")
            .replace("thank you", "nandri")
            .replace("yes", "aamam")
            .replace("no", "illai");
        
        return converted;
    }
    
    /**
     * Generate a greeting response
     */
    public String generateGreeting(String language) {
        switch (language.toLowerCase()) {
            case "ta":
            case "tamil":
                return "Vanakkam sir. Yeppadi irukkai?";
            case "tanglish":
                return "Hi sir. Vanakkam! Yeppadi irukkai?";
            case "en":
            case "english":
            default:
                return "Hi sir. How can I assist you?";
        }
    }
    
    /**
     * Generate an acknowledgment response
     */
    public String generateAcknowledgment(String language) {
        switch (language.toLowerCase()) {
            case "ta":
            case "tamil":
                return "Sari sir. Panren.";
            case "tanglish":
                return "Hi sir. Sari panren.";
            case "en":
            case "english":
            default:
                return "Hi sir. Understood.";
        }
    }
    
    /**
     * Generate an error response
     */
    public String generateErrorResponse(String errorMessage, String language) {
        switch (language.toLowerCase()) {
            case "ta":
            case "tamil":
                return "Vanakkam sir. Oru problem vandhuthu. " + errorMessage;
            case "tanglish":
                return "Hi sir. Oru error vanduthu. " + errorMessage;
            case "en":
            case "english":
            default:
                return "Hi sir. An error occurred: " + errorMessage;
        }
    }
}
