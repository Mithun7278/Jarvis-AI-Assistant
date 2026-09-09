package com.jarvis.voice;

/**
 * Interface for Speech-to-Text services
 * 
 * Implementations of this interface can be swapped without changing core logic.
 * Examples: Google Cloud Speech-to-Text, OpenAI Whisper, local recognition, etc.
 */
public interface SpeechToText {
    /**
     * Transcribe audio from microphone to text
     * 
     * @return The transcribed text
     * @throws Exception if transcription fails
     */
    String transcribe() throws Exception;
    
    /**
     * Transcribe audio to text with specified language
     * 
     * @param languageCode Language code (e.g., "en", "ta", "hi")
     * @return The transcribed text
     * @throws Exception if transcription fails
     */
    String transcribe(String languageCode) throws Exception;
}
