package com.jarvis;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * Speech Recognition Engine with TalkBack Accessibility
 * Listens for voice input and provides accessibility feedback
 */
public class SpeechRecognition {
    private Scanner scanner;
    private TextToSpeech textToSpeech;
    private boolean talkBackEnabled;

    public SpeechRecognition(TextToSpeech textToSpeech) {
        this.scanner = new Scanner(System.in);
        this.textToSpeech = textToSpeech;
        this.talkBackEnabled = true; // TalkBack enabled by default
    }

    /**
     * Enable/Disable TalkBack for voice commands
     */
    public void setTalkBackEnabled(boolean enabled) {
        this.talkBackEnabled = enabled;
        if (enabled) {
            textToSpeech.announceScreenEvent("TalkBack enabled for voice commands");
        }
    }

    /**
     * Listen for voice input (simulated with text input)
     * Provides TalkBack feedback
     */
    public String listen() {
        try {
            // Announce that Jarvis is listening
            if (talkBackEnabled) {
                textToSpeech.announceScreenEvent("Listening for your voice command");
            }
            
            System.out.print("\n[Listening...] ");
            String input = scanner.nextLine().trim();
            
            if (!input.isEmpty()) {
                // Provide accessibility feedback
                if (talkBackEnabled) {
                    textToSpeech.provideFeedback("You said: " + input);
                }
                System.out.println("[You said]: " + input);
                return input.toLowerCase();
            }
            
            return "";
        } catch (Exception e) {
            System.out.println("[Error listening]: " + e.getMessage());
            if (talkBackEnabled) {
                textToSpeech.announceScreenEvent("Error while listening to voice command");
            }
            return "";
        }
    }

    /**
     * Listen for voice input with TalkBack announcement
     */
    public String listenWithAnnouncement(String prompt) {
        try {
            if (talkBackEnabled) {
                textToSpeech.announceScreenEvent(prompt);
            }
            
            System.out.print("\n[Listening...] ");
            String input = scanner.nextLine().trim();
            
            if (!input.isEmpty()) {
                if (talkBackEnabled) {
                    textToSpeech.provideFeedback("Voice command received: " + input);
                }
                System.out.println("[You said]: " + input);
                return input.toLowerCase();
            }
            
            return "";
        } catch (Exception e) {
            System.out.println("[Error listening]: " + e.getMessage());
            return "";
        }
    }

    /**
     * Recognize speech using external tool with TalkBack support
     */
    public String recognizeViaRecorder() {
        try {
            if (talkBackEnabled) {
                textToSpeech.announceScreenEvent("Recording audio");
            }
            
            System.out.println("[Recording...]");
            Thread.sleep(3000);
            
            if (talkBackEnabled) {
                textToSpeech.announceScreenEvent("Processing audio");
            }
            
            System.out.println("[Processing audio...]");
            return listen();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return "";
        }
    }

    /**
     * Close scanner and resources
     */
    public void close() {
        if (scanner != null) {
            scanner.close();
        }
    }

    /**
     * Check if TalkBack is enabled
     */
    public boolean isTalkBackEnabled() {
        return talkBackEnabled;
    }
}
