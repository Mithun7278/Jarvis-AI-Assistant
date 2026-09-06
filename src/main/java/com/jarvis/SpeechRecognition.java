package com.jarvis;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * Speech Recognition Engine for Jarvis AI Assistant
 */
public class SpeechRecognition {
    private Scanner scanner;

    public SpeechRecognition() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Listen for voice input (simulated with text input)
     */
    public String listen() {
        try {
            System.out.print("\n[Listening...] ");
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
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
     * Recognize speech using external tool
     */
    public String recognizeViaRecorder() {
        try {
            // This would use external tools like SoX or ffmpeg for actual voice recognition
            System.out.println("[Recording...]");
            Thread.sleep(3000);
            System.out.println("[Processing audio...]");
            return listen();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return "";
        }
    }

    /**
     * Close scanner
     */
    public void close() {
        if (scanner != null) {
            scanner.close();
        }
    }
}
