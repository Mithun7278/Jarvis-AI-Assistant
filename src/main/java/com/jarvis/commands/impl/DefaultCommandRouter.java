package com.jarvis.commands.impl;

import com.jarvis.commands.CommandRouter;
import com.jarvis.commands.CommandResult;
import com.jarvis.commands.CommandType;
import com.jarvis.core.AssistantContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Default Command Router Implementation
 * 
 * Routes user input to appropriate command handlers based on intent detection.
 * This implementation uses keyword matching and can be replaced with ML-based intent detection.
 */
public class DefaultCommandRouter implements CommandRouter {
    private static final Logger logger = LoggerFactory.getLogger(DefaultCommandRouter.class);
    
    // Intent keyword patterns
    private static final Set<String> TIME_KEYWORDS = new HashSet<>(Arrays.asList(
        "time", "what time", "current time", "what's the time",
        "neram", "yenna neram"
    ));
    
    private static final Set<String> DATE_KEYWORDS = new HashSet<>(Arrays.asList(
        "date", "what date", "current date", "what's the date", "today",
        "naal", "inru yenna naal"
    ));
    
    private static final Set<String> HELP_KEYWORDS = new HashSet<>(Arrays.asList(
        "help", "what can you do", "commands", "available commands",
        "sollungal"
    ));
    
    private static final Set<String> EXIT_KEYWORDS = new HashSet<>(Arrays.asList(
        "exit", "quit", "goodbye", "bye", "leave",
        "poi varum", "varuvom", "exit jarvis"
    ));
    
    private static final Set<String> MEMORY_SAVE_KEYWORDS = new HashSet<>(Arrays.asList(
        "remember", "save this", "keep this", "store this", "don't forget",
        "mulikki vachen", "save pannu", "remember pannu"
    ));
    
    private static final Set<String> MEMORY_RETRIEVE_KEYWORDS = new HashSet<>(Arrays.asList(
        "remind me", "what do you remember", "tell me", "recall",
        "enakku enna mulikka irukku", "yenna save pannen"
    ));
    
    private static final Set<String> MEMORY_DELETE_KEYWORDS = new HashSet<>(Arrays.asList(
        "forget", "delete this", "remove this", "forget this",
        "maranthidum", "delete pannu"
    ));
    
    private static final Set<String> NEWS_KEYWORDS = new HashSet<>(Arrays.asList(
        "news", "latest news", "today's news", "what's happening", "breaking news",
        "current news", "updates",
        "news enna", "aaj ka news", "latest news"
    ));
    
    private static final Set<String> SEARCH_KEYWORDS = new HashSet<>(Arrays.asList(
        "search", "find", "look for", "search for", "look up",
        "search the web", "internet search",
        "thagaipu", "search pannu"
    ));
    
    private static final Set<String> CONVERSATION_KEYWORDS = new HashSet<>(Arrays.asList(
        "hello", "hi", "how are you", "what are you", "who are you",
        "explain", "tell me", "what is", "how does",
        "vanakkam", "eppadi irukkai", "nee yaaru"
    ));
    
    public DefaultCommandRouter() {
        logger.info("DefaultCommandRouter initialized");
    }
    
    @Override
    public CommandResult route(String userInput, AssistantContext context) throws Exception {
        if (userInput == null || userInput.trim().isEmpty()) {
            return new CommandResult(CommandType.UNKNOWN, false, "Empty input");
        }
        
        String normalizedInput = normalizeInput(userInput);
        logger.debug("Routing input: {} -> normalized: {}", userInput, normalizedInput);
        
        // Detect command type
        CommandType commandType = detectCommandType(normalizedInput);
        logger.debug("Detected command type: {}", commandType);
        
        // Route to appropriate handler
        switch (commandType) {
            case TIME:
                return handleTimeCommand(context);
            case DATE:
                return handleDateCommand(context);
            case HELP:
                return handleHelpCommand(context);
            case EXIT:
                return handleExitCommand(context);
            case MEMORY_SAVE:
                return handleMemorySaveCommand(userInput, context);
            case MEMORY_RETRIEVE:
                return handleMemoryRetrieveCommand(userInput, context);
            case MEMORY_DELETE:
                return handleMemoryDeleteCommand(userInput, context);
            case NEWS:
                return handleNewsCommand(userInput, context);
            case WEB_SEARCH:
                return handleWebSearchCommand(userInput, context);
            case CONVERSATION:
                return handleConversationCommand(userInput, context);
            case UNKNOWN:
            default:
                return handleUnknownCommand(userInput, context);
        }
    }
    
    /**
     * Detect command type from normalized input
     */
    private CommandType detectCommandType(String normalizedInput) {
        // Check specific keywords first (more precise)
        if (matchesAnyKeyword(normalizedInput, EXIT_KEYWORDS)) {
            return CommandType.EXIT;
        }
        
        if (matchesAnyKeyword(normalizedInput, HELP_KEYWORDS)) {
            return CommandType.HELP;
        }
        
        if (matchesAnyKeyword(normalizedInput, TIME_KEYWORDS)) {
            return CommandType.TIME;
        }
        
        if (matchesAnyKeyword(normalizedInput, DATE_KEYWORDS)) {
            return CommandType.DATE;
        }
        
        if (matchesAnyKeyword(normalizedInput, MEMORY_SAVE_KEYWORDS)) {
            return CommandType.MEMORY_SAVE;
        }
        
        if (matchesAnyKeyword(normalizedInput, MEMORY_DELETE_KEYWORDS)) {
            return CommandType.MEMORY_DELETE;
        }
        
        if (matchesAnyKeyword(normalizedInput, MEMORY_RETRIEVE_KEYWORDS)) {
            return CommandType.MEMORY_RETRIEVE;
        }
        
        if (matchesAnyKeyword(normalizedInput, NEWS_KEYWORDS)) {
            return CommandType.NEWS;
        }
        
        if (matchesAnyKeyword(normalizedInput, SEARCH_KEYWORDS)) {
            return CommandType.WEB_SEARCH;
        }
        
        if (matchesAnyKeyword(normalizedInput, CONVERSATION_KEYWORDS)) {
            return CommandType.CONVERSATION;
        }
        
        // Default to conversation
        return CommandType.CONVERSATION;
    }
    
    /**
     * Check if input matches any keyword from a set
     */
    private boolean matchesAnyKeyword(String input, Set<String> keywords) {
        for (String keyword : keywords) {
            if (input.contains(keyword)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Normalize input for keyword matching
     */
    private String normalizeInput(String input) {
        return input.toLowerCase().trim();
    }
    
    /**
     * Handle time command
     */
    private CommandResult handleTimeCommand(AssistantContext context) {
        LocalTime now = LocalTime.now();
        String formattedTime = now.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        String response = "Hi sir. Current time is " + formattedTime;
        
        context.setLastResponse(response);
        logger.info("Time command executed: {}", formattedTime);
        
        return new CommandResult(CommandType.TIME, true, response);
    }
    
    /**
     * Handle date command
     */
    private CommandResult handleDateCommand(AssistantContext context) {
        LocalDate today = LocalDate.now();
        String formattedDate = today.format(DateTimeFormatter.ofPattern("EEEE, MMMM dd, yyyy"));
        String response = "Hi sir. Today is " + formattedDate;
        
        context.setLastResponse(response);
        logger.info("Date command executed: {}", formattedDate);
        
        return new CommandResult(CommandType.DATE, true, response);
    }
    
    /**
     * Handle help command
     */
    private CommandResult handleHelpCommand(AssistantContext context) {
        String response = "Hi sir. Available commands:\n" +
                "  • time - Get current time\n" +
                "  • date - Get current date\n" +
                "  • help - Show this help\n" +
                "  • news - Get latest news\n" +
                "  • search - Search the web\n" +
                "  • remember/save - Save to memory\n" +
                "  • remind me/recall - Retrieve from memory\n" +
                "  • forget/delete - Delete from memory\n" +
                "  • exit/quit - Exit application";
        
        context.setLastResponse(response);
        logger.info("Help command executed");
        
        return new CommandResult(CommandType.HELP, true, response);
    }
    
    /**
     * Handle exit command
     */
    private CommandResult handleExitCommand(AssistantContext context) {
        String response = "Hi sir. Goodbye. See you next time.";
        context.setLastResponse(response);
        logger.info("Exit command executed");
        
        return new CommandResult(CommandType.EXIT, true, response);
    }
    
    /**
     * Handle memory save command
     */
    private CommandResult handleMemorySaveCommand(String userInput, AssistantContext context) {
        String response = "Hi sir. Memory save command detected. This feature will be implemented in Phase 9.";
        context.setLastResponse(response);
        logger.info("Memory save command: {}", userInput);
        
        return new CommandResult(CommandType.MEMORY_SAVE, true, response);
    }
    
    /**
     * Handle memory retrieve command
     */
    private CommandResult handleMemoryRetrieveCommand(String userInput, AssistantContext context) {
        String response = "Hi sir. Memory retrieve command detected. This feature will be implemented in Phase 9.";
        context.setLastResponse(response);
        logger.info("Memory retrieve command: {}", userInput);
        
        return new CommandResult(CommandType.MEMORY_RETRIEVE, true, response);
    }
    
    /**
     * Handle memory delete command
     */
    private CommandResult handleMemoryDeleteCommand(String userInput, AssistantContext context) {
        String response = "Hi sir. Memory delete command detected. This feature will be implemented in Phase 9.";
        context.setLastResponse(response);
        logger.info("Memory delete command: {}", userInput);
        
        return new CommandResult(CommandType.MEMORY_DELETE, true, response);
    }
    
    /**
     * Handle news command
     */
    private CommandResult handleNewsCommand(String userInput, AssistantContext context) {
        String response = "Hi sir. News command detected. This feature will be implemented in Phase 8.";
        context.setLastResponse(response);
        logger.info("News command: {}", userInput);
        
        return new CommandResult(CommandType.NEWS, true, response);
    }
    
    /**
     * Handle web search command
     */
    private CommandResult handleWebSearchCommand(String userInput, AssistantContext context) {
        String response = "Hi sir. Web search command detected. This feature will be implemented in Phase 7.";
        context.setLastResponse(response);
        logger.info("Web search command: {}", userInput);
        
        return new CommandResult(CommandType.WEB_SEARCH, true, response);
    }
    
    /**
     * Handle conversation command
     */
    private CommandResult handleConversationCommand(String userInput, AssistantContext context) {
        String response = "Hi sir. You said: " + userInput + ". " +
                "AI conversation will be implemented in Phase 3.";
        context.setLastResponse(response);
        logger.info("Conversation command: {}", userInput);
        
        return new CommandResult(CommandType.CONVERSATION, true, response);
    }
    
    /**
     * Handle unknown command
     */
    private CommandResult handleUnknownCommand(String userInput, AssistantContext context) {
        String response = "Hi sir. I didn't understand that command. Type 'help' for available commands.";
        context.setLastResponse(response);
        logger.warn("Unknown command: {}", userInput);
        
        return new CommandResult(CommandType.UNKNOWN, false, response);
    }
}
