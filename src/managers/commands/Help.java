package managers.commands;

import managers.CommandManager;
import system.Request;
import system.Response;

import java.io.Serial;
import java.io.Serializable;
import java.util.Map;

public class Help extends Command implements Serializable {

    /**
     * Executes the "help" command, displaying information about available commands and their usage.
     * @param args Command-line arguments (not used in this implementation).
     */
    @Serial
    private static final long serialVersionUID = -116L;
    public Help(){
        super("help", false);
    }
    @Override
    public Response execute(Request request) {
        return new Response("\n+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+\n" +
                "help - вывести справку по доступным командам\n" +
                "info - вывести информацию о коллекции\n" +
                "show - вывести все элементы коллекции в строковом представлении\n" +
                "add {element} - добавить новый элемент в коллекцию\n" +
                "update id {element} - обновить значение элемента коллекции, id которого равен задванному\n" +
                "remove_by_id id - удалить элемент из коллекции по его id\n" +
                "clear - очистить коллекцию\n" +
                "execute_script file_name - считать и исполнить скрипт из указанного файла\n" +
                "exit - завершить программу\n" +
                "remove_first - удалить первый элемент из коллекции\n" +
                "filter_contains_name name - вывести элементы, значение поля name которых содержит заданную строку\n" +
                "print_descending - вывести элементы коллекции в порядке убывания\n" +
                "sort - вывести элементы коллекции в порядке возрастания\n" +
                "shuffle - перемешать элементы коллекции\n" +
                "register [логин] [пароль]- создать нового пользователя\n" +
                "autorization [логин] [пароль]- авторизация пользователя\n" +
                "exit_from_account - выход из текущего аккаунта\n");
    }
}
