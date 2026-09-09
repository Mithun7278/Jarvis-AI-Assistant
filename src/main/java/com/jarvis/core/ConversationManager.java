package com.jarvis.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.List;

/**
 * ConversationManager - Manages conversation history and context
 * 
 * This class tracks the conversation flow and maintains conversation history
 * for context-aware responses.
 */
public class ConversationManager {
    private static final Logger logger = LoggerFactory.getLogger(ConversationManager.class);
    
    private static final class ConversationTurn {
        final String userInput;
        final String assistantResponse;
        final long timestamp;
        
        ConversationTurn(String userInput, String assistantResponse) {
            this.userInput = userInput;
            this.assistantResponse = assistantResponse;
            this.timestamp = System.currentTimeMillis();
        }
    }
    
    private final List<ConversationTurn> history;
    private static final int MAX_HISTORY = 50; // Keep last 50 turns
    
    public ConversationManager() {
        this.history = new ArrayList<>();
        logger.debug("ConversationManager created");
    }
    
    /**
     * Record a conversation turn
     */
    public void recordTurn(String userInput, String assistantResponse) {
        history.add(new ConversationTurn(userInput, assistantResponse));
        
        // Keep history size manageable
        if (history.size() > MAX_HISTORY) {
            history.remove(0);
        }
        
        logger.debug("Conversation turn recorded. Total turns: {}", history.size());
    }
    
    /**
     * Get the last user input
     */
    public String getLastUserInput() {
        if (history.isEmpty()) {
            return null;
        }
        return history.get(history.size() - 1).userInput;
    }
    
    /**
     * Get the last assistant response
     */
    public String getLastAssistantResponse() {
        if (history.isEmpty()) {
            return null;
        }
        return history.get(history.size() - 1).assistantResponse;
    }
    
    /**
     * Get conversation history as a single string
     */
    public String getConversationHistory() {
        StringBuilder sb = new StringBuilder();
        for (ConversationTurn turn : history) {
            sb.append("User: ").append(turn.userInput).append("\n")
              .append("JARVIS: ").append(turn.assistantResponse).append("\n\n");
        }
        return sb.toString();
    }
    
    /**
     * Clear conversation history
     */
    public void clear() {
        history.clear();
        logger.info("Conversation history cleared");
    }
    
    /**
     * Get conversation history size
     */
    public int size() {
        return history.size();
    }
}
