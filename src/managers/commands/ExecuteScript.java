package managers.commands;

import managers.CollectionManager;
import system.Request;
import system.Response;

import java.io.FileNotFoundException;
import java.io.Serial;
import java.io.Serializable;

public class ExecuteScript extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = -114L;

    public ExecuteScript(){
        super("execute_script",true);
    }

    /**
     *  Выводит элементы коллекции в порядке убывания цены.
     * @param request запрос
     * @return ответ
     */
    @Override
    public Response execute(Request request) throws FileNotFoundException {
        return CollectionManager.executeScript(request.getArgs()[1]);
    }
}
