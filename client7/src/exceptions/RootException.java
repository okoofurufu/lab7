package exceptions;

/**
 * Базовое исключение, от которого наследуются другие пользовательские исключения.
 */
public class RootException extends Exception {

    /**
     * Конструктор с параметром для создания объекта RootException с заданным сообщением об ошибке.
     *
     * @param message Сообщение об ошибке.
     */
    public RootException(String message) {
        super(message);
    }
}
