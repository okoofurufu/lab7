package managers.commands;

import classes.Product;
import managers.CollectionManager;
import managers.DBManager;
import system.Request;
import system.Response;
import system.User;

import java.io.Serial;
import java.io.Serializable;

/**
 * Команда для добавления нового элемента в коллекцию.
 */
public class Add extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = -111L;

    public Add(){
        super("add",false);
    }

    /**
     *  Добавляет продукт в коллекцию.
     * @param request запрос
     * @return ответ
     */
    @Override
    public Response execute(Request request) {
        User user = request.getUser();
        DBManager dbManager = new DBManager();
        Product product = request.getProduct();
        if (dbManager.addProduct(product,user)!=-1){
            CollectionManager.setCollection(dbManager.createCollection());
            return new Response("Organization added successfully");
        }
        return new Response("Add failed, try one more time");
    }
}