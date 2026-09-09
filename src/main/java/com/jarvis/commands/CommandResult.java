package com.jarvis.commands;

/**
 * Result of command execution
 */
public class CommandResult {
    private final CommandType commandType;
    private final boolean success;
    private final String message;
    private final Object data;
    
    public CommandResult(CommandType commandType, boolean success,
                        String message) {
        this(commandType, success, message, null);
    }
    
    public CommandResult(CommandType commandType, boolean success,
                        String message, Object data) {
        this.commandType = commandType;
        this.success = success;
        this.message = message;
        this.data = data;
    }
    
    public CommandType getCommandType() {
        return commandType;
    }
    
    public boolean isSuccess() {
        return success;
    }
    
    public String getMessage() {
        return message;
    }
    
    public Object getData() {
        return data;
    }
    
    @Override
    public String toString() {
        return "CommandResult{" +
               "type=" + commandType +
               ", success=" + success +
               ", message='" + message + '\'' +
               '}';
    }
}
