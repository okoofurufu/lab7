package classes;

import java.io.Serial;
import java.io.Serializable;

/**
 * Класс, представляющий организацию.
 */
public class Organization implements Serializable {
    @Serial
    private static final long serialVersionUID = -222L;
    /**
     * Уникальный идентификатор организации.
     * Поле не может быть null.
     * Значение поля должно быть больше 0.
     * Значение этого поля должно быть уникальным.
     * Значение этого поля должно генерироваться автоматически.
     */
    private Long id;

    /**
     * Название организации.
     * Поле не может быть null.
     * Строка не может быть пустой.
     */
    private String name;

    /**
     * Полное название организации.
     * Значение этого поля должно быть уникальным.
     * Поле может быть null.
     */
    private String fullName;

    /**
     * Тип организации.
     * Поле может быть null.
     */
    private OrganizationType type;

    /**
     * Конструктор класса Organization.
     *
     * @param id       Уникальный идентификатор организации.
     * @param name     Название организации.
     * @param fullName Полное название организации.
     * @param type     Тип организации.
     */
    public Organization(long id, String name, String fullName, OrganizationType type) {
        this.id = id;
        this.name = name;
        this.fullName = fullName;
        this.type = type;
    }

    /**
     * Возвращает уникальный идентификатор организации.
     *
     * @return Уникальный идентификатор организации.
     */
    public Long getId() {
        return id;
    }

    /**
     * Возвращает тип организации.
     *
     * @return Тип организации.
     */
    public OrganizationType getType() {
        return type;
    }

    /**
     * Возвращает полное название организации.
     *
     * @return Полное название организации.
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Возвращает название организации.
     *
     * @return Название организации.
     */
    public String getName() {
        return name;
    }

    /**
     * Устанавливает полное название организации.
     *
     * @param fullName Полное название организации.
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * Устанавливает уникальный идентификатор организации.
     *
     * @param id Уникальный идентификатор организации.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Устанавливает название организации.
     *
     * @param name Название организации.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Устанавливает тип организации.
     *
     * @param type Тип организации.
     */
    public void setType(OrganizationType type) {
        this.type = type;
    }
}

