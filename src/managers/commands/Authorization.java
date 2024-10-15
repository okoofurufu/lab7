package managers.commands;

import managers.DBManager;
import system.Request;
import system.Response;
import system.User;

public class Authorization extends Command{
    public Authorization() {
        super("autorization", true);
    }

    @Override
    public Response execute(Request request) {

        DBManager dbManager = new DBManager();
        User user = request.getUser();
        if (dbManager.existUser(user)){
            return new Response("Successfull authorization!");
        }
        return new Response("User with this login and password does not exist");
    }
}
