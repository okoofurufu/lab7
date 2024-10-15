package managers.commands;

import managers.CollectionManager;
import system.Request;
import system.Response;

import java.io.Serial;
import java.io.Serializable;

public class PrintDescending extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = -118L;

    public PrintDescending(){
        super("print_descending",false);
    }

    /**
     *  Выводит элементы коллекции в порядке убывания цены.
     * @param request запрос
     * @return ответ
     */
    @Override
    public Response execute(Request request) {
        CollectionManager.printDescending();
         return CollectionManager.show();
    }
}
