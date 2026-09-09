package com.jarvis.system;

import com.jarvis.commands.CommandResult;

/**
 * Interface for System Command Execution
 * 
 * This service handles safe execution of system commands.
 * Dangerous operations must use an allowlist.
 */
public interface SystemCommandService {
    /**
     * Execute a safe system command
     * 
     * @param command The command to execute
     * @return The result of command execution
     * @throws Exception if execution fails
     */
    CommandResult execute(String command) throws Exception;
    
    /**
     * Check if a command is allowed
     * 
     * @param command The command to check
     * @return true if the command is allowed
     */
    boolean isAllowed(String command);
}
