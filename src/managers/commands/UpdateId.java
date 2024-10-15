package managers.commands;

import classes.Product;
import managers.CollectionManager;
import managers.DBManager;
import system.Request;
import system.Response;
import system.User;

import java.io.Serial;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class UpdateId extends  Command implements Serializable {
    @Serial
    private static final long serialVersionUID = -1115L;

    public UpdateId(){
        super("update_id",false);
    }

    /**
     *  Выводит элементы коллекции в порядке убывания цены.
     * @param request запрос
     * @return ответ
     */
    @Override
    public Response execute(Request request) {
        if (request.getArgs().length<2){
            return new Response("Not enough info for update organization, maybe you forgot login or password");
        }
        User user = request.getUser();
        // Get the collection of organizations
        DBManager dbManager = new DBManager();
        Product[] collection = CollectionManager.getCollection().toArray(new Product[0]);

        String[] args = request.getArgs();
        // Check if the command is entered correctly
        try {
            // Parse the ID from the command-line argument
            int mark = Integer.parseInt(args[1]);
            dbManager.updateObject(mark,user,request.getProduct());
            List list = Arrays.stream(collection).filter(product -> product.getId()==mark).map(product -> request.getProduct()).toList();
            // Iterate through organizations and update the one with the specified ID

            // Display appropriate messages based on the update result
            if (list.isEmpty()) {
                return new Response("Collection does not contain an element with the given id");
            } else {
                CollectionManager.setCollection(dbManager.createCollection());
                return new Response("Element with id: " + mark + " updated successfully");
            }
        } catch (NumberFormatException e) {
            return new Response("Incorrect id");
        }
    }
}
