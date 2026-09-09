package com.jarvis.commands;

import com.jarvis.core.AssistantContext;

/**
 * Interface for Command Routing
 * 
 * The command router determines the type of command the user issued
 * and routes it to the appropriate handler.
 */
public interface CommandRouter {
    /**
     * Route a user input to the appropriate handler
     * 
     * @param userInput The user's input
     * @param context The current assistant context
     * @return The result of command processing
     * @throws Exception if routing fails
     */
    CommandResult route(String userInput, AssistantContext context) throws Exception;
}
