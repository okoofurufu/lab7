package managers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import exceptions.InvalidInputException;
import exceptions.RootException;
import exceptions.UnknownCommandException;
import managers.commands.*;
import system.Request;
import system.Response;

import javax.lang.model.element.UnknownElementException;

/**
 * Менеджер команд, который обрабатывает ввод пользователя и запускает соответствующие команды.
 */
public class CommandManager {
    private static Map<String, Command> commandList;

    /**
     * Конструктор по умолчанию. Инициализирует список команд и добавляет их в карту.
     */
    public CommandManager() {
        commandList = new HashMap<>();
        commandList.put("help", new Help());
        commandList.put("info", new Info());
        commandList.put("show", new Show());
        commandList.put("clear", new Clear());
        commandList.put("print_descending", new PrintDescending());
        commandList.put("remove_first", new RemoveFirst());
        commandList.put("shuffle", new Shuffle());
        commandList.put("sort", new Sort());
        commandList.put("add", new Add());
        commandList.put("remove_by_id", new RemoveById());
        commandList.put("execute_script", new ExecuteScript());
        commandList.put("update", new UpdateId());
        commandList.put("filter_contains_name", new FilterContainsName());
        commandList.put("count_by_unit_of_measure", new CountByUnitOfMeasure());
        commandList.put("register", new Register());
        commandList.put("authorization", new Authorization());
        commandList.put("exit_from_account", new ExitFromAccount());
    }

    /**
     *  Запускает команду.
     * @param request Запрос
     * @return Ответ
     * @throws UnknownCommandException Если команда неизвестна
     */
    public static Response startExecuting(Request request) throws UnknownCommandException, InvalidInputException, IOException, RootException {
        Command command = commandList.get(request.getArgs()[0]);
        if (command== null){
            return new Response("такой комманды нет");
        }
        else {
            if (command.isHasArguments() && request.getArgs().length<2){
                return new Response("не хватает аргументов для команды: " + command.getName());
            }
            return command.execute(request);
        }
    }

    /**
     * Возвращает карту команд.
     * @return Карта команд
     */
    public static Map<String, Command> getCommandList() {
        return commandList;
    }

}

