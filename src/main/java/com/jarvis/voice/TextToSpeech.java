package com.jarvis.voice;

/**
 * Interface for Text-to-Speech services
 * 
 * Implementations of this interface can be swapped without changing core logic.
 * Examples: Google Cloud TTS, espeak, local synthesis, etc.
 */
public interface TextToSpeech {
    /**
     * Speak the provided text
     * 
     * @param text The text to speak
     * @throws Exception if speech synthesis fails
     */
    void speak(String text) throws Exception;
    
    /**
     * Speak text in specified language
     * 
     * @param text The text to speak
     * @param languageCode Language code (e.g., "en", "ta", "hi")
     * @throws Exception if speech synthesis fails
     */
    void speak(String text, String languageCode) throws Exception;
}
