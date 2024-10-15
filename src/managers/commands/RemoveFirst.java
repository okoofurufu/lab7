package managers.commands;

import classes.Product;
import managers.CollectionManager;
import managers.DBManager;
import system.Request;
import system.Response;
import system.User;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;

public class RemoveFirst extends Command implements Serializable{
    @Serial
    private static final long serialVersionUID = -1110L;

    public RemoveFirst(){
        super("remove_first",false);
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
        if (CollectionManager.getCollection().size() == 0){
            return new Response("Коллекция пуста");
        }
        else{
            Product product = CollectionManager.getCollection().getFirst();
            int id = product.getId();
            DBManager.deleteObject(id, user);
            CollectionManager.getCollection().removeFirst();
            return new Response("Элемент удален");
        }

    }
}
