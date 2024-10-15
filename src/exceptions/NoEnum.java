package exceptions;

/**
 * Исключение, выбрасываемое при отсутствии перечислимого типа.
 */
public class NoEnum extends Throwable {

    /**
     * Конструктор с параметром для создания объекта NoEnum с сообщением об ошибке.
     *
     * @param word Строка, описывающая отсутствующий перечислимый тип.
     */
    public NoEnum(String word) {
        super("Типа " + word + "не существует");
    }

}