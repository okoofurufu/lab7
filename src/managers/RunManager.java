package managers;


import exceptions.InvalidInputException;
import exceptions.RootException;
import exceptions.UnknownCommandException;
import system.Request;
import system.Response;

import java.io.IOException;
import java.util.Set;

public class RunManager {
    private final CommandManager commandManager;
    public RunManager(CommandManager commandManager){
        this.commandManager = commandManager;
    }
    public Response run(Request request) throws UnknownCommandException, InvalidInputException, IOException, RootException {
        return CommandManager.startExecuting(request);
    }
}
