package managers.commands;

import exceptions.InvalidInputException;
import exceptions.RootException;
import system.Response;
import system.Request;
import java.io.IOException;
import java.io.Serial;
import java.io.Serializable;

public abstract class Command implements Serializable {
    @Serial
    private static final long serialVersionUID = -110L;
    private final String name;
    private final boolean hasArguments;

    protected Command(String name, boolean hasArguments) {
        this.name = name;
        this.hasArguments = hasArguments;
    }

    public String getName() {
        return name;
    }

    public Response execute(Request request) throws InvalidInputException, IOException, RootException {
        return new Response(null);
    }

    public boolean isHasArguments() {
        return hasArguments;
    }
}
