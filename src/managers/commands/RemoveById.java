package managers.commands;

import classes.Product;
import exceptions.InvalidInputException;
import managers.CollectionManager;
import managers.DBManager;
import system.Request;
import system.Response;
import system.User;

import java.io.Serial;
import java.io.Serializable;
import java.util.LinkedList;

public class RemoveById extends  Command implements Serializable {
    @Serial
    private static final long serialVersionUID = -119L;

    public RemoveById(){
        super("remove_by_id",true);
    }

    /**
     *  Выводит элементы коллекции в порядке убывания цены.
     * @param request запрос
     * @return ответ
     */
    @Override
    public Response execute(Request request) {
        if (request.getArgs().length<2){
            return new Response("Not enough info for remove organization, maybe you forgot login or password");
        }
        // Get the collection of organizations
        User user = request.getUser();
        String[] args = request.getArgs();
        DBManager dbManager = new DBManager();
        LinkedList<Product> col =CollectionManager.getCollection();
        // Check if the command is entered correctly
        try {
            // Parse the ID from the command-line argument
            int id = Integer.parseInt(args[1]);
            // Display appropriate messages based on the removal result
            if (!dbManager.deleteObject(id, user)) {
                return new Response("Cant delete object");
            } else {
                col.removeIf(organization -> organization.getId()==id);
                CollectionManager.setCollection(col);
                return new Response("Element with id: " + id + " deleted");
            }
        } catch (NumberFormatException e) {
            return new Response("Incorrect id value");
        }
    }
}
