package classes.generators;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Класс для генерации уникальных идентификаторов.
 */
public class IdGenerator implements Serializable {
    @Serial
    private static final long serialVersionUID = -223L;
    // Список, хранящий сгенерированные идентификаторы
    private static ArrayList<Integer> idList = new ArrayList<>();

    /**
     * Генерирует и возвращает уникальный идентификатор.
     *
     * @return Уникальный идентификатор.
     */
    public static Integer generateid(){
        Integer id = (int)Math.floor(Math.random() * 386800) + 244;
        while (idList.contains(id)){
            id = (int)Math.floor(Math.random() * 386800) + 244;
        }
        idList.add(id);
        return id;
    }

    /**
     * Проверяет, является ли данный идентификатор уникальным.
     *
     * @param id Идентификатор для проверки.
     * @return true, если идентификатор уникальный, иначе false.
     */
    public static boolean idIsUnique(int id){
        return !idList.contains(id);
    }

    /**
     * Удаляет указанный идентификатор из списка сгенерированных.
     *
     * @param id Идентификатор для удаления.
     */
    public static void remove(int id){
        idList.remove(id);
    }

    /**
     * Добавляет указанный идентификатор в список сгенерированных.
     *
     * @param id Идентификатор для добавления.
     */
    public static void insert(int id){
        idList.add(id);
    }
}

