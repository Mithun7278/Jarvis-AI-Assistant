package com.jarvis;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * Command Processor for Jarvis AI Assistant with TalkBack Support
 * Processes and executes user commands with accessibility features
 */
public class CommandProcessor {
    private TextToSpeech textToSpeech;
    private static final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("EEEE, MMMM dd, yyyy");
    private static final String GREETING_PREFIX = "Hi Sir. ";

    public CommandProcessor(TextToSpeech textToSpeech) {
        this.textToSpeech = textToSpeech;
    }

    /**
     * Process user command with TalkBack support
     */
    public boolean processCommand(String command) {
        if (command == null || command.trim().isEmpty()) {
            return true;
        }

        String lowerCommand = command.toLowerCase();

        // Time command
        if (lowerCommand.contains("time") || lowerCommand.contains("neram")) {
            String time = LocalDateTime.now().format(timeFormatter);
            String response = GREETING_PREFIX + "Idhu " + time;
            textToSpeech.speakTanglish(response);
            System.out.println("[Jarvis]: " + response);
            textToSpeech.announceScreenEvent("Time announced: " + time);
            return true;
        }

        // Date command
        if (lowerCommand.contains("date") || lowerCommand.contains("naal")) {
            String date = LocalDateTime.now().format(dateFormatter);
            String response = GREETING_PREFIX + "Inru " + date;
            textToSpeech.speakTanglish(response);
            System.out.println("[Jarvis]: " + response);
            textToSpeech.announceScreenEvent("Date announced: " + date);
            return true;
        }

        // Greeting commands
        if (lowerCommand.contains("hello") || lowerCommand.contains("hi") || lowerCommand.contains("vanakkam")) {
            String response = GREETING_PREFIX + "Yeppadi irukkai? Enakku yenna thuli seigal patrum?";
            textToSpeech.speakTanglish(response);
            System.out.println("[Jarvis]: " + response);
            textToSpeech.announceScreenEvent("Greeting acknowledged");
            return true;
        }

        // Name query
        if (lowerCommand.contains("your name") || lowerCommand.contains("peru")) {
            String response = GREETING_PREFIX + "En peru Jarvis. Naan oru AI assistant.";
            textToSpeech.speakTanglish(response);
            System.out.println("[Jarvis]: " + response);
            textToSpeech.announceScreenEvent("Name announced");
            return true;
        }

        // Thank you
        if (lowerCommand.contains("thank")) {
            String response = GREETING_PREFIX + "Nandri! Vaalkai!";
            textToSpeech.speakTanglish(response);
            System.out.println("[Jarvis]: " + response);
            textToSpeech.announceScreenEvent("Thank you acknowledged");
            return true;
        }

        // Open Google
        if (lowerCommand.contains("open google")) {
            openBrowser("https://www.google.com");
            String response = GREETING_PREFIX + "Google-ai thira panren.";
            textToSpeech.speakTanglish(response);
            System.out.println("[Jarvis]: " + response);
            textToSpeech.announceScreenEvent("Opening Google");
            return true;
        }

        // Open YouTube
        if (lowerCommand.contains("open youtube")) {
            openBrowser("https://www.youtube.com");
            String response = GREETING_PREFIX + "YouTube-ai thira panren.";
            textToSpeech.speakTanglish(response);
            System.out.println("[Jarvis]: " + response);
            textToSpeech.announceScreenEvent("Opening YouTube");
            return true;
        }

        // How are you
        if (lowerCommand.contains("how are you") || lowerCommand.contains("eppadi")) {
            String response = GREETING_PREFIX + "Naan sari irukren. Nee eppadi irukkai?";
            textToSpeech.speakTanglish(response);
            System.out.println("[Jarvis]: " + response);
            textToSpeech.announceScreenEvent("Status inquiry answered");
            return true;
        }

        // TalkBack toggle
        if (lowerCommand.contains("talkback on")) {
            textToSpeech.setTalkBackEnabled(true);
            String response = GREETING_PREFIX + "TalkBack enabled.";
            textToSpeech.speak(response);
            System.out.println("[Jarvis]: " + response);
            textToSpeech.announceScreenEvent("TalkBack is now enabled");
            return true;
        }

        if (lowerCommand.contains("talkback off")) {
            textToSpeech.setTalkBackEnabled(false);
            String response = "[Jarvis]: Hi Sir. TalkBack disabled.";
            System.out.println(response);
            textToSpeech.announceScreenEvent("TalkBack is now disabled");
            return true;
        }

        // Help
        if (lowerCommand.contains("help")) {
            printHelp();
            String response = GREETING_PREFIX + "Enakku thuli seigal patrum. Yenna vendina sollu?";
            textToSpeech.speakTanglish(response);
            textToSpeech.announceScreenEvent("Help menu displayed");
            return true;
        }

        // Exit/Quit
        if (lowerCommand.contains("exit") || lowerCommand.contains("quit") || lowerCommand.contains("bye") || lowerCommand.contains("varuvom")) {
            String response = GREETING_PREFIX + "Poi varuvom! Pudu naal tappu solluvom!";
            textToSpeech.speakTanglish(response);
            System.out.println("[Jarvis]: " + response);
            textToSpeech.announceScreenEvent("Goodbye. Jarvis shutting down");
            return false;
        }

        // Default: don't respond to unknown commands
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
            textToSpeech.announceScreenEvent("Could not open browser");
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
        System.out.println("\nAccessibility Commands (TalkBack):");
        System.out.println("  - 'talkback on' - Enable TalkBack accessibility");
        System.out.println("  - 'talkback off' - Disable TalkBack accessibility");
        System.out.println("\n================================\n");
    }
}
