package com.jarvis;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * Command Processor for Jarvis AI Assistant
 * Processes and executes user commands
 */
public class CommandProcessor {
    private TextToSpeech textToSpeech;
    private static final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("EEEE, MMMM dd, yyyy");

    public CommandProcessor(TextToSpeech textToSpeech) {
        this.textToSpeech = textToSpeech;
    }

    /**
     * Process user command
     */
    public boolean processCommand(String command) {
        if (command == null || command.trim().isEmpty()) {
            return true;
        }

        String lowerCommand = command.toLowerCase();

        // Time command
        if (lowerCommand.contains("time") || lowerCommand.contains("neram")) {
            String time = LocalDateTime.now().format(timeFormatter);
            String response = "Idhu " + time;
            textToSpeech.speakTanglish(response);
            System.out.println("[Jarvis]: " + response);
            return true;
        }

        // Date command
        if (lowerCommand.contains("date") || lowerCommand.contains("naal")) {
            String date = LocalDateTime.now().format(dateFormatter);
            String response = "Inru " + date;
            textToSpeech.speakTanglish(response);
            System.out.println("[Jarvis]: " + response);
            return true;
        }

        // Greeting commands
        if (lowerCommand.contains("hello") || lowerCommand.contains("hi") || lowerCommand.contains("vanakkam")) {
            String response = "Vanakkam! Yeppadi irukkai? Enakku yenna thuli seigal patrum?";
            textToSpeech.speakTanglish(response);
            System.out.println("[Jarvis]: " + response);
            return true;
        }

        // Name query
        if (lowerCommand.contains("your name") || lowerCommand.contains("peru")) {
            String response = "En peru Jarvis. Naan oru AI assistant.";
            textToSpeech.speakTanglish(response);
            System.out.println("[Jarvis]: " + response);
            return true;
        }

        // Thank you
        if (lowerCommand.contains("thank")) {
            String response = "Nandri! Vaalkai!";
            textToSpeech.speakTanglish(response);
            System.out.println("[Jarvis]: " + response);
            return true;
        }

        // Open Google
        if (lowerCommand.contains("open google")) {
            openBrowser("https://www.google.com");
            String response = "Google-ai thira panren.";
            textToSpeech.speakTanglish(response);
            System.out.println("[Jarvis]: " + response);
            return true;
        }

        // Open YouTube
        if (lowerCommand.contains("open youtube")) {
            openBrowser("https://www.youtube.com");
            String response = "YouTube-ai thira panren.";
            textToSpeech.speakTanglish(response);
            System.out.println("[Jarvis]: " + response);
            return true;
        }

        // How are you
        if (lowerCommand.contains("how are you") || lowerCommand.contains("eppadi")) {
            String response = "Naan sari irukren. Nee eppadi irukkai?";
            textToSpeech.speakTanglish(response);
            System.out.println("[Jarvis]: " + response);
            return true;
        }

        // Help
        if (lowerCommand.contains("help")) {
            printHelp();
            String response = "Enakku thuli seigal patrum. Yenna vendina sollu?";
            textToSpeech.speakTanglish(response);
            return true;
        }

        // Exit/Quit
        if (lowerCommand.contains("exit") || lowerCommand.contains("quit") || lowerCommand.contains("bye") || lowerCommand.contains("varuvom")) {
            String response = "Poi varuvom! Pudu naal tappu solluvom!";
            textToSpeech.speakTanglish(response);
            System.out.println("[Jarvis]: " + response);
            return false;
        }

        // Default: don't respond to unknown commands (let API processor handle them)
        return true;
    }

    /**
     * Open browser with URL
     */
    private void openBrowser(String url) {
        try {
            String os = System.getProperty("os.name").toLowerCase();
            if (os.contains("win")) {
                Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + url);
            } else if (os.contains("mac")) {
                Runtime.getRuntime().exec(new String[]{"open", url});
            } else if (os.contains("nix") || os.contains("nux")) {
                Runtime.getRuntime().exec(new String[]{"xdg-open", url});
            }
        } catch (Exception e) {
            System.out.println("[Error]: Could not open browser - " + e.getMessage());
        }
    }

    /**
     * Print help information
     */
    private void printHelp() {
        System.out.println("\n=== JARVIS AI ASSISTANT - HELP ===");
        System.out.println("\nBasic Commands (Tanglish/English):");
        System.out.println("  - 'time' / 'neram' - Get current time");
        System.out.println("  - 'date' / 'naal' - Get current date");
        System.out.println("  - 'hello' / 'vanakkam' - Greet Jarvis");
        System.out.println("  - 'your name' / 'peru' - Ask Jarvis's name");
        System.out.println("  - 'thank you' / 'nandri' - Say thank you");
        System.out.println("  - 'how are you' / 'eppadi' - Ask how Jarvis is");
        System.out.println("  - 'open google' - Open Google in browser");
        System.out.println("  - 'open youtube' - Open YouTube in browser");
        System.out.println("  - 'help' - Show this help menu");
        System.out.println("  - 'bye' / 'exit' / 'quit' - Exit Jarvis");
        System.out.println("\nAPI Commands (External APIs):");
        System.out.println("  - 'joke' - Get a random joke");
        System.out.println("  - 'programming joke' - Get a programming joke");
        System.out.println("  - 'quote' / 'motivation' - Get a motivational quote");
        System.out.println("  - 'weather' - Get weather information (Chennai)");
        System.out.println("\n================================\n");
    }
}
