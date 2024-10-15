package exceptions;

import classes.OrganizationType;
import classes.UnitOfMeasure;

/**
 * Класс для проверки входных данных.
 */
public class Validator {

    /**
     * Проверяет, что значение идентификатора больше 0.
     *
     * @param id Строка с аргументами для проверки.
     * @throws InvalidInputException если идентификатор не больше 0.
     */
    public static void idMoreThanZero(String id) throws InvalidInputException{
        try {
            Integer arg = Integer.parseInt(id);
            if (arg < 0 || arg > Integer.MAX_VALUE){
                throw new InvalidInputException("id");
            }
        }
        catch (Exception e) {
            throw new InvalidInputException("id");
        }
    }

    /**
     * Проверяет, что цена больше 0.
     *
     * @param args Строка с аргументами для проверки.
     * @throws InvalidInputException если цена не больше 0.
     */
    public static void priceMoreThanZero(String args) throws InvalidInputException{
        try {
            int price = Integer.parseInt(args);
            if ((price < 0) || price > Integer.MAX_VALUE){
                throw new InvalidInputException("price");
            }
        }
        catch (Exception e) {
            throw new InvalidInputException("price");
        }
    }

    /**
     * Проверяет, что значение не является null и преобразуемо в int.
     *
     * @param args Строка с аргументами для проверки.
     * @param data Строка с названием данных для сообщения об ошибке.
     * @throws NullPointerException если значение null или не преобразуется в int.
     */
    public static void notNullint(String args, String data) throws NullPointerException{
        try {
            int value = Integer.parseInt(args);
            if (args == null || value > Long.MAX_VALUE) {
                throw new NullPointerException(data);
            }
        } catch (Exception e) {
            throw new NullPointerException(data);
        }
    }

    /**
     * Проверяет, что значение не является null и преобразуемо в long.
     *
     * @param args Строка с аргументами для проверки.
     * @param data Строка с названием данных для сообщения об ошибке.
     * @throws NullPointerException если значение null или не преобразуется в long.
     */
    public static void notNullLong(String args, String data) throws NullPointerException {
        try {
            Long value = Long.parseLong(args);
            if (value == null || value == 0 || value > Long.MAX_VALUE) {
                throw new NullPointerException(data);
            }
        } catch (Exception e) {
            throw new NullPointerException(data);
        }
    }


    /**
     * Проверяет, что значение строки не пустое.
     *
     * @param args Строка с аргументами для проверки.
     * @throws InvalidInputException если строка пустая.
     */
    public static void inputIsEmpty(String args, String data) throws InvalidInputException{
        if (args.isEmpty() || args.trim().isEmpty()) {
            System.out.println("Поле пустое, введите значение.");
            throw new InvalidInputException(data);

        }
    }

    /**
     * Проверяет, что значение является целым числом.
     *
     * @param args Строка с аргументами для проверки.
     * @return true, если значение целое число, иначе false.
     * @throws InvalidInputException если значение не является целым числом.
     */
    public static boolean intIsInt(String args) throws InvalidInputException{
        try{
            int value = Integer.parseInt(args);
            return true;
        }catch (Exception e){
            System.out.println("Введите значение int");
            throw new InvalidInputException(args);
        }
    }

    /**
     * Проверяет, что значение строки соответствует одному из перечислений UnitOfMeasure.
     *
     * @param args Строка с аргументами для проверки.
     * @throws InvalidInputException если значение не соответствует перечислению UnitOfMeasure.
     */
    public static boolean unitOfMeasureIsRight(String args) throws InvalidInputException {
        try {
            UnitOfMeasure unit = UnitOfMeasure.valueOf(args.toUpperCase());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Проверяет, что значение строки соответствует одному из перечислений OrganizationType.
     *
     * @param args Строка с аргументами для проверки.
     * @throws InvalidInputException если значение не соответствует перечислению OrganizationType.
     */
    public static void organizationTypeIsRight(String args) throws InvalidInputException {
        try {
            OrganizationType.valueOf(args);
        } catch (Exception e) {
            System.out.println("Такого типа не существует");
            throw new InvalidInputException("OrganizationType");
        }
    }
}
