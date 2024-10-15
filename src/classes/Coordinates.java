package classes;

import java.io.Serial;
import java.io.Serializable;

/**
 * Класс, представляющий координаты.
 */
public class Coordinates implements Serializable {

    @Serial
    private static final long serialVersionUID = -221L;
    /**
     * Координата X.
     * Поле не может быть null.
     */
    private Long x;

    /**
     * Координата Y.
     */
    private int y;

    /**
     * Конструктор класса Coordinates.
     *
     * @param x Координата X.
     * @param y Координата Y.
     */
    public Coordinates(Long x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Возвращает координату X.
     *
     * @return Координата X.
     */
    public Long getX() {
        return x;
    }

    /**
     * Возвращает координату Y.
     *
     * @return Координата Y.
     */
    public int getY() {
        return y;
    }

    /**
     * Устанавливает координату X.
     *
     * @param x Координата X.
     */
    public void setX(Long x) {
        this.x = x;
    }

    /**
     * Устанавливает координату Y.
     *
     * @param y Координата Y.
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Вычисляет вектор по координатам.
     *
     * @param x Координата X.
     * @param y Координата Y.
     * @return Вектор.
     */
    public int getVector(Long x, int y) {
        return Integer.parseInt(String.valueOf(Math.sqrt(x * x + y * y)));
    }
}
