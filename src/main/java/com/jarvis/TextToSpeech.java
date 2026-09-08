package com.jarvis;

import javax.sound.sampled.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * Text-to-Speech Engine with TalkBack Accessibility Support
 * Provides voice output in multiple languages with accessibility features
 */
public class TextToSpeech {
    private static final String TTS_URL = "https://translate.google.com/translate_tts";
    private Clip audioClip;
    private boolean talkBackEnabled;
    private static final long SPEECH_DELAY = 500; // Delay between announcements

    public TextToSpeech() {
        this.talkBackEnabled = true; // TalkBack enabled by default
    }

    /**
     * Enable/Disable TalkBack accessibility
     */
    public void setTalkBackEnabled(boolean enabled) {
        this.talkBackEnabled = enabled;
    }

    public boolean isTalkBackEnabled() {
        return talkBackEnabled;
    }

    /**
     * Speak text with TalkBack support
     */
    public void speak(String text) {
        if (!talkBackEnabled) {
            System.out.println("[JARVIS]: " + text);
            return;
        }

        try {
            // Add accessibility announcement
            announceForAccessibility("Speaking: " + text);
            
            // Use Java's built-in speech synthesis
            Process process = Runtime.getRuntime().exec(new String[]{
                    "espeak",
                    text
            });
            process.waitFor();
            
            Thread.sleep(SPEECH_DELAY);
        } catch (Exception e) {
            System.out.println("[JARVIS]: " + text);
            System.out.println("[TalkBack]: " + text);
        }
    }

    /**
     * Speak in Tanglish with TalkBack and "Hi Sir" greeting
     */
    public void speakTanglish(String text) {
        if (!talkBackEnabled) {
            System.out.println("[JARVIS]: " + text);
            return;
        }

        try {
            // Announce greeting for accessibility
            announceForAccessibility("Jarvis: Hi Sir");
            
            // Use espeak with Tamil voice
            String tanglishText = TanglishTranslator.translateToTanglish(text);
            
            Process process = Runtime.getRuntime().exec(new String[]{
                    "espeak",
                    "-v", "ta", // Tamil voice
                    "Hi Sir. " + tanglishText
            });
            process.waitFor();
            
            // Log for accessibility
            System.out.println("[TalkBack]: Hi Sir. " + tanglishText);
            Thread.sleep(SPEECH_DELAY);
        } catch (Exception e) {
            String tanglishText = TanglishTranslator.translateToTanglish(text);
            System.out.println("[JARVIS]: Hi Sir. " + tanglishText);
            System.out.println("[TalkBack]: Hi Sir. " + tanglishText);
        }
    }

    /**
     * Speak with custom language and TalkBack support
     */
    public void speak(String text, String language) {
        if (!talkBackEnabled) {
            System.out.println("[JARVIS]: " + text);
            return;
        }

        try {
            String voiceCode = getVoiceCode(language);
            
            // Announce for accessibility
            announceForAccessibility("Speaking in " + language + ": " + text);
            
            Process process = Runtime.getRuntime().exec(new String[]{
                    "espeak",
                    "-v", voiceCode,
                    "Hi Sir. " + text
            });
            process.waitFor();
            
            System.out.println("[TalkBack]: Hi Sir. [" + language + "] " + text);
            Thread.sleep(SPEECH_DELAY);
        } catch (Exception e) {
            System.out.println("[JARVIS]: " + text);
        }
    }

    /**
     * Announce for accessibility (TalkBack)
     * This simulates Android TalkBack announcements
     */
    private void announceForAccessibility(String announcement) {
        if (!talkBackEnabled) {
            return;
        }
        
        // Log accessibility announcement
        System.out.println("\n[ACCESSIBILITY - TalkBack]: " + announcement);
        
        try {
            // Try to use espeak for accessibility announcement
            Runtime.getRuntime().exec(new String[]{
                    "espeak",
                    announcement
            }).waitFor();
        } catch (Exception e) {
            // Silent fail - continue anyway
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

    /**
     * Announce screen event for accessibility
     */
    public void announceScreenEvent(String event) {
        if (!talkBackEnabled) {
            return;
        }
        
        System.out.println("[ACCESSIBILITY - Screen Event]: " + event);
        
        try {
            Runtime.getRuntime().exec(new String[]{
                    "espeak",
                    event
            }).waitFor();
        } catch (Exception e) {
            // Silent fail
        }
    }

    /**
     * Provide voice feedback for user actions
     */
    public void provideFeedback(String feedback) {
        if (!talkBackEnabled) {
            return;
        }
        
        System.out.println("[ACCESSIBILITY - Feedback]: " + feedback);
        announceForAccessibility(feedback);
    }
}
