package managers.commands;

import managers.CollectionManager;
import system.Request;
import system.Response;

import java.io.Serial;
import java.io.Serializable;

public class FilterContainsName extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = -115L;

    public FilterContainsName(){
        super("filter_contains_name",true);
    }

    /**
     *  Выводит элементы коллекции в порядке убывания цены.
     * @param request запрос
     * @return ответ
     */
    @Override
    public Response execute(Request request) {
        String name = request.getArgs()[1];
        return CollectionManager.filterContainsName(name);
    }
}
