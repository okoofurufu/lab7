package managers.commands;

import managers.CollectionManager;
import system.Request;
import system.Response;

import java.io.Serial;
import java.io.Serializable;

public class Shuffle extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = -1113L;

    public Shuffle(){
        super("shuffle",false);
    }

    /**
     *  Выводит элементы коллекции в порядке убывания цены.
     * @param request запрос
     * @return ответ
     */
    @Override
    public Response execute(Request request) {
        return CollectionManager.shuffleCollection();
    }
}
