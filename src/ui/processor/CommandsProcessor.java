package ui.processor;

import exceptions.InvalidCommandException;

public interface CommandsProcessor {

    void processMainCommands(String command) throws Exception;

}
