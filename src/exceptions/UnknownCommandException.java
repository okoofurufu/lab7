package exceptions;

/**
 * Исключение, которое выбрасывается, когда вводится неизвестная команда.
 */
public class UnknownCommandException extends Throwable {

    /**
     * Конструктор с параметром для создания объекта UnknownCommandException с указанием имени неизвестной команды.
     *
     * @param commandName Имя неизвестной команды.
     */
    public UnknownCommandException(String commandName) {
        super("Неизвестная команда: " + commandName);
    }
}
