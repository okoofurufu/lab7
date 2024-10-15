package managers.commands;

import exceptions.UserExistsException;
import managers.DBManager;
import system.Request;
import system.Response;
import system.User;

import java.io.Serial;
import java.io.Serializable;

public class Register extends Command implements Serializable {

    @Serial
    private static final long serialVersionUID = -2264026831201018523L;

    public Register() {
        super("register", true);
    }

    @Override
    public Response execute(Request request){
        User user = request.getUser();
        DBManager dbManager = new DBManager();
        try{
            dbManager.addUser(user);
            return new Response("User added successfully");
        }catch (UserExistsException e){
            return new Response(e.getMessage());
        }
        catch (Exception e){
            return new Response("Error while adding user, try another login");
        }

    }
}
