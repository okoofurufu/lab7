import managers.*;
import system.Server;

import java.util.logging.Logger;

public class Main {
    public static void main(String[] args){
        final Logger serverLogger = Logger.getLogger("logger");
        CommandManager commandManager = new CommandManager();
        CollectionManager collectionManager = new CollectionManager();
        DBManager dbManager = new DBManager();
        CollectionManager.setCollection(dbManager.createCollection());
        RunManager runManager = new RunManager(commandManager);


        Server server = new Server( 2577, runManager, dbManager, collectionManager);
        server.run();
    }
}