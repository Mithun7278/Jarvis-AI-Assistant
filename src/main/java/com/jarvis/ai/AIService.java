package com.jarvis.ai;

import com.jarvis.core.AssistantContext;

/**
 * Interface for AI Services
 * 
 * Implementations of this interface can be swapped without changing core logic.
 * Examples: OpenAI GPT, Google Bard, Hugging Face, local LLM, etc.
 */
public interface AIService {
    /**
     * Generate a response to user input
     * 
     * @param userInput The user's input/question
     * @param context The current assistant context
     * @return The AI-generated response
     * @throws Exception if AI processing fails
     */
    String generateResponse(String userInput, AssistantContext context) throws Exception;
    
    /**
     * Check if the AI service is available
     * 
     * @return true if service is available and configured
     */
    boolean isAvailable();
}
