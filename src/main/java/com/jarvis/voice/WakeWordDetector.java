package com.jarvis.voice;

/**
 * Interface for Wake Word Detection
 * 
 * Implementations of this interface can be swapped without changing core logic.
 * Examples: "Hey JARVIS", "OK Google", custom wake words, etc.
 */
public interface WakeWordDetector {
    /**
     * Listen for the wake word
     * 
     * @return true if wake word is detected, false otherwise
     * @throws Exception if listening fails
     */
    boolean detectWakeWord() throws Exception;
    
    /**
     * Get the wake word phrase
     * 
     * @return The wake word or phrase
     */
    String getWakeWord();
}
