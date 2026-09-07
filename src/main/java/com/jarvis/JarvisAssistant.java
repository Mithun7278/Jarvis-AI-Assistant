package com.jarvis;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Main Jarvis AI Assistant Class with API Support
 * A complete Java-based AI Assistant with Tanglish support and external APIs
 */
public class JarvisAssistant {
    private TextToSpeech textToSpeech;
    private SpeechRecognition speechRecognition;
    private CommandProcessor commandProcessor;
    private APICommandProcessor apiCommandProcessor;
    private boolean isRunning;

    public JarvisAssistant() {
        this.textToSpeech = new TextToSpeech();
        this.speechRecognition = new SpeechRecognition();
        this.commandProcessor = new CommandProcessor(textToSpeech);
        this.apiCommandProcessor = new APICommandProcessor(textToSpeech);
        this.isRunning = false;
    }

    /**
     * Initialize Jarvis Assistant
     */
    public void initialize() {
        printWelcomeBanner();
        System.out.println("\n[Initializing Jarvis AI Assistant...]");
        
        try {
            Thread.sleep(1000);
            System.out.println("[Systems loaded successfully!]");
            System.out.println("[Ready to assist you!]\n");
            
            // Speak welcome message
            textToSpeech.speakTanglish("Vanakkam! Naan Jarvis. Yeppadi irukkai?");
            System.out.println("[Jarvis]: Vanakkam! Naan Jarvis. Yeppadi irukkai? (Hello! I am Jarvis. How are you?)");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Start Jarvis Assistant
     */
    public void start() {
        this.isRunning = true;
        initialize();
        commandLoop();
    }

    /**
     * Main command loop
     */
    private void commandLoop() {
        System.out.println("\n[Type your commands below. Type 'help' for available commands or 'exit' to quit]");
        
        while (isRunning) {
            try {
                // Listen for user input
                String userCommand = speechRecognition.listen();

                if (userCommand.isEmpty()) {
                    continue;
                }

                // Try API commands first
                if (!apiCommandProcessor.processAPICommand(userCommand)) {
                    this.isRunning = false;
                    break;
                }

                // Then process regular commands
                boolean continueRunning = commandProcessor.processCommand(userCommand);
                
                if (!continueRunning) {
                    this.isRunning = false;
                }

                Thread.sleep(500); // Small delay between commands
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }

        shutdown();
    }

    /**
     * Shutdown Jarvis Assistant
     */
    public void shutdown() {
        System.out.println("\n[Shutting down Jarvis Assistant...]");
        textToSpeech.stop();
        speechRecognition.close();
        System.out.println("[Goodbye! See you next time!]\n");
        System.exit(0);
    }

    /**
     * Print welcome banner
     */
    private void printWelcomeBanner() {
        System.out.println("\n" +
                "╔═══════════════════════════════════════════════════════════╗\n" +
                "║                                                           ║\n" +
                "║         WELCOME TO JARVIS AI ASSISTANT v1.1              ║\n" +
                "║              (With Tanglish & API Support)               ║\n" +
                "║                                                           ║\n" +
                "║  A Complete Java-based AI Assistant                      ║\n" +
                "║  Developed with Tamil/Tanglish Communication             ║\n" +
                "║  Integrated with External APIs (Jokes, Quotes, Weather)  ║\n" +
                "║                                                           ║\n" +
                "╚═══════════════════════════════════════════════════════════╝\n");
        
        System.out.println("[System Info]");
        System.out.println("  OS: " + System.getProperty("os.name"));
        System.out.println("  Java Version: " + System.getProperty("java.version"));
        System.out.println("  Current Time: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }

    /**
     * Get version
     */
    public String getVersion() {
        return "1.1.0";
    }

    /**
     * Get assistant name
     */
    public String getAssistantName() {
        return "Jarvis";
    }

    /**
     * Check if running
     */
    public boolean isRunning() {
        return isRunning;
    }

    /**
     * Main method
     */
    public static void main(String[] args) {
        try {
            JarvisAssistant jarvis = new JarvisAssistant();
            jarvis.start();
        } catch (Exception e) {
            System.err.println("[FATAL ERROR]: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }
}
