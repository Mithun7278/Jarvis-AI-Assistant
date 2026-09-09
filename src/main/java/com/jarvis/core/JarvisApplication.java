package com.jarvis.core;

import com.jarvis.config.JarvisConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Main JARVIS Application Class
 * 
 * This class orchestrates the entire JARVIS assistant lifecycle.
 * It manages initialization, command loop, and shutdown.
 */
public class JarvisApplication {
    private static final Logger logger = LoggerFactory.getLogger(JarvisApplication.class);
    
    private final JarvisConfig config;
    private final AssistantContext context;
    private final ConversationManager conversationManager;
    private boolean running;
    
    /**
     * Initialize JARVIS Application
     */
    public JarvisApplication(JarvisConfig config) {
        this.config = config;
        this.context = new AssistantContext();
        this.conversationManager = new ConversationManager();
        this.running = false;
        
        logger.info("JARVIS Application initialized");
    }
    
    /**
     * Start the JARVIS application
     */
    public void start() {
        try {
            logger.info("Starting JARVIS...");
            this.running = true;
            
            // Print welcome message
            printWelcome();
            
            // Initialize services
            initializeServices();
            
            // Main command loop
            commandLoop();
            
        } catch (Exception e) {
            logger.error("Error during application execution", e);
            throw new RuntimeException("JARVIS application failed", e);
        } finally {
            shutdown();
        }
    }
    
    /**
     * Initialize all services
     */
    private void initializeServices() {
        logger.info("Initializing services...");
        // Services will be initialized in later phases
        logger.info("Services initialized successfully");
    }
    
    /**
     * Main command loop
     */
    private void commandLoop() {
        logger.info("Entering command loop");
        System.out.println("\n[Ready] Type 'help' for commands or 'exit' to quit\n");
        
        while (running) {
            try {
                // Get user input
                System.out.print("jarvis> ");
                String input = readUserInput();
                
                if (input.isEmpty()) {
                    continue;
                }
                
                logger.debug("Received input: {}", input);
                
                // Check for exit
                if ("exit".equalsIgnoreCase(input) || "quit".equalsIgnoreCase(input)) {
                    System.out.println("\n[JARVIS] Goodbye, sir.");
                    this.running = false;
                    break;
                }
                
                // Check for help
                if ("help".equalsIgnoreCase(input)) {
                    printHelp();
                    continue;
                }
                
                // Echo input for now (Phase 2 will add command routing)
                System.out.println("[JARVIS] Hi sir. You said: " + input);
                
            } catch (Exception e) {
                logger.error("Error processing command", e);
                System.out.println("[ERROR] " + e.getMessage());
            }
        }
    }
    
    /**
     * Read user input from console
     */
    private String readUserInput() {
        try {
            java.io.BufferedReader reader = new java.io.BufferedReader(
                new java.io.InputStreamReader(System.in)
            );
            return reader.readLine().trim();
        } catch (java.io.IOException e) {
            logger.error("Error reading user input", e);
            return "";
        }
    }
    
    /**
     * Shutdown the application
     */
    private void shutdown() {
        logger.info("Shutting down JARVIS...");
        this.running = false;
        System.out.println("\n[JARVIS] Application terminated.");
        logger.info("JARVIS shutdown complete");
    }
    
    /**
     * Print welcome message
     */
    private void printWelcome() {
        System.out.println("\n" +
            "╔════════════════════════════════════════════════════════════════════════╗\n" +
            "║                                                                        ║\n" +
            "║              JARVIS - Personal AI Assistant v2.0.0                     ║\n" +
            "║                                                                        ║\n" +
            "║  A Java-based conversational AI with voice, memory, and web access     ║\n" +
            "║                                                                        ║\n" +
            "║         Powered by modular architecture and clean interfaces           ║\n" +
            "║                                                                        ║\n" +
            "╚════════════════════════════════════════════════════════════════════════╝\n");
    }
    
    /**
     * Print help message
     */
    private void printHelp() {
        System.out.println("\n" +
            "JARVIS Commands:\n" +
            "  help               - Show this help message\n" +
            "  exit / quit        - Exit the application\n" +
            "\n" +
            "Phase 1 Status: Core architecture initialized.\n" +
            "Upcoming features: Voice input, AI processing, web search, memory, news\n");
    }
    
    /**
     * Check if application is running
     */
    public boolean isRunning() {
        return running;
    }
}
