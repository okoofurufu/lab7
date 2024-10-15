package classes;

import classes.generators.IdGenerator;
import exceptions.InvalidInputException;
import exceptions.Validator;

import java.io.Serial;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

/**
 * Класс, представляющий продукт.
 */
public class Product implements Comparable<Product>, Serializable {
    @Serial
    private static final long serialVersionUID = -220L;
    private Integer id; // Поле не может быть null, значение должно быть больше 0, уникальное, генерируется автоматически
    private String name; // Поле не может быть null, строка не может быть пустой
    private Coordinates coordinates; // Поле не может быть null
    private Date creationDate; // Поле не может быть null, значение генерируется автоматически
    private int price; // Значение должно быть больше 0
    private UnitOfMeasure unitOfMeasure; // Поле может быть null
    private Organization manufacturer; // Поле может быть null
    private int ownerId;

    SimpleDateFormat sdf = new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);


    /**
     * Конструктор для создания нового продукта из массива данных.
     * @param data массив данных
     * @throws InvalidInputException если данные некорректны
     */
    public Product(String[] data) throws InvalidInputException {
        Validator.notNullint(data[1], "id");
        Validator.inputIsEmpty(data[2], "name");
        Validator.notNullLong(data[3], "x");
        Validator.notNullint(data[4], "y");
        Validator.notNullint(data[5], "price");
        Validator.unitOfMeasureIsRight(data[6]);
        Validator.notNullLong(data[7], "id");
        Validator.inputIsEmpty(data[8], "name");
        Validator.inputIsEmpty(data[9], "fullname");
        Validator.organizationTypeIsRight(data[10]);

        this.id = Integer.parseInt(data[1]);
        this.name = data[2];
        this.coordinates = new Coordinates(Long.parseLong(data[3]), Integer.parseInt(data[4]));
        this.creationDate = new Date();
        this.price =Integer.parseInt(data[5]);
        this.unitOfMeasure = UnitOfMeasure.valueOf(data[6]);
        this.manufacturer = new Organization(Long.parseLong(data[7]), data[8], data[9],OrganizationType.valueOf(data[10]));
    }

    /**
     * Пустой конструктор для создания пустого продукта.
     */
    public Product(){
        this.id = IdGenerator.generateid();
        this.name = null;
        this.coordinates = null;
        this.creationDate = new Date();
        this.price = 0;
        this.unitOfMeasure = null;
        this.manufacturer = null;
    }

    public Product(int id, String name, Coordinates coordinates, String creationDate, int price, UnitOfMeasure unitOfMeasure, Organization manufacturer) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        try{
            this.creationDate = sdf.parse(creationDate);
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("Wrong date value");
        }
        this.price = price;
        this.unitOfMeasure = unitOfMeasure;
        this.manufacturer = manufacturer;
    }

    /**
     * Конструктор для создания продукта с заданным идентификатором.
     * @param id идентификатор продукта
     */
    public Product(int id){
        this.id = id;
        this.name = null;
        this.coordinates = null;
        this.creationDate = new Date();
        this.price = 0;
        this.unitOfMeasure = null;
        this.manufacturer = null;
    }

    /**
     * Переопределение метода toString().
     * @return строковое представление объекта
     */
    @Override
    public String toString(){
        return "Product{" +
                "id=" + id +
                "name= '" + name + "'" +
                "cordinate_x=" + coordinates.getX() +
                "coordinate_y=" + coordinates.getY() +
                "creation_date=" + creationDate +
                "price=" + price +
                "unit_of_measure=" + unitOfMeasure +
                "org_id=" + manufacturer.getId() +
                "org_name=" + manufacturer.getName() +
                "org_fullname=" + manufacturer.getFullName() +
                "org_type=" + manufacturer.getType() +
                "}";
    }

    /**
     * Устанавливает имя продукта.
     *
     * @param name имя продукта
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Устанавливает идентификатор продукта.
     *
     * @param id идентификатор продукта
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Возвращает имя продукта.
     *
     * @return имя продукта
     */
    public String getName() {
        return name;
    }

    /**
     * Возвращает идентификатор продукта.
     *
     * @return идентификатор продукта
     */
    public Integer getId() {
        return id;
    }

    /**
     * Возвращает дату создания продукта.
     *
     * @return дата создания продукта
     */
    public Date getCreationDate() {
        return creationDate;
    }

    /**
     * Устанавливает дату создания продукта.
     *
     * @param creationDate дата создания продукта
     */
    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = this.creationDate;
    }

    /**
     * Возвращает координаты продукта.
     *
     * @return координаты продукта
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }

    /**
     * Устанавливает координаты продукта.
     *
     * @param coordinates координаты продукта
     */
    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    /**
     * Возвращает цену продукта.
     *
     * @return цена продукта
     */
    public int getPrice() {
        return price;
    }

    /**
     * Устанавливает цену продукта.
     *
     * @param price цена продукта
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * Возвращает единицы измерения продукта.
     *
     * @return единицы измерения продукта
     */
    public UnitOfMeasure getUnitOfMeasure() {
        return unitOfMeasure;
    }

    /**
     * Устанавливает единицы измерения продукта.
     *
     * @param unitOfMeasure единицы измерения продукта
     */
    public void setUnitOfMeasure(UnitOfMeasure unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }

    /**
     * Возвращает производителя продукта.
     *
     * @return производитель продукта
     */
    public Organization getManufacturer() {
        return manufacturer;
    }

    /**
     * Устанавливает производителя продукта.
     *
     * @param manufacturer производитель продукта
     */
    public void setManufacturer(Organization manufacturer) {
        this.manufacturer = manufacturer;
    }

    /**
     * Преобразует данные о продукте в формат XML.
     *
     * @return данные о продукте в формате XML
     */
    public String toXML() {
        return "id=\"" + id + "\"" +
                " name=\"" + name + "\"" +
                " x=\"" + coordinates.getX() + "\"" +
                " y=\"" + coordinates.getY() + "\"" +
                " creationDate=\"" + creationDate + "\"" +
                " price=\"" + price + "\"" +
                " unit_of_measure=\"" + unitOfMeasure + "\"" +
                " org_id=\"" + manufacturer.getId() + "\"" +
                " org_name=\"" + manufacturer.getName() + "\"" +
                " org_full_name=\"" + manufacturer.getFullName() + "\"" +
                " org_type=\"" + manufacturer.getType() + "\"";
    }

    /** Переопределение compareTo()
     *
     * @param product the object to be compared.
     * @return отсортированные данные
     */
    @Override
    public int compareTo(Product product) {
        int priceComparison = Double.compare(this.price, product.getPrice());

        if (priceComparison == 0) {
            return this.name.compareTo(product.getName());
        } else {
            return priceComparison;
        }
    }
    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }
    public int getOwnerId() {
        return ownerId;
    }


}

