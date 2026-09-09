package com.jarvis;

import com.jarvis.core.JarvisApplication;
import com.jarvis.config.JarvisConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Entry point for JARVIS - Java-Based Personal AI Assistant
 * 
 * This is the main class that initializes and starts the JARVIS application.
 * The application follows a modular architecture with clean interfaces for
 * easy extension and testing.
 */
public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        try {
            logger.info("========================================");
            logger.info("JARVIS - Personal AI Assistant v2.0.0");
            logger.info("========================================");
            
            // Load configuration
            JarvisConfig config = JarvisConfig.load();
            logger.info("Configuration loaded successfully");
            
            // Create and start application
            JarvisApplication application = new JarvisApplication(config);
            logger.info("Initializing JARVIS...");
            
            application.start();
            
        } catch (Exception e) {
            logger.error("Fatal error during application startup", e);
            System.err.println("\n[ERROR] Failed to start JARVIS: " + e.getMessage());
            System.exit(1);
        }
    }
}
