package managers.commands;

import classes.Product;
import exceptions.InvalidInputException;
import managers.CollectionManager;
import managers.DBManager;
import system.Request;
import system.Response;
import system.User;

import java.io.Serializable;
import java.util.LinkedList;

public class Clear extends Command implements Serializable {
    private static final long serialVersionUID = -112L;

    public Clear(){
        super("clear", false);
    }

    /**
     *  Очищает коллекцию
     * @param request запрос
     * @return ответ
     */
    @Override
    public Response execute(Request request) throws InvalidInputException {
        // Clear the collection of organizations
        User user = request.getUser();
        DBManager dbManager = new DBManager();
        LinkedList<Product> collection =CollectionManager.getCollection();
        for (Product product : collection){
            if(dbManager.deleteObject(product.getId(),user)){
                CollectionManager.removeById(product.getId());
            }
        }
        CollectionManager.setCollection(dbManager.createCollection());
        return new Response("Collection was cleared");

    }
}
