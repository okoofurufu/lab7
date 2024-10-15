package managers.commands;

import classes.Product;
import managers.CollectionManager;
import system.Request;
import system.Response;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.LinkedList;

public class Info extends Command implements Serializable {

    /**
     * Executes the "info" command, displaying information about the collection managed by.
     * @param args Command-line arguments (not used in this implementation).
     */
    @Serial
    private static final long serialVersionUID = -117L;

    public Info(){
        super("info", false);
    }
    @Override
    public Response execute(Request request) {
        return CollectionManager.info();
    }
}
