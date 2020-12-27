package ui.processor;
/**
 *
 * Base interface for processing commands
 *
 * @author  Arshak Papoyan
 * @version 1.0
 * @since   25.12.2020
 * @see AuthCommandProcessor
 * @see MainCommandsProcessor
 */
public interface CommandsProcessor {

    /**
     * Process commands imputed by the user
     * @param command user command number
     * @throws Exception if command is invalid
     */
    void processMainCommands(String command) throws Exception;

}
