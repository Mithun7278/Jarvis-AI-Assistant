package com.jarvis.commands;

/**
 * Enumeration of all possible command types
 * 
 * This provides a type-safe way to route commands without string comparisons.
 */
public enum CommandType {
    CONVERSATION,       // General conversation
    WEB_SEARCH,        // Search the web
    NEWS,              // Get news
    MEMORY_SAVE,       // Save to memory
    MEMORY_RETRIEVE,   // Get from memory
    MEMORY_DELETE,     // Delete from memory
    SYSTEM_COMMAND,    // Execute system command
    TIME,              // Get current time
    DATE,              // Get current date
    HELP,              // Show help
    EXIT,              // Exit application
    UNKNOWN            // Unknown command
}
