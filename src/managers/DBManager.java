package managers;

import classes.*;
import exceptions.UserExistsException;
import system.User;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class DBManager {
    private static final Query queryManager = new Query();


    public static Connection connect() {
        try {
           // Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection("jdbc:postgresql://pg:5432/studs", "s385054", "CHJfQZEyWzhXBEfY");
        } catch (SQLException e) {
            System.err.println("Error with connection to DataBase");

            e.printStackTrace();
        }
        return null;
    }

    public void addUser(User user) throws UserExistsException {
        Connection connection = connect();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(queryManager.checkUser);
            preparedStatement.setString(1,user.getLogin());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                throw new UserExistsException();
            }
            Hasher passwordHasherManager = new Hasher();
            String salt = saltGenerator();
            String username = user.getLogin();
            String password = passwordHasherManager.hashPassword(user.getPassword() + salt );
            PreparedStatement pr = connection.prepareStatement(queryManager.addUser);
            pr.setString(1, username);
            pr.setString(2, password);
            pr.setString(3, salt);
            pr.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Ошибка при выполнении запроса");
        }
    }

    public int addProduct(Product product, User user) {
        Connection connection = connect();
        int user_id = 0;
        try {
            Hasher passwordHasherManager = new Hasher();
            PreparedStatement getSalt = connection.prepareStatement(queryManager.getSalt);
            getSalt.setString(1,user.getLogin());
            ResultSet getSaltResult = getSalt.executeQuery();
            if (!getSaltResult.next()){
                return 0;
            }
            String salt = getSaltResult.getString(1);
            String password = passwordHasherManager.hashPassword(user.getPassword() + salt );
            PreparedStatement preparedStatement = connection.prepareStatement(queryManager.getUserId);
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2,password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                user_id = resultSet.getInt(1);
            }
            preparedStatement = connection.prepareStatement(queryManager.addProduct);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getCoordinates().getX());
            preparedStatement.setInt(3, product.getCoordinates().getY());
            preparedStatement.setString(4, product.getCreationDate().toString());
            preparedStatement.setInt(5, product.getPrice());
            preparedStatement.setString(6, product.getUnitOfMeasure().toString());
            preparedStatement.setLong(7, product.getManufacturer().getId());
            preparedStatement.setString(8, product.getManufacturer().getName());
            preparedStatement.setString(9, product.getManufacturer().getFullName());
            preparedStatement.setString(10, product.getManufacturer().getType().toString());
            preparedStatement.setInt(11, user_id);
            try {
                resultSet = preparedStatement.executeQuery();
            }catch(Exception e){
                e.printStackTrace();
            }
            if (!resultSet.next()) {
                System.err.println("Не удалось добавить объект");
                return -1;
            }
            System.err.println("Объект был успешно добавлен");
            return resultSet.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Ошибка при выполнении запроса");
            return -1;
        }
    }

    public int removeFirst(User user){
        Connection connection = connect();
        int user_id = 0;
        try{
            Hasher passwordHasherManager = new Hasher();
            PreparedStatement getSalt = connection.prepareStatement(queryManager.getSalt);
            getSalt.setString(1,user.getLogin());
            ResultSet getSaltResult = getSalt.executeQuery();
            if (!getSaltResult.next()){
                return 0;
            }
            String salt = getSaltResult.getString(1);
            String password = passwordHasherManager.hashPassword(user.getPassword() + salt );
            PreparedStatement preparedStatement = connection.prepareStatement(queryManager.getUserId);
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2,password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                user_id = resultSet.getInt(1);
            }
            preparedStatement = connection.prepareStatement(queryManager.removeFirst);
            preparedStatement.setInt(1,user_id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                return 1;
            }
            return 0;
        } catch (SQLException e) {
            return -1;
        }
    }



    public static boolean deleteObject(int id, User user) {
        Connection connection = connect();
        int user_id = 0;
        try {
            Hasher passwordHasherManager = new Hasher();
            PreparedStatement getSalt = connection.prepareStatement(queryManager.getSalt);
            getSalt.setString(1,user.getLogin());
            ResultSet getSaltResult = getSalt.executeQuery();
            if (!getSaltResult.next()){
                return false;
            }
            String salt = getSaltResult.getString(1);
            String password = passwordHasherManager.hashPassword(user.getPassword() + salt );
            PreparedStatement preparedStatement = connection.prepareStatement(queryManager.getUserId);
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2,password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                user_id = resultSet.getInt(1);
            }
            preparedStatement = connection.prepareStatement(queryManager.deleteObject);
            preparedStatement.setInt(1, user_id);
            preparedStatement.setLong(2, id);
            resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            return false;
        }

    }

    public boolean existUser(User user){
        Connection connection = connect();
        try{
            Hasher passwordHasherManager = new Hasher();
            PreparedStatement getSalt = connection.prepareStatement(queryManager.getSalt);
            getSalt.setString(1,user.getLogin());
            ResultSet getSaltResult = getSalt.executeQuery();
            if (!getSaltResult.next()){
                return false;
            }
            String salt = getSaltResult.getString(1);
            String password = passwordHasherManager.hashPassword(user.getPassword() + salt );
            PreparedStatement preparedStatement = connection.prepareStatement(queryManager.getUserId);
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2,password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                return true;
            }
        }catch (SQLException e){
            System.out.println("SQL error");
        }
        return false;
    }
    public boolean updateObject(int id, User user, Product product) {
        Connection connection = connect();
        int user_id = 0;
        try {
            Hasher passwordHasherManager = new Hasher();
            PreparedStatement getSalt = connection.prepareStatement(queryManager.getSalt);
            getSalt.setString(1,user.getLogin());
            ResultSet getSaltResult = getSalt.executeQuery();
            if (!getSaltResult.next()){
                return false;
            }
            String salt = getSaltResult.getString(1);
            String password = passwordHasherManager.hashPassword(user.getPassword() + salt );
            PreparedStatement preparedStatement = connection.prepareStatement(queryManager.getUserId);
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2,password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                user_id = resultSet.getInt(1);
            }
            preparedStatement = connection.prepareStatement(queryManager.updateObject);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setLong(2, product.getCoordinates().getY());
            preparedStatement.setDouble(3, product.getCoordinates().getX());
            preparedStatement.setString(4, product.getCreationDate().toString());
            preparedStatement.setInt(5, product.getPrice());
            preparedStatement.setString(6, product.getUnitOfMeasure().toString());
            preparedStatement.setLong(7, product.getManufacturer().getId());
            preparedStatement.setString(8, product.getManufacturer().getName());
            preparedStatement.setString(9, product.getManufacturer().getFullName());
            preparedStatement.setString(10, product.getManufacturer().getType().toString());
            preparedStatement.setInt(11, user_id);
            preparedStatement.setInt(12, id);
            resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            return false;
        }
    }

    public LinkedList<Product> createCollection() {
        Connection connection = connect();
        LinkedList<Product> products = new LinkedList<>();
        try {
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(queryManager.addObjects);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    products.add(new Product(resultSet.getInt(1),
                            resultSet.getString(2),
                            new Coordinates(resultSet.getLong(3),
                                    resultSet.getInt(4)),
                            resultSet.getString(5),
                            resultSet.getInt(6),
                            UnitOfMeasure.valueOf(resultSet.getString(7)),
                            new Organization(resultSet.getLong(8),
                                    resultSet.getString(9),
                                    resultSet.getString(10),
                                    OrganizationType.valueOf(resultSet.getString(11)))));
                }
                return products;


            } catch (SQLException e) {
                e.printStackTrace();
                System.err.println("Ошибка выполнения запроса");
                return new LinkedList<>();
            }


        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            System.err.println("Поля объектов не валидны");
            return new LinkedList<>();
        }
    }

    public List<String> showUserObjects(User user) {
        Connection connection = connect();
        List<String> products = new LinkedList<>();
        int user_id = 0;
        try {
            Hasher passwordHasherManager = new Hasher();
            PreparedStatement getSalt = connection.prepareStatement(queryManager.getSalt);
            getSalt.setString(1,user.getLogin());
            ResultSet getSaltResult = getSalt.executeQuery();
            if (!getSaltResult.next()){
                return new LinkedList<>();
            }
            String salt = getSaltResult.getString(1);
            String password = passwordHasherManager.hashPassword(user.getPassword() + salt );
            PreparedStatement preparedStatement = connection.prepareStatement(queryManager.getUserId);
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2,password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                user_id = resultSet.getInt(1);
            }
            preparedStatement = connection.prepareStatement(queryManager.getUserObjects);
            preparedStatement.setInt(1,user_id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                products.add(new Product(resultSet.getInt(1),
                        resultSet.getString(2),
                        new Coordinates(resultSet.getLong(3),
                                resultSet.getInt(4)),
                        resultSet.getString(5),
                        resultSet.getInt(6),
                        UnitOfMeasure.valueOf(resultSet.getString(7)),
                        new Organization(resultSet.getLong(8),
                                resultSet.getString(9),
                                resultSet.getString(10),
                                OrganizationType.valueOf(resultSet.getString(11)))).toString());
            }
                return products;
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            System.err.println("Поля объектов не валидны");
            return new LinkedList<>();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    private String saltGenerator() {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        Random random = new Random();
        StringBuilder sb = new StringBuilder(15);

        for (int i = 0; i < 15; i++) {
            int index = random.nextInt(alphabet.length());
            char randomChar = alphabet.charAt(index);
            sb.append(randomChar);
        }

        return sb.toString();
    }
}
