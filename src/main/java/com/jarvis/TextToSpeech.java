package com.jarvis;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * Text-to-Speech Engine for Jarvis AI Assistant
 */
public class TextToSpeech {
    private static final String TTS_URL = "https://translate.google.com/translate_tts";
    private Clip audioClip;

    /**
     * Speak text using system-level TTS
     */
    public void speak(String text) {
        try {
            // Use Java's built-in speech synthesis
            Runtime.getRuntime().exec(new String[]{
                    "espeak",
                    "-v", "ta", // Tamil voice
                    text
            }).waitFor();
        } catch (Exception e) {
            // Fallback: Use system beep
            System.out.println("[JARVIS]: " + text);
            System.out.println("\u0007"); // System beep
        }
    }

    /**
     * Speak in Tanglish
     */
    public void speakTanglish(String text) {
        try {
            // Use espeak with Tamil support
            String tanglishText = TanglishTranslator.translateToTanglish(text);
            Runtime.getRuntime().exec(new String[]{
                    "espeak",
                    "-v", "ta",
                    tanglishText
            }).waitFor();
            System.out.println("[JARVIS (Tanglish)]: " + tanglishText);
        } catch (Exception e) {
            // Fallback: Print to console
            System.out.println("[JARVIS (Tanglish)]: " + text);
        }
    }

    /**
     * Speak with custom language
     */
    public void speak(String text, String language) {
        try {
            String voiceCode = getVoiceCode(language);
            Runtime.getRuntime().exec(new String[]{
                    "espeak",
                    "-v", voiceCode,
                    text
            }).waitFor();
        } catch (Exception e) {
            System.out.println("[JARVIS]: " + text);
        }
    }

    /**
     * Get voice code for language
     */
    private String getVoiceCode(String language) {
        switch (language.toLowerCase()) {
            case "tamil":
            case "ta":
                return "ta";
            case "english":
            case "en":
                return "en";
            case "hindi":
            case "hi":
                return "hi";
            case "telugu":
            case "te":
                return "te";
            default:
                return "en";
        }
    }

    /**
     * Stop current speech
     */
    public void stop() {
        if (audioClip != null && audioClip.isRunning()) {
            audioClip.stop();
        }
    }
}
