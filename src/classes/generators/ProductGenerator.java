package classes.generators;

import classes.*;
import exceptions.Validator;

import java.io.Serial;
import java.io.Serializable;
import java.util.Scanner;
/**
 * Класс для генерации экземпляров класса Product с помощью пользовательского ввода.
 */
public class ProductGenerator implements Serializable {
    @Serial
    private static final long serialVersionUID = -224L;

    /**
     * Создает новый объект типа Product с заданным идентификатором.
     *
     * @param id Идентификатор для нового продукта.
     * @return Новый объект типа Product.
     */

    public static Product createProduct(Integer id) {
        System.out.println("Generate...");

        Scanner scanner = new Scanner(System.in);

        String input;
        long x;
        int y;
        Coordinates coordinates;
        int price;
        long orgId;
        String name;
        String fullName;

        Organization manufacturer;


        Product product;
        if (id == 0) {
            product = new Product();
        } else {
            product = new Product(id);
        }


        while (true) {
            try {
                System.out.println("Введите имя: ");
                input = scanner.nextLine();
                product.setName(input);
                if (!(input.isEmpty())) {
                    break;
                } else {
                    System.out.println("имя не может быть пустым");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        while (true) {
            try {
                System.out.println("Введите координату x: ");
                x = Long.parseLong(scanner.nextLine());
                break;

            } catch (Exception e) {
                System.out.println("необходимо ввести long число");
            }
        }


        while (true) {
            try {
                System.out.println("Введите координату y: ");
                y = Integer.parseInt(scanner.nextLine());
                break;
            } catch (Exception e) {
                System.out.println("необходимо ввести int число");
            }
        }

        coordinates = new Coordinates(x, y);
        product.setCoordinates(coordinates);

        while (true) {
            try {
                System.out.println("Введите цену");
                price = Integer.parseInt(scanner.nextLine());
                if (Integer.parseInt(String.valueOf(price)) > 0) {
                    product.setPrice(Integer.parseInt(String.valueOf(price)));
                    break;
                } else {
                    System.out.println("цена не может быть отрицательной");
                }


            } catch (Exception e) {
                System.out.println("данное значение не подходит, введите int число");
            }
        }

        while (true) {
            try {
                System.out.println("Введите единицу измерения из доступных вариантов: KILOGRAMS" + "   METERS" + "   CENTIMETERS" + "   MILLIGRAMS");
                input = scanner.nextLine();
                if (Validator.intIsInt(input)) {
                    int index = Integer.parseInt(input);
                    UnitOfMeasure type = switch (index) {
                        case 1 -> UnitOfMeasure.KILOGRAMS;
                        case 2 -> UnitOfMeasure.METERS;
                        case 3 -> UnitOfMeasure.CENTIMETERS;
                        case 4 -> UnitOfMeasure.MILLIGRAMS;
                        default -> null;
                    };
                    if (type == null) {
                        System.out.println("слишком большое значение");
                    } else {
                        product.setUnitOfMeasure(type);
                        break;
                    }

                }
            } catch (Exception e) {
                try {
                    input = input.toUpperCase();
                    product.setUnitOfMeasure(UnitOfMeasure.valueOf(input));
                    break;
                } catch (Exception a) {
                    System.out.println("Единицы измерения " + input + " не существует");
                }

            }
        }

        while (true) {
            try {
                System.out.println("Введите id организации");
                orgId = Long.parseLong(scanner.nextLine());
                if (orgId > 0) {
                    break;
                } else {
                    System.out.println("id не может быть отрицательным");
                }

            } catch (Exception e) {
                System.out.println("данное значение не подходит, введите long число");
            }
        }
        while (true) {
            try {
                System.out.println("Введите название организации: ");
                name = scanner.nextLine();
                if (!(name.isEmpty())) {
                    break;
                } else {
                    System.out.println("название организации не может быть пустым");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        while (true) {
            try {
                System.out.println("Введите полное название организации: ");
                fullName = scanner.nextLine();
                if (!(fullName.isEmpty())) {
                    break;
                } else {
                    System.out.println("полное название организации не может быть пустым");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        while (true) {
            manufacturer = new Organization(orgId, name, fullName, null);
            try {
                System.out.println("Введите тип организации из доступных вариантов: COMMERCIAL" + "   PUBLIC" + "   GOVERNMENT" + "   PRIVATE_LIMITED_COMPANY");
                input = scanner.nextLine();
                if (Validator.intIsInt(input)) {
                    int index = Integer.parseInt(input);
                    input = input.toUpperCase();
                    OrganizationType type = switch (index) {
                        case 1 -> OrganizationType.COMMERCIAL;
                        case 2 -> OrganizationType.PUBLIC;
                        case 3 -> OrganizationType.GOVERNMENT;
                        case 4 -> OrganizationType.PRIVATE_LIMITED_COMPANY;
                        default -> null;

                    };
                    if (type == null) {
                        System.out.println("слишком большое значение");
                    }
                    else {manufacturer.setType(type);
                        product.setManufacturer(manufacturer);
                        break;}
                }
            } catch (Exception e) {
                try {
                    input = input.toUpperCase();
                    manufacturer.setType(OrganizationType.valueOf(input));
                    product.setManufacturer(manufacturer);
                    break;
                } catch (Exception a) {
                    System.out.println("Типа " + input + " не существует");
                }
            }
        }
        System.out.println("Generation is complete");
        return product;
    }
}

