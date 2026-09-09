package com.jarvis.intent;

/**
 * Enumeration of all possible user intents
 */
public enum Intent {
    // Time and Date
    GET_TIME,
    GET_DATE,
    
    // Information
    GET_NEWS,
    WEB_SEARCH,
    CONVERSATION,
    
    // Memory
    SAVE_MEMORY,
    RETRIEVE_MEMORY,
    DELETE_MEMORY,
    
    // System
    HELP,
    EXIT,
    
    // Unknown
    UNKNOWN
}
