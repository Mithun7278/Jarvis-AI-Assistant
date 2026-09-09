package com.jarvis.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utility class for logging
 */
public class LoggerUtil {
    /**
     * Get logger for a class
     */
    public static Logger getLogger(Class<?> clazz) {
        return LoggerFactory.getLogger(clazz);
    }
    
    /**
     * Log and print message
     */
    public static void logAndPrint(Logger logger, String message) {
        logger.info(message);
        System.out.println("[INFO] " + message);
    }
}
