package exceptions;

/**
 * Исключение, выбрасываемое при неверном вводе данных.
 */
public class InvalidInputException extends Exception {

    /**
     * Конструктор с параметром для создания объекта InvalidInputException с сообщением об ошибке.
     *
     * @param data Строка с данными, вызвавшими ошибку.
     */
    public InvalidInputException(String data){

    }
}
