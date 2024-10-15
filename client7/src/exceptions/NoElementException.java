package exceptions;

/**
 * Исключение, выбрасываемое при отсутствии элемента с указанным индексом.
 */
public class NoElementException extends Throwable {

    /**
     * Конструктор с параметром для создания объекта NoElementException с сообщением об ошибке.
     *
     * @param id Идентификатор элемента, который не найден.
     */
    public NoElementException(Integer id) {
        super("Нет элемента с данным индексом");
    }
}
