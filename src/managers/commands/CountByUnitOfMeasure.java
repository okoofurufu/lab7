package managers.commands;

import exceptions.InvalidInputException;
import managers.CollectionManager;
import system.Request;
import system.Response;

import java.io.Serial;
import java.io.Serializable;

public class CountByUnitOfMeasure extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = -113L;

    public CountByUnitOfMeasure(){
        super("count_by_unit_of_measure",false);
    }

    /**
     *  Выводит элементы коллекции в порядке убывания цены.
     * @param request запрос
     * @return ответ
     */
    @Override
    public Response execute(Request request) throws InvalidInputException {
        String unit = request.getArgs()[1];
        return CollectionManager.countByUnitOfMeasure(unit);
    }
}
