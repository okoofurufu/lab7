package managers.commands;

import classes.Product;
import managers.CollectionManager;
import system.Request;
import system.Response;

import java.io.Serial;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class Show extends Command implements Serializable {

    /**
     * Executes the "show" command, displaying the elements of the collection in ascending order of annual turnover.
     * @param args Command-line arguments (not used in this implementation).
     */
    @Serial
    private static final long serialVersionUID = -1112L;
    public Show(){
        super("show",false);
    }
    @Override
    public Response execute(Request request) {
        return CollectionManager.show();
    }
}