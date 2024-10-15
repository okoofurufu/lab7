package managers;

public class Query {

    String checkUser = "SELECT id FROM users where login = ?;";

    String getSalt = "SELECT salt from users where login = ? ;";
    String addUser = "INSERT INTO users (login, password, salt) VALUES (?, ?, ?)";

    String addProduct = """
            INSERT INTO products(name, coordinates_x, coordinates_y, creation_date, price, unit_of_measure, manufacturer_id, manufacturer_name, manufacturer_full_name, manufacturer_type, user_id) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
            RETURNING id;
            """;


    String deleteObject = "delete from products where (user_id = ?) and (id = ?) returning id;";

    String updateObject = """
            update products
            set (name, coordinates_x, coordinates_y, creation_date, price, unit_of_measure, manufacturer_id, manufacturer_name, manufacturer_full_name, manufacturer_type) = (?, ?, ?, ?, ?, ?, ?, ?, ?, ?) where (user_id = ? and id = ?) returning id;
            """;

    String addObjects = """
            select * from products;
            
            """;

    String getUserObjects =  """
            select * from products where user_id = ?;
            """;

    String getUserId = """
            select id from users where (login = ?) and (password =  ?);
            """;

    String removeFirst = "remove from products where id = 1 and user_id = ? RETURNING id;";
}
