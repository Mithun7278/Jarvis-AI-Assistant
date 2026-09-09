package com.jarvis.core;

import com.jarvis.commands.CommandResult;
import com.jarvis.commands.CommandRouter;
import com.jarvis.commands.CommandType;
import com.jarvis.commands.impl.DefaultCommandRouter;
import com.jarvis.response.ResponseGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Updated JARVIS Application Class with Command Routing
 * 
 * This class orchestrates the entire JARVIS assistant lifecycle.
 * It manages initialization, command loop, and shutdown.
 * Now includes command routing and response generation.
 */
public class JarvisApplication {
    private static final Logger logger = LoggerFactory.getLogger(JarvisApplication.class);
    
    private final JarvisConfig config;
    private final AssistantContext context;
    private final ConversationManager conversationManager;
    private final CommandRouter commandRouter;
    private final ResponseGenerator responseGenerator;
    private boolean running;
    
    /**
     * Initialize JARVIS Application
     */
    public JarvisApplication(JarvisConfig config) {
        this.config = config;
        this.context = new AssistantContext();
        this.conversationManager = new ConversationManager();
        this.commandRouter = new DefaultCommandRouter();
        this.responseGenerator = new ResponseGenerator();
        this.running = false;
        
        logger.info("JARVIS Application initialized with command routing");
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
            
            // Print greeting
            String greeting = responseGenerator.generateGreeting(context.getUserLanguagePreference());
            System.out.println("\n[JARVIS] " + greeting);
            
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
        System.out.println("[Ready] Type 'help' for commands or 'exit' to quit\n");
        
        while (running) {
            try {
                // Get user input
                System.out.print("jarvis> ");
                String userInput = readUserInput();
                
                if (userInput.isEmpty()) {
                    continue;
                }
                
                logger.debug("Received input: {}", userInput);
                context.setLastUserInput(userInput);
                
                // Route command
                CommandResult result = commandRouter.route(userInput, context);
                logger.debug("Command result: {}", result);
                
                // Check for exit
                if (result.getCommandType() == CommandType.EXIT) {
                    System.out.println("\n[JARVIS] " + result.getMessage());
                    this.running = false;
                    break;
                }
                
                // Display response
                String response = result.getMessage();
                System.out.println("[JARVIS] " + response);
                
                // Record in conversation history
                conversationManager.recordTurn(userInput, response);
                
            } catch (Exception e) {
                logger.error("Error processing command", e);
                String errorResponse = responseGenerator.generateErrorResponse(
                    e.getMessage(),
                    context.getUserLanguagePreference()
                );
                System.out.println("[JARVIS] " + errorResponse);
            }
        }
    }
    
    /**
     * Read user input from console
     */
    private String readUserInput() {
        try {
            BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in)
            );
            String line = reader.readLine();
            return line != null ? line.trim() : "";
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
        logger.info("Conversation history - {} turns recorded", conversationManager.size());
    }
    
    /**
     * Print welcome message
     */
    private void printWelcome() {
        System.out.println("\n" +
            "╔════════════════════════════════════════════════════════════════════════╗\n" +
            "║                                                                        ║\n" +
            "║              JARVIS - Personal AI Assistant v2.0.0                     ║\n" +
            "║                   Phase 2: Command Routing Enabled                       ║\n" +
            "║                                                                        ║\n" +
            "║  A Java-based conversational AI with voice, memory, and web access     ║\n" +
            "║                                                                        ║\n" +
            "║         Powered by modular architecture and clean interfaces           ║\n" +
            "║                                                                        ║\n" +
            "╚════════════════════════════════════════════════════════════════════════╝\n");
    }
    
    /**
     * Check if application is running
     */
    public boolean isRunning() {
        return running;
    }
}
