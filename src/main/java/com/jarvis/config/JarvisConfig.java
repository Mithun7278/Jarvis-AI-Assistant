package com.jarvis.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * Configuration loader for JARVIS
 * 
 * Loads configuration from environment variables and .env file.
 * Sensitive information like API keys should NOT be hardcoded.
 */
public class JarvisConfig {
    private static final Logger logger = LoggerFactory.getLogger(JarvisConfig.class);
    private static final String ENV_FILE = ".env";
    
    private final Map<String, String> properties;
    
    private JarvisConfig(Map<String, String> properties) {
        this.properties = properties;
    }
    
    /**
     * Load configuration from environment and .env file
     */
    public static JarvisConfig load() {
        Map<String, String> properties = new HashMap<>();
        
        // Load from environment variables
        loadFromEnvironment(properties);
        
        // Load from .env file if it exists
        loadFromEnvFile(properties);
        
        logger.info("Configuration loaded with {} properties", properties.size());
        return new JarvisConfig(properties);
    }
    
    /**
     * Load configuration from environment variables
     */
    private static void loadFromEnvironment(Map<String, String> properties) {
        System.getenv().forEach((key, value) -> {
            if (key.startsWith("JARVIS_")) {
                String configKey = key.substring(7).toLowerCase();
                properties.put(configKey, value);
            }
        });
    }
    
    /**
     * Load configuration from .env file
     */
    private static void loadFromEnvFile(Map<String, String> properties) {
        try {
            if (Files.exists(Paths.get(ENV_FILE))) {
                Files.lines(Paths.get(ENV_FILE))
                    .filter(line -> !line.isEmpty() && !line.startsWith("#"))
                    .forEach(line -> {
                        String[] parts = line.split("=", 2);
                        if (parts.length == 2) {
                            String key = parts[0].trim();
                            String value = parts[1].trim();
                            if (key.startsWith("JARVIS_")) {
                                String configKey = key.substring(7).toLowerCase();
                                properties.put(configKey, value);
                            }
                        }
                    });
                logger.info(".env file loaded");
            }
        } catch (IOException e) {
            logger.warn("Could not load .env file: {}", e.getMessage());
        }
    }
    
    /**
     * Get configuration value
     */
    public String get(String key) {
        return properties.get(key);
    }
    
    /**
     * Get configuration value with default
     */
    public String get(String key, String defaultValue) {
        return properties.getOrDefault(key, defaultValue);
    }
    
    /**
     * Get configuration value as boolean
     */
    public boolean getBoolean(String key, boolean defaultValue) {
        String value = get(key);
        if (value == null) {
            return defaultValue;
        }
        return "true".equalsIgnoreCase(value) || "yes".equalsIgnoreCase(value);
    }
    
    /**
     * Check if configuration key exists
     */
    public boolean has(String key) {
        return properties.containsKey(key);
    }
}
