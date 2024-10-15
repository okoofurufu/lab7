package managers;

import classes.*;
import classes.generators.ProductGenerator;
import exceptions.*;
import system.Request;
import system.Response;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.time.LocalDate;

/**
 * Менеджер коллекции, который управляет коллекцией объектов класса Product.
 */
public class CollectionManager {
    private static LinkedList<Product> collection;
    private static LocalDate date;

    /**
     * Получает коллекцию.
     *
     * @return Коллекция объектов класса Product.
     */
    public static LinkedList<Product> getCollection() {
        return collection;
    }

    /**
     * Устанавливает новую коллекцию.
     *
     * @param collection Новая коллекция объектов класса Product.
     */
    public static void setCollection(LinkedList<Product> collection) {
        CollectionManager.collection = Objects.requireNonNullElseGet(collection, LinkedList::new);
    }

    public CollectionManager() {
        collection = new LinkedList<>();
        date = LocalDate.now();
    }

    /**
     * Добавляет продукт в коллекцию.
     *
     * @param product Продукт для добавления в коллекцию.
     */
    public static Response add(Product product) {
        collection.add(product);
        return new Response("Product added successfully");
    }

    /**
     * Выводит информацию о коллекции, такую как тип коллекции, дата инициализации и количество элементов.
     */
    public static Response info() {
        return new Response("Type of collection:" + collection.getClass().getSimpleName() + "\nDate of initialization:" + date + "\nNumbers of elements:" + collection.size());
    }

    /**
     * Отображает содержимое коллекции.
     */
    public static Response show() {

        Product[] products = collection.toArray(new Product[0]);
        if (collection.isEmpty()) {
            return new Response("Collection is empty");
        } else {

            int idMax = 0;
            int nameMax = 0;
            int xMax = 0;
            int yMax = 0;
            int priceMax = 0;
            int orgIdMax = 0;
            int orgNameMax = 0;
            int orgFullNameMax = 0;

            for (Product product : collection) {

                idMax = Math.max(idMax, String.valueOf(product.getId()).length());
                nameMax = Math.max(nameMax, product.getName().length());
                xMax = Math.max(xMax, String.valueOf(product.getCoordinates().getX()).length());
                yMax = Math.max(yMax, String.valueOf(product.getCoordinates().getY()).length());
                priceMax = Math.max(priceMax, String.valueOf(product.getPrice()).length());
                orgIdMax = Math.max(orgIdMax, String.valueOf(product.getManufacturer().getId()).length());
                orgNameMax = Math.max(orgNameMax, product.getManufacturer().getName().length());
                orgFullNameMax = Math.max(orgFullNameMax, product.getManufacturer().getFullName().length());

            }

            // Define column widths
            int idColumnWidth = idMax + 5;
            int nameColumnWidth = nameMax + 5;
            int xsColumnWidth = xMax + 5;
            int ysColumnWidth = yMax + 5;
            int datesColumnWidth = 16;
            int pricesColumnWidth = priceMax + 5;
            int unitOfMeasureColumnWidth = 18;
            int orgIdColumnWidth = orgIdMax + 5;
            int orgNamesColumnWidth = orgNameMax + 5;
            int orgFullNamesColumnWidth = orgFullNameMax + 5;
            int orgTypesColumnWidth = 20;

            StringBuilder line = new StringBuilder();


            String width = (String.format("%-" + pricesColumnWidth + "s%-" + nameColumnWidth + "s%-"
                            + idColumnWidth + "s%-" + xsColumnWidth + "s%-" + ysColumnWidth + "s%-"
                            + datesColumnWidth + "s%-" + unitOfMeasureColumnWidth + "s%-" + orgIdColumnWidth + "s%-"
                            + orgNamesColumnWidth+ "s%-" + orgFullNamesColumnWidth+ "s%-" + orgTypesColumnWidth+ "s%n",
                    "PRICE", "NAME", "ID", "X", "Y", "DATE", "UNITOFMEASURE", "ORGID", "ORGNAME", "ORGFULLNAME", "ORGTYPE"));
            String width2 = (String.format("-".repeat(pricesColumnWidth + nameColumnWidth + idColumnWidth
                    + xsColumnWidth + ysColumnWidth + datesColumnWidth + unitOfMeasureColumnWidth
                    + orgIdColumnWidth + orgNamesColumnWidth + orgFullNamesColumnWidth + orgTypesColumnWidth)));
            line.append(width);
            line.append(width2).append("\n");


//            Arrays.sort(products);


            for (Product product : products) {
                String formattedId = String.format("%-" + idColumnWidth + "d", product.getId());
                String formattedName = String.format("%-" + nameColumnWidth + "s", product.getName());
                String formattedX = String.format("%-" + xsColumnWidth + "d", product.getCoordinates().getX());
                String formattedY = String.format("%-" + ysColumnWidth + "d", product.getCoordinates().getY());
                String formattedDate = String.format("%-" + datesColumnWidth + "s", product.getCreationDate());
                String formattedPrice = String.format("%-" + pricesColumnWidth + "d", product.getPrice());
                String formattedUnitOfMeasure = String.format("%-" + unitOfMeasureColumnWidth + "s", product.getUnitOfMeasure());
                String formattedOrgId = String.format("%-" + orgIdColumnWidth + "s", product.getManufacturer().getId());
                String formattedOrgName = String.format("%-" + orgNamesColumnWidth + "s", product.getManufacturer().getName());
                String formattedOrgFullName = String.format("%-" + orgFullNamesColumnWidth + "s", product.getManufacturer().getFullName());
                String formattedOrgType = String.format("%-" + orgTypesColumnWidth + "s", product.getManufacturer().getType());

                String prod = formattedPrice + formattedName + formattedId + formattedX + formattedY + formattedDate + formattedUnitOfMeasure + formattedOrgId + formattedOrgName + formattedOrgFullName + formattedOrgType + "\n";
                line.append(prod);
            }
            return  new Response(line.toString());
        }
    }


    /**
     * Очищает коллекцию, удаляя из неё все элементы.
     */
    public static Response clearCollection() {
        collection.clear();
        return new Response("Коллекция очищена");
    }

    /**
     * Удаляет первый элемент из коллекции.
     */
    public static Response removeFirst() {
        collection.removeFirst();
        return new Response("Первый элемент удален из коллекции");
    }

    /**
     * Перемешивает элементы в коллекции.
     */
    public static Response shuffleCollection() {
        Collections.shuffle(collection);
        return new Response("Коллекция перемешана");
    }

    /**
     * Удаляет элемент из коллекции по его ID.
     *
     * @param id ID элемента для удаления.
     * @return
     */
    public static void removeById(int id) throws InvalidInputException {
        for (Product product : collection) {
            if (product.getId() == id){
                collection.remove(product);
                break;
            }
        }
    }


    /**
     * Обновляет элемент в коллекции по его ID.
     *
     * @param args Аргументы команды. args[1] содержит новый ID для обновления элемента.
     * @return
     */
    public static Response updateId(String[] args) {
        int id;
        try {
            id = Integer.parseInt(args[1]);
            int flag = 0;
            for (Product product : collection) {
                if (product.getId() == id) {
                    collection.remove(product);
                    collection.add(ProductGenerator.createProduct(id));
                    flag = 1;
                    return new Response("Элемент обновлен");
                }
            }
            if (flag == 0) {
                return new Response("No element with this id");
            }
        } catch (Exception e) {
            return new Response("Wrong id format");
        }
        return new Response("Wrong id format");
    }

    /**
     * Фильтрует коллекцию по имени продукта.
     *
     * @param str Образец продукта для фильтрации.
     */
    public static Response filterContainsName(String str) {
        ArrayList<Product> filterObjects = new ArrayList<>();
        for (Product product : collection) {
            if (product.getName().contains(str)) {
                filterObjects.add(product);
            }
        }
        if (filterObjects.size() == 0) {
            return new Response("Нет ни одного экземпляра с таким полем");
        } else {
//            System.out.println(filterObjects);
            Product[] products = filterObjects.toArray(new Product[0]);
            if (filterObjects.isEmpty()) {
                return new Response("Collection is empty");
            } else {

                int idMax = 0;
                int nameMax = 0;
                int xMax = 0;
                int yMax = 0;
                int priceMax = 0;
                int orgIdMax = 0;
                int orgNameMax = 0;
                int orgFullNameMax = 0;

                for (Product product : filterObjects) {

                    idMax = Math.max(idMax, String.valueOf(product.getId()).length());
                    nameMax = Math.max(nameMax, product.getName().length());
                    xMax = Math.max(xMax, String.valueOf(product.getCoordinates().getX()).length());
                    yMax = Math.max(yMax, String.valueOf(product.getCoordinates().getY()).length());
                    priceMax = Math.max(priceMax, String.valueOf(product.getPrice()).length());
                    orgIdMax = Math.max(orgIdMax, String.valueOf(product.getManufacturer().getId()).length());
                    orgNameMax = Math.max(orgNameMax, product.getManufacturer().getName().length());
                    orgFullNameMax = Math.max(orgFullNameMax, product.getManufacturer().getFullName().length());

                }

                // Define column widths
                int idColumnWidth = idMax + 5;
                int nameColumnWidth = nameMax + 5;
                int xsColumnWidth = xMax + 5;
                int ysColumnWidth = yMax + 5;
                int datesColumnWidth = 16;
                int pricesColumnWidth = priceMax + 5;
                int unitOfMeasureColumnWidth = 18;
                int orgIdColumnWidth = orgIdMax + 5;
                int orgNamesColumnWidth = orgNameMax + 5;
                int orgFullNamesColumnWidth = orgFullNameMax + 5;
                int orgTypesColumnWidth = 20;


                StringBuilder line = new StringBuilder();
                String width = String.format("%-" + pricesColumnWidth + "s%-" + nameColumnWidth + "s%-"
                                + idColumnWidth + "s%-" + xsColumnWidth + "s%-" + ysColumnWidth + "s%-"
                                + datesColumnWidth + "s%-" + unitOfMeasureColumnWidth + "s%-" + orgIdColumnWidth + "s%-"
                                + orgNamesColumnWidth+ "s%-" + orgFullNamesColumnWidth+ "s%-" + orgTypesColumnWidth+ "s%n",
                        "PRICE", "NAME", "ID", "X", "Y", "DATE", "UNITOFMEASURE", "ORGID", "ORGNAME", "ORGFULLNAME", "ORGTYPE");
                String width2 = String.format("-".repeat(pricesColumnWidth + nameColumnWidth + idColumnWidth
                        + xsColumnWidth + ysColumnWidth + datesColumnWidth + unitOfMeasureColumnWidth
                        + orgIdColumnWidth + orgNamesColumnWidth + orgFullNamesColumnWidth + orgTypesColumnWidth));


                line.append(width);
                line.append(width2);
                line.append("\n");

//            Arrays.sort(products);


                for (Product product : products) {
                    String formattedId = String.format("%-" + idColumnWidth + "d", product.getId());
                    String formattedName = String.format("%-" + nameColumnWidth + "s", product.getName());
                    String formattedX = String.format("%-" + xsColumnWidth + "d", product.getCoordinates().getX());
                    String formattedY = String.format("%-" + ysColumnWidth + "d", product.getCoordinates().getY());
                    String formattedDate = String.format("%-" + datesColumnWidth + "s", product.getCreationDate());
                    String formattedPrice = String.format("%-" + pricesColumnWidth + "d", product.getPrice());
                    String formattedUnitOfMeasure = String.format("%-" + unitOfMeasureColumnWidth + "s", product.getUnitOfMeasure());
                    String formattedOrgId = String.format("%-" + orgIdColumnWidth + "s", product.getManufacturer().getId());
                    String formattedOrgName = String.format("%-" + orgNamesColumnWidth + "s", product.getManufacturer().getName());
                    String formattedOrgFullName = String.format("%-" + orgFullNamesColumnWidth + "s", product.getManufacturer().getFullName());
                    String formattedOrgType = String.format("%-" + orgTypesColumnWidth + "s", product.getManufacturer().getType());

                    String prod = formattedPrice + formattedName + formattedId + formattedX + formattedY + formattedDate + formattedUnitOfMeasure + formattedOrgId + formattedOrgName + formattedOrgFullName + formattedOrgType+"\n";
                    line.append(prod);
                }
                return new Response(line.toString());
            }
        }
    }

    /**
     * Метод для выполнения команды execute_script
     * @param file_path путь к файлу
     * @throws FileNotFoundException ошибка отсутствия файла
     */
    public static Response executeScript(String file_path) throws FileNotFoundException {

        Scanner scanner = new Scanner(new FileReader(file_path));
        try{
            while(scanner.hasNextLine()){
                String line =scanner.nextLine();
                Request request = new Request(line.split(" "));
                if(line.split(" ")[0].equals("add")){

                    int id = Integer.parseInt(scanner.nextLine());
                    String name = scanner.nextLine();
                    long x = Long.parseLong(scanner.nextLine());
                    int y = Integer.parseInt(scanner.nextLine());
                    Coordinates coordinates = new Coordinates(x, y);
                    int price = Integer.parseInt(scanner.nextLine());
                    UnitOfMeasure unitOfMeasure = UnitOfMeasure.valueOf(scanner.nextLine().toUpperCase());
                    Long orgId = Long.parseLong(scanner.nextLine());
                    String orgName = scanner.nextLine();
                    String fullName = scanner.nextLine();
                    OrganizationType type = OrganizationType.valueOf(scanner.nextLine().toUpperCase());
                    Organization manufacture = new Organization(orgId, orgName, fullName, type);

                    Product product = new Product(id);
                    product.setName(name);
                    product.setCoordinates(coordinates);
                    product.setPrice(price);
                    product.setUnitOfMeasure(unitOfMeasure);
                    product.setManufacturer(manufacture);

                    CollectionManager.add(product);
                    return new Response("элемент добавлен");
                } else if(line.split(" ")[0].equals("update_id")){
                    for (Product product : collection){
                        int id = Integer.parseInt(line.split(" ")[1]);
                        if (product.getId()==id){
                            collection.remove(product);
                            int oldId = id;
                            String name = scanner.nextLine();
                            long x = Long.parseLong(scanner.nextLine());
                            int y = Integer.parseInt(scanner.nextLine());
                            Coordinates coordinates = new Coordinates(x, y);
                            int price = Integer.parseInt(scanner.nextLine());
                            UnitOfMeasure unitOfMeasure = UnitOfMeasure.valueOf(scanner.nextLine().toUpperCase());
                            Long orgId = Long.parseLong(scanner.nextLine());
                            String orgName = scanner.nextLine();
                            String fullName = scanner.nextLine();
                            OrganizationType type = OrganizationType.valueOf(scanner.nextLine().toUpperCase());
                            Organization manufacture = new Organization(orgId, orgName, fullName, type);

                            Product newProduct = new Product(oldId);
                            newProduct.setName(name);
                            newProduct.setCoordinates(coordinates);
                            newProduct.setPrice(price);
                            newProduct.setUnitOfMeasure(unitOfMeasure);
                            newProduct.setManufacturer(manufacture);

                            CollectionManager.add(newProduct);
                            return new Response("элемент обновлен");
                        }
                    }

                }else{
                    CommandManager.startExecuting(request);
                }
            }
            return new Response("команда выполнена :)");
        } catch (FileNotFoundException e) {
            return new Response("нет такого файла");
        } catch (UnknownCommandException | InvalidInputException e) {
            return new Response(e.getMessage());
        } catch (Exception e) {
            return new Response(e.getMessage());
        }
    }

    /**
     * Подсчитывает количество продуктов в коллекции по единице измерения.
     *
     * @param unit Образец продукта для фильтрации.
     */
    public static Response countByUnitOfMeasure(String unit) throws InvalidInputException {
        if (Validator.unitOfMeasureIsRight(unit)) {
            int counter = 0;
            for (Product product : collection) {
                if (product.getUnitOfMeasure().equals(UnitOfMeasure.valueOf(unit.toUpperCase()))) {
                    counter++;
                }
            }
            if (counter == 0) {
                return new Response("Нет ни одного экземпляра с таким полем");
            } else {
                return new Response("Число продуктов с такой единицей измерения: " + counter);
            }
        }
        return new Response("Некорректное поле единицы измерения");
    }

    /**
     * Сохраняет коллекцию в файл.
     *
     * @throws IOException   Если возникает ошибка ввода-вывода при сохранении.
     * @throws RootException Если возникает ошибка при записи корневого элемента.
     */
    public static Response save() throws IOException, RootException {
        FileManager.write("example.xml");
        return new Response("Save successfully");
    }

    /**
     * Сортирует коллекцию по цене.
     */
    public static Response sortCollection() {
        Collections.sort(collection);
        return new Response("коллекция отсортирована");
    }

    /**
     * Выводит элементы коллекции в порядке убывания цены.
     */
    public static void printDescending() {
        collection.sort(Collections.reverseOrder());
    }

    public static Boolean isIdExist(Long id){
        for (Product product : collection) {
            if (product.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }
}
